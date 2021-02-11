package com.project.security;

import com.project.config.Constants;
import com.project.entity.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RestTemplate restTemplate;

    public UserDetailsServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = restTemplate.getForObject(Constants.USER_URL + "/username/" + username, User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exist: " + username);
        }
        return new UserDetailsImpl(user);
    }
}
