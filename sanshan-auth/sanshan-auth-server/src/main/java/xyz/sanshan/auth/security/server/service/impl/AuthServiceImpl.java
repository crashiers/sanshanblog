package xyz.sanshan.auth.security.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.sanshan.auth.security.common.util.jwt.JWTInfo;
import xyz.sanshan.auth.security.server.feign.IUserService;
import xyz.sanshan.auth.security.server.service.AuthService;
import xyz.sanshan.auth.security.server.util.user.JwtTokenUtil;
import xyz.sanshan.common.vo.ResponseMsgVO;
import xyz.sanshan.main.api.vo.user.UserInfo;

import java.util.Date;

@Service
public class AuthServiceImpl  implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;

    @Value("${jwt.token-header}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }


    @Override
    public String login(String username, String password) throws Exception {
        ResponseMsgVO<UserInfo> msgVO = userService.validate(username,password);
        UserInfo info = msgVO.getData();
        if (info == null) {
            return null;
        }
        String token = "";
        token = jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(),info.get_id(),new Date()));
        return token;
    }

    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    @Override
    public String refresh(String oldToken) {
        //TODO: 2018:3:25 刷新
        return null;
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public Boolean invalid(String token) {
        //TODO: 2018:3:25 注销
        return null;
    }
}
