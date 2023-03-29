package com.realblox.dimetime.control;

import com.realblox.dimetime.model.UserRiskVO;
import com.realblox.dimetime.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {
    Logger _logger = LoggerFactory.getLogger( IndexController.class );

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, ModelMap modelMap) throws Exception {
        UserRiskVO userRiskVO = new UserRiskVO();
        List<UserRiskVO> list = userService.getUserList(userRiskVO);

        modelMap.put("user_list", list );
        return "user/user_risk_list";
    }
}