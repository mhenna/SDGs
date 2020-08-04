package com.techdev.sdg.Authentication;

import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.SuperEntity;
import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.PrivateSector.PrivateSectorRepository;
import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.NGO.NGORepository;
import com.techdev.sdg.Admin.Admin;
import com.techdev.sdg.Admin.AdminRepository;
import com.techdev.sdg.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private NGORepository ngoUser;

    @Autowired
    private PrivateSectorRepository psUser;

    @Autowired
    private AdminRepository admin;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            PrivateSector user = psUser.findByName(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            List a = new ArrayList();
            a.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return Role.USER;
                }
            });
            Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
            authorities.addAll(a);
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                    authorities);
        } catch (Exception e) {
            try {
                NGO user = ngoUser.findByName(username);
                if (user == null) {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }

                List a = new ArrayList();
                a.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return Role.USER;
                    }
                });
                Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
                authorities.addAll(a);
                return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                        authorities);
            } catch (Exception e1) {
                Admin user = admin.findByName(username);
                if (user == null) {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }

                List a = new ArrayList();
                a.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return Role.ADMIN;
                    }
                });
                Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
                authorities.addAll(a);

                return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                        authorities);
            }
        }
    }

    public Map<String, Object> loadUserObject(String username) throws UsernameNotFoundException {

        Entity entity;
        Map<String, Object> entityInfo;

        try {
            entity = psUser.findByName(username);
            if (entity == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            entityInfo = entity.toMap();
            entityInfo.remove("files");
            return entityInfo;
        } catch (Exception e) {
            try {
                entity = ngoUser.findByName(username);

                if (entity == null) {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
                entityInfo = entity.toMap();
                entityInfo.remove("files");
                return entityInfo;

            } catch (Exception e1) {
                SuperEntity sEntity = admin.findByName(username);

                if (sEntity == null) {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
                entityInfo = sEntity.toMap();
                return entityInfo;
            }
        }

    }
}