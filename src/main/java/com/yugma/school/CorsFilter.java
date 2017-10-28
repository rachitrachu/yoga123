package com.yugma.school;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest request = (HttpServletRequest) req;
     
        /*if(request.getHeader("Access-Control-Allow-Origin")==null){
        	
        }else*/
        	
        
        String path = request.getRequestURI();
        //System.out.println("path : "+path);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,isWeb, Accept, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        if(path.startsWith("/management/nxtlife-websocket") || path.startsWith("/parent/nxtlife-websocket")){
        	
        }else{
        	response.setHeader("Access-Control-Allow-Origin", "*");
        }
        if (!path.startsWith("/_ah/") && request.getMethod().equals("OPTIONS")) {
        	
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}
