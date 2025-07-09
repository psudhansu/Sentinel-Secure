package com.example.Security_JWT.service;

import com.example.Security_JWT.entity.Users;
import com.example.Security_JWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optUser = userRepository.findByEmail(email);
        if(optUser.isPresent()){
            Users user = optUser.get();
            String role = user.getRole().name();
            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(role));

            return new User(user.getEmail(), user.getPassword(),authorityList);
        }else{
            throw new BadCredentialsException("User Details not found with this Username " + email );
        }
    }
}
