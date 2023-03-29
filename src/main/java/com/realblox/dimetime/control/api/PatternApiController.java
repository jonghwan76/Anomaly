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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/api/pattern/")
public class PatternApiController {
    @Value("${excel.path}")
    private String excelPath = "";
    @Autowired
    PatternService patternService;

    /**
     * 엑셀읽기
     * @return
     */
    @RequestMapping("/readCsv")
    @ResponseBody
    public HashMap readCsv() {
        HashMap retMap = new HashMap();
        String line = "";
        String today =  DateUtils.getDateToString("yyyy-MM-dd");

        try {
            Process process = null;
            Runtime runtime = Runtime.getRuntime();
            StringBuffer cmdResult = new StringBuffer();

/*
            //main.py --------------------------------------------------------------- start
            process = runtime.exec(excelPath + "main.bat");
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

 */


            //kmeans.py ------------------------------------------------------------- start
            cmdResult = new StringBuffer();
            process = runtime.exec(excelPath + "kmeans.bat");
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

            //analyze.py ------------------------------------------------------------ start
            String analyzeResult = "";
            String[] splitAnalyzeResult = null;
            cmdResult = new StringBuffer();
            process = runtime.exec(excelPath + "analyze.bat");
            process.waitFor();
            BufferedReader reader3=new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            while((line = reader3.readLine()) != null)
            {
                cmdResult.append(line);
            }
            log.info(cmdResult.toString());
            analyzeResult = cmdResult.substring(cmdResult.indexOf("("), cmdResult.lastIndexOf(")")+1);
            splitAnalyzeResult = analyzeResult.split("\\)");

            log.info("---- 순위 원본 ----");
            log.info(analyzeResult);
            log.info("---- 순위 원본 Split ----");

            //금일 데이터 삭제
            patternService.deleteRiskOrder(today);

            int order = 0;
            for(int i=0;i<splitAnalyzeResult.length;i++) {
                String row = splitAnalyzeResult[i];
                String[] arrarRow = null;
                row = row.replaceAll("\\(", "");
                row = row.replaceAll("\\'", "");
                row = row.replaceAll(" ", "");
                arrarRow = row.split(",");

                RiskOrderVO riskOrderVO = new RiskOrderVO();
                riskOrderVO.setUser_id(arrarRow[0]);
                riskOrderVO.setUser_order( (order++) + "");
                riskOrderVO.setStat_dt( today );

                patternService.insertRiskOrder(riskOrderVO);
//                log.info(arrarRow[0]);
//                log.info(arrarRow[1]);
            }
            //analyze.py ------------------------------------------------------------ end

            ExcelUtil excelUtil = new ExcelUtil();
            List<RiskVO> list1 = excelUtil.readCsv(excelPath + "\\result\\high_risk_group.csv", today);
            List<RiskVO> list2 = excelUtil.readCsv(excelPath + "\\result\\mid_risk_group.csv", today);

            for(RiskVO riskVO:list1) {
                patternService.insertHighAnomaly(riskVO);
            }

            for(RiskVO riskVO:list2) {
                patternService.insertMidAnomaly(riskVO);
            }

            retMap.put("high_cnt", list1.size());
            retMap.put("mid_cnt", list2.size());
        } catch(Exception e) {
            log.info(e.toString());
        }

        return retMap;
    }



    /**
     * 패턴정보조회
     * @param patternSearchVO
     * @return
     */
    @RequestMapping("/patternList")
    @ResponseBody
    public List<PatternVO> patternList(PatternSearchVO patternSearchVO) {
        List<PatternVO> patternList = patternService.getPatternList(patternSearchVO);
        return patternList;
    }

    /**
     * 패턴정보 등록 - JSON Body
     * @param body
     * @param request
     * @return
     */
    @RequestMapping(value = "/insertPatternBody", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public Object insertPatternBody(@RequestBody(required = false) String body, HttpServletRequest request) {
        Map<String, Object> retData = new HashMap<>();

        try {
            String data = URLDecoder.decode(body, "UTF-8");
            data = data.replaceAll("(\r\n|\n)", "");

            JSONObject jObject = new JSONObject(data);
            JSONArray jArray = jObject.getJSONArray("list");

            for (int i = 0; i < jArray.length(); i++) {
                PatternVO patternVO = new PatternVO();
                JSONObject obj = jArray.getJSONObject(i);

                patternVO.setUser_id(obj.getString("user_id"));
                patternVO.setGps_lat(obj.getString("gps_lat"));
                patternVO.setGps_lon(obj.getString("gps_lon"));

                try {
                    patternVO.setGyroscope_x(obj.getString("gyroscope_x"));
                } catch (Exception e) {}

                try {
                    patternVO.setGyroscope_y(obj.getString("gyroscope_y"));
                } catch (Exception e) {}

                try {
                    patternVO.setGyroscope_z(obj.getString("gyroscope_z"));
                } catch (Exception e) {}


                try {
                    patternVO.setTimestamp(obj.getString("Timestamp"));
                } catch(Exception e) {}

                try {
                    patternVO.setExt2(obj.getString("ext2"));
                } catch(Exception e) {}

                try {
                    patternVO.setExt3(obj.getString("ext3"));
                } catch(Exception e) {}

                patternService.insertPattern(patternVO);
            }

            retData.put("result", "200");
            retData.put("msg", "ok");
//            retData.put("data", data);
        } catch (Exception e) {
            log.info(e.toString());
            retData.put("result", false);
            retData.put("msg", "false");
        }

        return retData;
    }


    /**
     * 패턴정보 등록
     * @param patternVO
     * @return
     */
    @RequestMapping(value = "/insertPattern", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object insertPattern(PatternVO patternVO) {
        Map<String, Object> retData = new HashMap<>();

        try {
            int result = patternService.insertPattern(patternVO);

            if(result > 0) {
                retData.put("result", "200");
                retData.put("msg", "ok");
            } else {
                retData.put("result", "500");
                retData.put("msg", "false");
            }

        } catch (Exception e) {
            log.info(e.toString());
            retData.put("result", false);
            retData.put("msg", e.toString());
        }

        return retData;
    }
}
