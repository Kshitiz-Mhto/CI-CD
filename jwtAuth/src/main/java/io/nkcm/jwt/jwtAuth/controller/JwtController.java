package io.nkcm.jwt.jwtAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.nkcm.jwt.jwtAuth.jwtUtil.JwtUtil;
import io.nkcm.jwt.jwtAuth.model.JwtRequest;
import io.nkcm.jwt.jwtAuth.model.JwtResponse;
import io.nkcm.jwt.jwtAuth.services.CustomUserDetailService;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private CustomUserDetailService customUserDetailService;


    @RequestMapping(value="/publicToken", method=RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        System.out.println(jwtRequest);
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        // only execute if user is authenticated using above code
        UserDetails userDetials = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetials);
        System.out.println("token: "+ token);

        // token must be in json format and return it
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
}
