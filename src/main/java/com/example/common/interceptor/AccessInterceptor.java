package com.example.common.interceptor;

import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.user.UserEntity;
import com.example.service.UserService;
import com.example.service.vice.LoginVice;
import com.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 访问拦截
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {
    @Autowired
    LoginVice loginVice;
    @Autowired
    UserService userService;
    Boolean interceptLogin=false;

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
        if (response.getStatus() == 200&&interceptLogin) {//请求正常
            if (!loginVice.isLogin(request.getSession())) {//判断用户是否登录
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.setCharacterEncoding("utf-8");
                VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
                vcp.setMessage("未登录");
                vcp.setDialogue(LoginVice.LOGIN_NOT);
                //response.setHeader(LonginConf.LONGIN_TOKEN_NAME, String.valueOf(LonginConf.LOGIN_NOT));
                //response.getWriter().write("未登录！");
                ResponseUtil.responseJson(response, vcp);//向前端返回带有json参数的请求相应
                return false;
            }
            //判断登录信息和当前的用户信息是否同步 不同步则将信息同步
            loginVice.isLoginInfoSynchroniza(request.getSession(), true);

        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
