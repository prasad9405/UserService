package com.admin.userservice.filter;

import com.admin.userservice.exception.InvalidTokenError;
import com.admin.userservice.exception.InvalidTokenException;
import com.admin.userservice.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class  JWTFilter extends OncePerRequestFilter {
    private static final Logger log = LogManager.getLogger(JWTFilter.class);


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader=request.getHeader("Authorization");
        String username=null;
        String jwtToken=null;

        //null and format
        if(requestTokenHeader!=null &&requestTokenHeader.startsWith("Bearer ")){
            jwtToken=requestTokenHeader.substring(7);
            try{
                username=jwtUtil.extractUsername(jwtToken);


            }catch (InvalidTokenException e){
                log.error("exception throw while token validation",e.getMessage());
                InvalidTokenError tokenError=new InvalidTokenError();
                tokenError.setErrorMessage(e.getMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());

            }

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                //UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                //String username=userDetails.getUsername();
                UserDetails userDetails=userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Token is not valid");
            }

        }
        filterChain.doFilter(request,response);
    }

}
