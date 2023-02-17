package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
=======
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
>>>>>>> 哈哈哈哈哈
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        //请求转发到登录页面
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
<<<<<<< HEAD
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request){
=======
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
>>>>>>> 哈哈哈哈哈
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service层方法，查询用户
        User user = userService.queryUserByLoginActAndPwd(map);

        //根据查询结果生成相应信息
        ReturnObject returnObject = new ReturnObject();
        if(user == null){
            //登录失败，用户名或密码错误
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("登录失败，用户名或密码错误");
        }else{
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime()) > 0){
                //登录失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，账号已过期");
            }else if("0".equals(user.getLockState())){
                //登录失败，状态被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，状态被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，ip地址受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，ip地址受限");
            }else{
                //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
<<<<<<< HEAD
=======

                //把user保存到session中
                session.setAttribute(Contants.SESSION_USER,user);

                //如果需要记住密码，则写cookie
                if("true".equals(isRemPwd)){
                    Cookie c1 = new Cookie("loginAct", user.getLoginAct());
                    Cookie c2 = new Cookie("loginPwd", user.getLoginPwd());
                    c1.setMaxAge(10*24*60*60);
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    response.addCookie(c2);
                }else{
                    //把没有过期的cookie删除
                    Cookie c1 = new Cookie("loginAct","随意填写，因为立刻被删除");
                    Cookie c2 = new Cookie("loginPwd","随意填写，因为立刻被删除");
                    c1.setMaxAge(0);
                    c2.setMaxAge(0);
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
>>>>>>> 哈哈哈哈哈
            }
        }
        return returnObject;
    }
<<<<<<< HEAD
=======

    @RequestMapping("/settings/qx/user/logout.do")
    public String logOut(HttpServletResponse response, HttpSession session){
        //清空cookie
        Cookie c1 = new Cookie("loginAct","随意填写，因为立刻被删除");
        Cookie c2 = new Cookie("loginPwd","随意填写，因为立刻被删除");
        c1.setMaxAge(0);
        c2.setMaxAge(0);
        response.addCookie(c1);
        response.addCookie(c2);

        //销毁session
        session.invalidate();
        //跳转到首页
        return "redirect:/";//借助springmvc来重定向，response.sendRedirect("/crm/");
    }
>>>>>>> 哈哈哈哈哈
}
