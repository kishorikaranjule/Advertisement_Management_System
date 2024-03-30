package com.adpoint.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.adpoint.service.CheckAgency;







public class AgencyLoginFilter implements Filter {


	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		
		
		Cookie ck[] =req.getCookies();
		
		String  email=null;
		String  pwd=null;
		
		
		if(ck != null){
			for(Cookie cookie : ck){
				String cn=cookie.getName();
			    
			     if(cn.contentEquals("Aemail")) {
			    	  email=cookie.getValue();
			     }
			     else if(cn.contentEquals("Apwd")) { 
			    	 pwd=cookie.getValue();
			     }
			     else {}
			}
			if(email != null) {
				
				CheckAgency cc=new CheckAgency();
				if(cc.check(email, pwd)) {
					String name =cc.getName();
					request.setAttribute("name",name);
					RequestDispatcher rd = request.getRequestDispatcher("/AgencyProfile.jsp");
					rd.forward(request, response);
				}
				else{
					chain.doFilter(request, response);
				}
			}
			else {
				chain.doFilter(request, response);
			}
			
		}
		else{
			chain.doFilter(request, response);
		}
	}
		

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	public void destroy() {
		
	}



}
