package io.nkcm.jwt.jwtAuth.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import io.nkcm.jwt.jwtAuth.model.UserModel;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // for extracting user details
        
        final UserModel userModel = new UserModel("Kshitiz", "12345");

        if (username.equals(userModel.getUsername())){
            return new User(userModel.getUsername(),userModel.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found!!");
        }
    }
    
    
}
