package com.bargadss.Propozal.Admin.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

public class CORSFilter extends OncePerRequestFilter {

	private Properties prop = new Properties();
	HttpSession session;
	String uuid=null;
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		session=request.getSession();
		System.out.println("Inside Cross Origin Resource Sharing Filter Class");
		System.out.println(request.getContentType());
		System.out.println(request.getContentLength());
		System.out.println(request.getHeader("Content-Type") + ","
				+ request.getRequestURI());
		response.setContentType("application/json");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE, HEAD, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		uuid = (String) session.getAttribute("uuid"); 
		response.setHeader("Access-Control-Allow-Headers", uuid);
		System.out.println("uuid: "+response.getHeader("Access-Control-Allow-Headers"));
		filterChain.doFilter(request, response);
		System.out.println("Out Cross Origin Resource Sharing Filter Class");

	}
}

