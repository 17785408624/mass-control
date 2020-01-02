package com.example.service.vice;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.user.UserEntity;
import com.example.entity.user.UserInfoLoginSession;
import com.example.mapper.UserMapper;
import com.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
@Component
public class LoginVice {
    @Autowired
    UserMapper userMapper;
    public static String LONGIN_SESSION_KEY="LOGINKEY";//登录SESSION键名
    public static String LONGIN_TOKEN_NAME="LONGINSTATE";//登录token名
    public static int LOGIN_VALID_TIME=60*30*1000;//登录有效时间
    public static int LOGIN_NOT=1;//未登录状态
    public static int LOGIN_RIGHT=0;//正常登录
    public static int LOGIN_OVERTIME=2;//超时
    public static int LOGIN_REPETITION=3;//重复

    //public static int LOGIN_SAVE_TIME;//登录保存时间
//    private String loginSessionKey;////登录SESSION键名
//    private  String longinTokenName;//登录token名
//    private  int loginValidTime;//登录有效时间
//    private  int loginNot;//未登录状态
//    private  int loginRight;//已登录
//    private  int loginOvertime;//超时
//    private  int loginRepetition;//重复



    /**
     * 保存用户登录信息
     * @param ue 用户信息
     * @param session
     * @return
     */
    public boolean saveLoginInfo(UserEntity ue, HttpSession session){
        session.setAttribute(LONGIN_SESSION_KEY, ue);//将登录信息保存session
        session.setMaxInactiveInterval(LOGIN_VALID_TIME);//登录保存时间
        return true;
    };
    /**
     * 将登录信息session清空
     * @param session
     * @return
     */
    public boolean cleanLoginInfo(HttpSession session){

        session.setAttribute(
                LONGIN_SESSION_KEY, null);//将登录信息session清空
        return true;
    };

    /**
     * 获取用户登录信息
     * @param session
     * @return
     */
    public UserEntity getLoginInfo(HttpSession session) throws LoginException {
        Object o=session.getAttribute(LONGIN_SESSION_KEY);
        UserEntity ue = null;
        if(o!=null&&o instanceof UserEntity){
            ue=(UserEntity)o;
        }
        /**else{
            throw new LoginException("登录信息错误");
        }**/
     return ue;
    }

    /**
     * 更新用户登录信息
     * @param ue 用户信息
     * @param session
     * @return
     */
    public boolean updateLoginInfo(UserEntity ue, HttpSession session){
        cleanLoginInfo(session);//将登录信息session清空
        saveLoginInfo(ue,session);//保存登录信息
        return true;
    }

    /**
     * 判断用户是否登录
     * @param session
     * @return true为已登录
     */
    public boolean isLogin(HttpSession session) throws LoginException {
        UserEntity userInfoLoginEntity = (UserEntity) getLoginInfo(session);
        if(userInfoLoginEntity==null){
            return false;
        }
        return true;
    };

    /**
     * 判断登录信息和当前的用户信息是否同步
     * @param session
     * @return
     */
    public boolean isLoginInfoSynchroniza(HttpSession session) throws LoginException {//判断登录信息和当前的用户信息是否同步
        UserEntity loginUe=null;
        UserEntity ue=null;
        loginUe=getLoginInfo(session);
        ue=userMapper.selectUserEntityByUId(loginUe.getUser_id());
        return false;
    }

    /**
     * 判断登录信息和当前的用户信息是否同步
     * @param session
     * @param updateInfoLogin 是否更新用户登录信息
     * @return
     * @throws LoginException
     */
    public boolean isLoginInfoSynchroniza(HttpSession session,Boolean updateInfoLogin) throws LoginException {//判断登录信息和当前的用户信息是否同步
        UserEntity loginUe=null;//登录的用户信息
        UserEntity ue=null;
        loginUe=getLoginInfo(session);//保存的用户信息
        ue=userMapper.selectUserEntityByUId(loginUe.getUser_id());
        if(PublicUtil.isEntityEquality(ue,loginUe)){
            return true;
        }else{;//判断登录信息和当前保存的用户信息是否同步
            if(updateInfoLogin){
                updateLoginInfo(ue,session);//更新用户信息
            }
            return false;
        }
    }




}
