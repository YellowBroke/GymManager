package com.scut.GymManager.controller;

import com.scut.GymManager.dto.JwtResponse;
import com.scut.GymManager.entity.VipInfo;
import com.scut.GymManager.mapper.UserBasicMapper;
import com.scut.GymManager.mapper.VipInfoMapper;
import com.scut.GymManager.utility.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Authorization模块（JWT）
 * create by YellowBroke on 2020年6月8日 15点59分
 */
@Api(value = "认证接口", tags = "认证接口")
@RestController
public class AuthorizationController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserBasicMapper userBasicMapper;

    @Resource
    private VipInfoMapper vipInfoMapper;

    @Resource
    private JwtUtil jwtUtil;

    @ApiOperation("登录认证")
    @RequestMapping(value = "/auth/{username}/{password}", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> auth(@PathVariable("username") String username,
                                            @PathVariable("password") String password) {
        // authenticationManager最终调用的是JWTUserDetailsService中的loadUserByUsername
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        Map<String, Object> claims = new HashMap<>();
        //获取uid
        String uid = userBasicMapper.getUserIdByName(username);

        VipInfo vip = vipInfoMapper.selectById(uid);
        //判断身份
        if (vip != null)
            claims.put("identity","Vip");
        else
            claims.put("identity", "Coach");

        claims.put("uid", userBasicMapper.getUserIdByName(username));

        // 生成Token并返回
        String jwt = jwtUtil.generateToken(username, claims);
        return ResponseEntity.ok(new JwtResponse(jwt));

    }

}
