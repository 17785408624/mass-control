package com.example.common.interceptor;

import com.example.config.LonginConf;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.user.UserEntity;
import net.sf.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        if (handler instanceof HandlerMethod) {
//            NeedLogin needLogin = ((HandlerMethod) handler).getMethodAnnotation(NeedLogin.class);
//            if (null == needLogin) {
//                needLogin = ((HandlerMethod) handler).getMethod().getDeclaringClass()
//                        .getAnnotation(NeedLogin.class);
//            }
//            // 有登录验证注解，则校验登录
//            if (null != needLogin) {
//                UserInfoLoginEntity userInfoLoginEntity = (UserInfoLoginEntity) request.getSession()
//                        .getAttribute("curUserContext");
//                //如果session中没有，表示没登录
//                if (null == userInfoLoginEntity) {
//                    response.setCharacterEncoding("UTF-8");
//                    response.getWriter().write("未登录！");
//                    return false;
//                }
//            }
//
//        }

        UserEntity userInfoLoginEntity = (UserEntity) request.getSession()
                .getAttribute(LonginConf.LONGIN_SESSION_KEY);
        //如果session中没有，表示没登录
        if (null == userInfoLoginEntity&&response.getStatus()==200) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
            vcp.setMessage("未登录");
            vcp.setState(2);
            //response.setHeader(LonginConf.LONGIN_TOKEN_NAME, String.valueOf(LonginConf.LOGIN_NOT));
            //response.getWriter().write("未登录！");
            PrintWriter out = response.getWriter();//获取响应浏览器打印输出流
            JSONObject jSONObject=JSONObject.fromObject(vcp);//将返回结果转换为json
            out.print(jSONObject);//输出响应数据
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
