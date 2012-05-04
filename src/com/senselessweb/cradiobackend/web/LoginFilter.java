package com.senselessweb.cradiobackend.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginFilter implements Filter
{

    private final UserService userService = UserServiceFactory.getUserService();


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (((HttpServletRequest) request).getRequestURI().contains("_ah/login"))
			chain.doFilter(request, response);
		
		else if (this.userService.getCurrentUser() == null)
			((HttpServletResponse) response).sendRedirect(this.userService.createLoginURL(
					((HttpServletRequest) request).getRequestURI()));
		
		else chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
