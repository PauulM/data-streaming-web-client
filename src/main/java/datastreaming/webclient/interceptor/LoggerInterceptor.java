package datastreaming.webclient.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder infoBuilder = new StringBuilder();
        infoBuilder.append("[" + request.getMethod() + "]");
        if(request.getRequestURI() != null)
            infoBuilder.append(request.getRequestURI());
        if(request.getQueryString() != null)
            infoBuilder.append("?" + request.getQueryString());
        log.info(infoBuilder.toString());
        return true;
    }
}
