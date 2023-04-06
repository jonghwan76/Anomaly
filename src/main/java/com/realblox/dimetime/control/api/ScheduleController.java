package com.realblox.dimetime.control.api;

import com.realblox.dimetime.model.PatternSearchVO;
import com.realblox.dimetime.model.PatternVO;
import com.realblox.dimetime.model.RiskOrderVO;
import com.realblox.dimetime.model.RiskVO;
import com.realblox.dimetime.service.PatternService;
import com.realblox.dimetime.util.DateUtils;
import com.realblox.dimetime.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableScheduling
@Slf4j
public class ScheduleController {
    @Value("${excel.path1}")
    private String excelUserDataPath = "";

    @Value("${excel.path2}")
    private String excelHighRiskGroup = "";

    @Value("${excel.path3}")
    private String excelMidRiskGroup = "";


    @Value("${excel.command_main}")
    private String commandMain = "";

    @Value("${excel.command_kmeans}")
    private String commandKmeans = "";

    @Value("${excel.command_analyze_high}")
    private String commandAnalyzeHigh = "";

    @Value("${excel.command_analyze_mid}")
    private String commandAnalyzeMid = "";

    @Value("${pattern.high}")
    private String highYn = "";

    @Value("${pattern.mid}")
    private String midYn = "";

    @Value("${server.schedule}")
    private String schedule;

    @Autowired
    PatternService patternService;

