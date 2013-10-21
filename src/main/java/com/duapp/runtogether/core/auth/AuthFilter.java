package com.duapp.runtogether.core.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.api.Baidu;
import com.baidu.api.BaiduApiException;
import com.baidu.api.BaiduOAuthException;
import com.baidu.api.domain.User;
import com.baidu.api.store.BaiduCookieStore;
import com.baidu.api.store.BaiduStore;
import com.duapp.runtogether.account.bo.SysUser;
import com.duapp.runtogether.account.service.UserService;
import com.duapp.runtogether.core.config.SystemConfig2;

public class AuthFilter implements Filter {

	private static Logger log = Logger.getLogger(AuthFilter.class);
	
	@Autowired
	private UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String clientId = SystemConfig2.CLIENTID;
		String clientSecret = SystemConfig2.CLIENTSECRET;
		String redirectUri = buildRedirectUrl(req);
		
		BaiduStore store = new BaiduCookieStore(clientId, req, res);
        Baidu baidu = null;
        
        try {
            baidu = new Baidu(clientId, clientSecret, redirectUri, store, req);
            
            User user = baidu.getLoggedInUser();
            
            if (user != null) {
            	String username = user.getUname();
            	Long uid = user.getUid();
            	
            	SysUser sysUser = userService.findUserByUsername(username);
                
                if(sysUser == null) {
                	userService.addUserFromPassport(uid, username);
                }
            	
            	chain.doFilter(request, response);
            } else {
	            String state = baidu.getState();
	            Map<String, String> params = new HashMap<String, String>();
	            params.put("state", state);
//	            params.put("display", "mobile");
	            String authorizeUrl = baidu.getBaiduOAuth2Service().getAuthorizeUrl(params);
	            res.sendRedirect(authorizeUrl);
            }
        } catch (BaiduOAuthException e) {
            log.debug("BaiduOAuthException ", e);
        } catch (BaiduApiException e) {
            log.debug("BaiduApiException ", e);
        }
        
        return;
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	private String buildRedirectUrl(HttpServletRequest request) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(request.getScheme());
		sb.append("://");
		sb.append(request.getServerName());
		sb.append(":");
		sb.append(request.getServerPort());
		
//		String contextPath = request.getContextPath();
//		if (!StringUtils.isBlank(contextPath)) {
//			sb.append(contextPath);
//		}
//		request.getRequestURI();
		
		sb.append(request.getRequestURI());
		
		return sb.toString();
	}

}
