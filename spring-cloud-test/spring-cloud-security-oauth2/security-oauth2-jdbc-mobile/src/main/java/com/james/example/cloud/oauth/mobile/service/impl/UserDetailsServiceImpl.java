package com.james.example.cloud.oauth.mobile.service.impl;


import com.james.example.cloud.oauth.domain.UpmsMenu;
import com.james.example.cloud.oauth.domain.UpmsUser;
import com.james.example.cloud.oauth.service.UpmsMenuService;
import com.james.example.cloud.oauth.service.UpmsUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 2020/3/7.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UpmsUserService upmsUserService;

    @Resource
    private UpmsMenuService upmsMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UpmsUser upmsUser = upmsUserService.selectByName(username);
        if(null == upmsUser){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        List<UpmsMenu> upmsMenus = upmsMenuService.selectByUserId(upmsUser.getId());
        upmsMenus.forEach(upmsMenu -> {
            if(null != upmsMenu.getPermissions() && !"".equals(upmsMenu.getPermissions())){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(upmsMenu.getPermissions());
                grantedAuthorities.add(grantedAuthority);
            }
        });
        return new User(upmsUser.getUsername(), upmsUser.getPassword(), grantedAuthorities);
    }
}
