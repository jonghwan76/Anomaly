package com.realblox.dimetime.control.api;

import com.realblox.dimetime.model.PatternSearchVO;
import com.realblox.dimetime.model.PatternVO;
import com.realblox.dimetime.service.PatternService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableScheduling
@Slf4j
public class ScheduleController {
    @Autowired
    PatternService patternService;

//    @Scheduled(fixedDelay=1000 * 60 * 60 * 12)	//12시간 마다 실행
    public void patternList(PatternSearchVO patternSearchVO) {
        String line;
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer cmdResult = new StringBuffer();

        try {
            process = runtime.exec("C:\\tkeeper\\db\\backup_db.bat");
            process.waitFor();

            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            while((line = reader.readLine()) != null)
            {
                cmdResult.append(line);
            }

            // shell 실행이 정상 종료되었을 경우
            if (process.exitValue() == 0) {
                log.info("msg:" + cmdResult);
            } else {
            }
        } catch(Exception e) {

        }

    }
}
