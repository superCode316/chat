package hdu.homework.chat.utils;

import hdu.homework.chat.annotations.FriendCheck;
import hdu.homework.chat.annotations.GroupCheck;
import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.modules.groups.service.GroupService;
import hdu.homework.chat.modules.user.service.FriendService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by 钱曹宇@supercode on 3/16/2020
 */
@Component
public class GroupCheckInterceptor implements HandlerInterceptor {
    private GroupService service;

    public GroupCheckInterceptor(GroupService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        GroupCheck friendCheck = method.getMethodAnnotation(GroupCheck.class);
        if (friendCheck != null) {
            String _gid = request.getParameter("gid");
            if (_gid == null|| !StringUtils.isDigit(_gid)) {
                response.sendError(400);
                return false;
            }
            int gid = Integer.parseInt(_gid);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if (service.getGroupsByUserName(username).stream().noneMatch(group -> group.getGId().equals(gid))) {
                response.sendError(403, "你不在该组里");
                return false;
            }
            return true;

        }
        return true;
    }

}
