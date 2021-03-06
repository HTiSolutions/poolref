package com.htisolutions.poolref.security;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String nickname) throws UsernameNotFoundException {
        try {
            final User user = userDao.findByNickname(nickname);

            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + nickname);
            }

            return new org.springframework.security.core.userdetails.User(user.getNickname(), user.getHashedpassword(), getGrantedAuthorities(getPrivileges()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getPrivileges() {
        final List<String> privileges = new ArrayList<String>();
        privileges.add("ROLE_USER");
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
