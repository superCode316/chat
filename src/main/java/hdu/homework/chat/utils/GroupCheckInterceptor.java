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
import java.util.List;

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
        if (!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod method = (HandlerMethod) handler;
        GroupCheck groupCheck = method.getMethodAnnotation(GroupCheck.class);
        if (groupCheck != null) {
            String _gid = request.getParameter("gid");
            if (_gid == null)
                _gid = request.getParameter("receiver");
            if (_gid == null|| !StringUtils.isDigit(_gid)) {
                response.sendError(400);
                return false;
            }
            int gid = Integer.parseInt(_gid);
            if (groupCheck.checkIn()) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                List<Group> groups = service.getGroupsByUserName(username);
                if (groups.stream().noneMatch(group -> group.getGId().equals(gid))) {
                    response.sendError(403, "你不在该组里");
                    return false;
                }
            }
            if (groupCheck.checkNotIn()) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                List<Group> groups = service.getGroupsByUserName(username);
                if (groups.stream().anyMatch(group -> group.getGId().equals(gid))) {
                    response.sendError(403, "你已经在该组里");
                    return false;
                }
            }
            if (groupCheck.checkExist()) {
                Group group = service.getGroupByGid(gid);
                if (group == null) {
                    response.sendError(400, "没有该群");
                    return false;
                }
            }
            return true;

        }
        return true;
    }

}
