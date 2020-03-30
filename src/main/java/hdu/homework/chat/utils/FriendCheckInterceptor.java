package hdu.homework.chat.utils;

import hdu.homework.chat.annotations.FriendCheck;
//import hdu.homework.chat.modules.user.service.FriendService;
import hdu.homework.chat.modules.user.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

/**
 * created by 钱曹宇@supercode on 3/16/2020
 */
@Component
public class FriendCheckInterceptor implements HandlerInterceptor {
//    private FriendService service;

    public FriendCheckInterceptor() {
//        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod))
//            return true;
//        HandlerMethod method = (HandlerMethod) handler;
//        FriendCheck friendCheck = method.getMethodAnnotation(FriendCheck.class);
//        if (friendCheck != null) {
//            String param = friendCheck.targetName();
//            int fid;
//            try {
//                fid = Integer.parseInt(request.getParameter(param));
//            } catch (NumberFormatException e) {
//                response.sendError(400, "参数错误");
//                return false;
//            }
//            if (fid < 0) return true;
//            String username = SecurityContextHolder.getContext().getAuthentication().getName();
//            if (!service.isFriend(username, fid)) {
//                response.sendError(403, "你和对方不是朋友");
//                return false;
//            }
//        }
        return true;
    }
}
