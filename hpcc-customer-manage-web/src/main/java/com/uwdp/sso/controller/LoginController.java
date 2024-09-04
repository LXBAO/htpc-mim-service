package com.uwdp.sso.controller;

import com.uwdp.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /*jobNo 是oa中的工号 , 对应前端平台的账号*/
    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jobNo = request.getParameter("jobNo");
        String location = loginService.login(jobNo);
        if (null == location) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>登录失败</title></head><body>");
            out.println("<p>" + "没有同步该用户! 工号:『"+jobNo+"』" + "</p>");
            out.println("<p>" + "请联系管理员! </p>");
            out.println("</body></html>");
        } else {
            response.sendRedirect(location);
        }
    }


}
