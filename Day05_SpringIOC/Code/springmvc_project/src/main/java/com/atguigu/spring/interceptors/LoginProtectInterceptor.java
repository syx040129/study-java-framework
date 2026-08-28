package com.atguigu.spring.interceptors;

import com.atguigu.spring.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null || username.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            Result errorResult = Result.fail(501, "未登录，请先登录！");
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(errorResult));
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }
}