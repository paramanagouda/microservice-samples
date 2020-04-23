package com.pc.msa.oauth2.service.service.impl;

import com.pc.msa.oauth2.service.model.AppUser;
import com.pc.msa.oauth2.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    // hard coding the users. All passwords must be encoded.
    final List<AppUser> users = Arrays.asList(
            new AppUser(1, "patil", passwordEncoder.encode("12345"), "USER"),
            new AppUser(2, "admin", passwordEncoder.encode("12345"), "ADMIN")
    );

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        for (AppUser user : users) {
            if (user.getUsername().equals(username)) {

                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
                // And used by auth manager to verify and check user authentication.
                return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
            }
        }
        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public AppUser save(AppUser user) {
        users.add(user);
        return user;
    }

    public List<AppUser> findAll() {
        return users;
    }

    @Override
    public void delete(long id) {
        users.removeIf(user -> id == user.getId());
    }
}
