package com.realblox.dimetime.control.api;

import com.realblox.dimetime.model.PatternSearchVO;
import com.realblox.dimetime.model.PatternVO;
import com.realblox.dimetime.model.UserTimeHistoryVO;
import com.realblox.dimetime.service.PatternService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/pattern/")
public class PatternApiController {
    Logger _logger = LoggerFactory.getLogger( PatternApiController.class );

    @Autowired
    PatternService patternService;

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
                patternVO.setGyroscope_x(obj.getString("gyroscope_x"));
                patternVO.setGyroscope_y(obj.getString("gyroscope_y"));
                patternVO.setGyroscope_z(obj.getString("gyroscope_z"));

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
            retData.put("data", data);
        } catch (Exception e) {
            _logger.info(e.toString());
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
            _logger.info(e.toString());
            retData.put("result", false);
            retData.put("msg", e.toString());
        }

        return retData;
    }
}
