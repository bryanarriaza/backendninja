package com.barriaza.backendninja.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.barriaza.backendninja.repository.LogRepository;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;

	// SE EJECUTA ANTES DE ENTRAR EN EL METODO DE CONTROLADOR
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	// SE EJECUTA ANTES DE RETORNAR LA VISTA EN EL NAVEGADOR
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		String url = request.getRequestURL().toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (null != auth && auth.isAuthenticated()) {
			username = auth.getName();
		}
		logRepository.save(
				new com.barriaza.backendninja.entity.Log(new Date(), auth.getDetails().toString(), username, url));
		LOG.info("--REQUEST URL: '" + url + "' -- TOTAL TIME: '" + (System.currentTimeMillis() - startTime) + "'ms");
	}

}