    @Scheduled(fixedDelay=1000 * 60 * 60 * 1)	//1시간 마다 실행
    public void scheduleAnomaly() {
//        HashMap retMap = new HashMap();
        boolean resultHigh = false;
        boolean resultMid = false;
        String line;

        String today =  DateUtils.getDateToString("yyyy-MM-dd");

        try {
            if(schedule.equals("Y")) {
                
                //기존 집계파일 삭제 ------------------------------------------------------------------ 시작
                File file1 = new File(excelUserDataPath);
                File file2 = new File(excelHighRiskGroup);
                File file3 = new File(excelMidRiskGroup);

                if(file1.exists()) {
                    file1.delete();
                }
                if(file2.exists()) {
                    file2.delete();
                }
                if(file3.exists()) {
                    file3.delete();
                }
                //기존 집계파일 삭제 ------------------------------------------------------------------ 종료
                
                Process process = null;
                Runtime runtime = Runtime.getRuntime();
                StringBuffer cmdResult = new StringBuffer();

                //main.py --------------------------------------------------------------- start
                process = runtime.exec(commandMain);
                process.waitFor();
                BufferedReader reader1=new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );
                while((line = reader1.readLine()) != null)
                {
                    cmdResult.append(line);
                }
                log.info(cmdResult.toString());
                //main.py --------------------------------------------------------------- end

                //kmeans.py ------------------------------------------------------------- start
                cmdResult = new StringBuffer();
                process = runtime.exec(commandKmeans);
                process.waitFor();
                BufferedReader reader2=new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );
                while((line = reader2.readLine()) != null)
                {
                    cmdResult.append(line);
                }
                log.info(cmdResult.toString());
                //kmeans.py ------------------------------------------------------------- end

                Path path = Paths.get(excelUserDataPath);
                if(highYn.equals("Y") && path.toFile().isFile()) {
                    resultHigh = highAnalyze(today);
                }

                if(midYn.equals("Y") && path.toFile().isFile()) {
                    resultMid = midAnalyze(today);
                }

                log.info("resultHigh:" + resultHigh);
                log.info("resultMid:" + resultMid);
            }

        } catch(Exception e) {
            log.info("-- scheduleAnomaly Error --");
            log.info(e.toString());
        }

    }


    /**
     * 이상패턴감지 분석 - 고위험군
     * @return
     */
    public boolean highAnalyze(String today) {
        boolean result;
        String line = "";

        try {
            Process process;
            Runtime runtime = Runtime.getRuntime();
            StringBuffer cmdResult = new StringBuffer();

            //analyze_high.py ------------------------------------------------------------ start
            String analyzeResult1 = "";
            String[] splitAnalyzeResult1 = null;
            process = runtime.exec(commandAnalyzeHigh);
            process.waitFor();
            BufferedReader reader3=new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            while((line = reader3.readLine()) != null)
            {
                cmdResult.append(line);
            }

            log.info(cmdResult.toString());

            //분석결과가 있는 경우에만 처리
            if(!cmdResult.equals("") && cmdResult.indexOf("(") >=0) {
                analyzeResult1 = cmdResult.substring(cmdResult.indexOf("("), cmdResult.lastIndexOf(")")+1);
                splitAnalyzeResult1 = analyzeResult1.split("\\)");
                log.info(analyzeResult1);

                //금일 고위험군 데이터 삭제
                patternService.deleteHighRiskOrder(today);

                int order1 = 0;
                for(int i=0;i<splitAnalyzeResult1.length;i++) {
                    String row = splitAnalyzeResult1[i];
                    String[] arrarRow = null;
                    row = row.replaceAll("\\(", "");
                    row = row.replaceAll("\\'", "");
                    row = row.replaceAll(" ", "");
                    arrarRow = row.split(",");

                    RiskOrderVO riskOrderVO = new RiskOrderVO();
                    riskOrderVO.setUser_id(arrarRow[0]);
                    riskOrderVO.setUser_order( (++order1) + "");
                    riskOrderVO.setStat_dt( today );

                    patternService.insertHighRiskOrder(riskOrderVO);
                }
                //analyze_high.py ------------------------------------------------------------ end

                ExcelUtil excelUtil = new ExcelUtil();
                List<RiskVO> list1 = excelUtil.readCsv(excelHighRiskGroup, today);

                //기존 분석그룹 데이터 삭제
                patternService.deleteHighAnomaly(today);

                for(RiskVO riskVO:list1) {
                    patternService.insertHighAnomaly(riskVO);
                }
            }
            result = true;
        } catch(Exception e) {
            log.info("-- highAnalyze Error --");
            log.info(e.toString());
            result = false;
        }

        return result;
    }


    /**
     * 이상패턴감지 분석 - 중간 위험군
     * @return
     */
    public boolean midAnalyze(String today) {
        boolean result;
        String line = "";

        try {
            Process process;
            Runtime runtime = Runtime.getRuntime();
            StringBuffer cmdResult = new StringBuffer();

            //analyze_mid.py ------------------------------------------------------------ start
            String analyzeResult1 = "";
            String[] splitAnalyzeResult1 = null;
            process = runtime.exec(commandAnalyzeMid);
            process.waitFor();
            BufferedReader reader3=new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            while((line = reader3.readLine()) != null)
            {
                cmdResult.append(line);
            }
            log.info(cmdResult.toString());

            //분석결과가 있는 경우에만 처리
            if(!cmdResult.equals("") && cmdResult.indexOf("(") >=0) {
                analyzeResult1 = cmdResult.substring(cmdResult.indexOf("("), cmdResult.lastIndexOf(")")+1);
                splitAnalyzeResult1 = analyzeResult1.split("\\)");
                log.info(analyzeResult1);

                //금일 중간위험군 데이터 삭제
                patternService.deleteMidRiskOrder(today);

                int order1 = 0;
                for(int i=0;i<splitAnalyzeResult1.length;i++) {
                    String row = splitAnalyzeResult1[i];
                    String[] arrarRow = null;
                    row = row.replaceAll("\\(", "");
                    row = row.replaceAll("\\'", "");
                    row = row.replaceAll(" ", "");
                    arrarRow = row.split(",");

                    RiskOrderVO riskOrderVO = new RiskOrderVO();
                    riskOrderVO.setUser_id(arrarRow[0]);
                    riskOrderVO.setUser_order( (++order1) + "");
                    riskOrderVO.setStat_dt( today );

                    patternService.insertMidRiskOrder(riskOrderVO);
                }
                //analyze_mid.py ------------------------------------------------------------ end


                ExcelUtil excelUtil = new ExcelUtil();
                List<RiskVO> list1 = excelUtil.readCsv(excelMidRiskGroup, today);

                //기존 분석그룹 데이터 삭제
                patternService.deleteMidAnomaly(today);

                for(RiskVO riskVO:list1) {
                    patternService.insertMidAnomaly(riskVO);
                }
            }

            result = true;
        } catch(Exception e) {
            log.info("-- midAnalyze Error --");
            log.info(e.toString());
            result = false;
        }

        return result;
    }

}
