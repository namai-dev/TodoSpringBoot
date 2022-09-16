package com.example.practice.SecurityConfig.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.practice.AppUser.AppUser;
import com.example.practice.AppUser.AppUserService;
import com.example.practice.AppUser.Hey;
import com.example.practice.AppUser.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor

public class CustomAuthentication extends UsernamePasswordAuthenticationFilter {
    public final AuthenticationManager manager;
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        return manager.authenticate(usernamePasswordAuthenticationToken);

    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User appUser = (User) authResult.getPrincipal();
      //   AppUserService service = null;
        Algorithm algorithm = Algorithm.HMAC256("secrete".getBytes());
        String token ="Bearer "+ JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("authorities", appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(algorithm);
        String refreshToken = "Bearer "+ JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                .withClaim("authorities", appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        //hey.saveToken(appUser.getUsername(), token, refreshToken);
        
      //   private String generateToken(Map<String, Object> claims, String subject) {
       // final long now = System.currentTimeMillis();
      //  return Jwts.builder()
              //  .setClaims(claims)
                //.setSubject(subject)
                //.setIssuedAt(new Date(now))
                //.setExpiration(new Date(now + JWT_TOKEN_VALIDITY * 1000))
                //.signWith(SignatureAlgorithm.HS512, secret).compact();
   // }


        response.setHeader("AccessToken", token);
        response.setHeader("RefreshToken", refreshToken);

    }
}
