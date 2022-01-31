package com.aize.assignment.server;

import com.aize.assignment.erros.UnauthorizedRequestException;
import com.aize.assignment.models.Admin;
import com.aize.assignment.models.Customer;
import com.aize.assignment.models.User;
import com.aize.assignment.servcies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor extends WebRequestHandlerInterceptorAdapter {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    public SessionInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = ((HandlerMethod) handler);

        Object handlerBean = handlerMethod.getBean();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof UsernamePasswordAuthenticationToken){
            User user = (User) authentication.getPrincipal();

            if (handlerBean instanceof AdminEndpoint && user instanceof Admin) {
                // check more authorization settings if needed
                return true;
            } else if (handlerBean instanceof CustomerEndpoint && user instanceof Customer) {
                return true;
            }
        } else if(handlerBean instanceof PublicEndPoint){
            return true;
        }

        throw new UnauthorizedRequestException();
    }
}