package com.scut.GymManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.GymManager.entity.UserBasic;
import com.scut.GymManager.mapper.UserBasicMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * JWT专用验证服务
 * create by YellowBroke on 2020年6月8日 15点26分
 */
@Component
public class JWTUserDetailsService implements UserDetailsService{

    @Resource
    private UserBasicMapper userBasicMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserBasic> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserBasic userBasic = userBasicMapper.selectOne(wrapper);

        if(userBasic == null)
            throw new UsernameNotFoundException("用户不存在");

        Collection<GrantedAuthority> collection = new ArrayList<>();

        return (new User(username, userBasic.getPassword(), collection));

    }

}
