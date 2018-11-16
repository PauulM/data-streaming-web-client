package datastreaming.webclient.interceptor;

import datastreaming.webclient.misc.Message;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null || session.getAttribute("token") == null){
            addFlashMessage(new Message("Please sign in before continue", Message.Type.ERROR), request, response);
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    private void addFlashMessage(Message message, HttpServletRequest request, HttpServletResponse response){
        FlashMap flashMap = new FlashMap();
        flashMap.put("message", message);
        FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
        flashMapManager.saveOutputFlashMap(flashMap, request, response);
    }
}
