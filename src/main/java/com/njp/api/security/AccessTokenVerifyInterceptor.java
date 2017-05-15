package com.njp.api.security;

import com.google.gson.Gson;
import com.njp.api.model.ErrorResult;
import com.njp.api.service.ValidationService;
import lombok.extern.java.Log;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by niejinping on 2017/5/15.
 */
@Component
@Log
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ValidationService validationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("access token before");
        boolean flag = false;

        // TODO 协议验证
        //　APIUTILS.check();

// token
        String accessToken = request.getParameter("token");
        if (accessToken != null && accessToken.isEmpty() == false) {
// 验证
            //  ValidationModel v = validationService.verifyAccessToken(accessToken);
// TODO 时间过期
// 用户验证
/*            if (v != null) {
                User user = userService.findById(v.getUid());
                if(user != null) {
                    request.setAttribute(CommonConst.PARAM_USER, user);
                    log.info("AccessToken SUCCESS ...  user:" + user.getUserName() + " - " + accessToken);
                    flag = true;
                }
            }
        }*/

        // 测试用，以后可以在这里对，token类型，CRUD类型，通过路由表进行路由
        if(request.getMethod().equalsIgnoreCase("get")){
            flag = true;
        }

        }

        // TODO 路由规则引擎，可以在这里面上了　RuleService RouteService

        if (!flag) {
            ErrorResult errorResult = new ErrorResult();
            errorResult.code = HttpStatus.FORBIDDEN.value();
            errorResult.message = "forbidden";
            errorResult.url = request.getRequestURL().toString();

            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(new Gson().toJson(errorResult));
        }

        return flag;
    }
}