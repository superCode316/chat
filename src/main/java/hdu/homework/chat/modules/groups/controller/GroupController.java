package hdu.homework.chat.modules.groups.controller;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.entity.bean.request.AddGroup;
import hdu.homework.chat.entity.bean.request.JoinGroup;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.modules.groups.service.GroupService;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.utils.ConvertUtils;
import hdu.homework.chat.utils.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("group")
public class GroupController {

    private GroupService service;
    private UserDetailsServiceImpl userService;

    public GroupController(GroupService service, UserDetailsServiceImpl userService) {
        this.service = service;
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> add(@RequestBody AddGroup group) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Group group1 = new Group();
        ConvertUtils.convert(group1, group, Group.class, AddGroup.class);
        service.addGroup(username, group1);
        return ResultUtil.success();
    }

    @RequestMapping("/info")
    public ResponseEntity<Msg<?>> info(@RequestParam Integer id) {
        Group group = service.getGroupByGid(id);
        return ResultUtil.success(group);
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> join(Integer gid) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (service.getGroupByGid(gid) == null)
            return ResultUtil.error("群组不存在");
        service.joinGroup(gid, username);
        return ResultUtil.success();
    }

}
