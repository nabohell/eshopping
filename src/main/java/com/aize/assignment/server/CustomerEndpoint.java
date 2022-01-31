package com.aize.assignment.server;

import com.aize.assignment.models.Customer;
import com.aize.assignment.servcies.CustomerService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class CustomerEndpoint {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomerService customerService;

    protected Customer getUser(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                return customerService.getCustomerByUserName(username);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }
        return null;
    }
}
