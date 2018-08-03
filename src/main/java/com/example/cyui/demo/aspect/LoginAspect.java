package com.example.cyui.demo.aspect;

import com.example.cyui.demo.dto.login.LoginMessage;
import com.example.cyui.demo.entiy.Individual;
import com.example.cyui.demo.util.RedisUtils;
import com.example.cyui.demo.util.SysContextUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Aspect
@Component
@Order(0)
public class LoginAspect {
    Logger logger= LoggerFactory.getLogger(LoginAspect.class);

    /**
     * @Title: pointCutMethodAuth
     * @Description: 切入点
     *  && !execution(* com.example.cyui.demo.controller.LoginController.*.*(..))&& !execution(* com.example.cyui.demo.controller.ErrController.*.*(..))
     */
//    @Pointcut("@annotation(com.example.cyui.demo.aspect.Login)")
    @Pointcut("execution(* com.example.cyui.demo.controller..*.*(..))&& !execution(* com.example.cyui.demo.controller.LoginController.*(..))&& !execution(* com.example.cyui.demo.controller.ErrController.*(..))")
    public void pointCutMethodAuth()
    {
    }

    //拦截是否登录
    @Before(value ="pointCutMethodAuth()")
    public void doBefore() throws Throwable {

        HttpServletRequest request= SysContextUtil.getRequest();
        HttpServletResponse response=SysContextUtil.getResponse();
        HttpSession session=request.getSession(false);

        if(null!=session && null!=session.getAttribute("loginMessage")){
            LoginMessage loginMessage=(LoginMessage)session.getAttribute("loginMessage");

            if (null!=loginMessage){
                //从session中取出数据
                Individual individual=loginMessage.getIndividual();
                String tokenId=loginMessage.getToken();

                //redis中获取用户登录loginToken
                String loginToken=(String) RedisUtils.get("loginToken"+individual.getId());
                //判断redis中用户登录信息是否为空,redis中用户信息是否和session相比配
               if (null!=tokenId && tokenId.equals(loginToken)){

                   return;

               }
               else {
                   //进入登录页面
                   response.sendRedirect("index.do");
               }

            }else {
                //进入登录页面
                response.sendRedirect("index.do");
            }
        }
       else {
            //进入登录页面
            response.sendRedirect("index.do");
        }
    }

}
