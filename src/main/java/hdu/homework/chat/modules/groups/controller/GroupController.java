package hdu.homework.chat.modules.groups.controller;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.entity.bean.request.AddGroup;
import hdu.homework.chat.entity.bean.request.JoinGroup;
import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.entity.bean.response.swagger.Forbidden;
import hdu.homework.chat.entity.bean.response.swagger.GroupResponse;
import hdu.homework.chat.entity.bean.response.swagger.GroupsResponse;
import hdu.homework.chat.entity.bean.response.swagger.SuccessResponse;
import hdu.homework.chat.modules.groups.service.GroupService;
import hdu.homework.chat.modules.user.service.UserDetailsServiceImpl;
import hdu.homework.chat.utils.ConvertUtils;
import hdu.homework.chat.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@RestController
@RequestMapping("group")
@Api(tags = "群组相关接口")
public class GroupController {

    private GroupService service;
    private UserDetailsServiceImpl userService;

    public GroupController(GroupService service, UserDetailsServiceImpl userService) {
        this.service = service;
        this.userService = userService;
    }

    @ApiOperation("新增群组接口")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    public ResponseEntity<Msg<?>> add(@RequestBody AddGroup group) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Group group1 = new Group();
        ConvertUtils.convert(group1, group, Group.class, AddGroup.class);
        service.addGroup(username, group1);
        return ResultUtil.success();
    }

    @ApiOperation("查看群组信息接口")
    @ApiResponses({
            @ApiResponse(code = 200, response = GroupResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<Msg<?>> info(@RequestParam Integer id) {
        Group group = service.getGroupByGid(id);
        return ResultUtil.success(group);
    }

    @ApiOperation("加入群组接口")
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = SuccessResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    public ResponseEntity<Msg<?>> join(@RequestBody JoinGroup join) {
        Integer gid = join.getGid();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (service.getGroupByGid(gid) == null)
            return ResultUtil.error("群组不存在");
        service.joinGroup(gid, username);
        return ResultUtil.success();
    }

    @ApiOperation("获取用户的群组接口")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, response = GroupsResponse.class, message = "请求成功"),
            @ApiResponse(code = 403, response = Forbidden.class, message = "请求失败"),
            @ApiResponse(code = 401, response = Forbidden.class, message = "请求失败")
    })
    public ResponseEntity<Msg<?>> getAllGroups() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Group> groups = service.getGroupsByUserName(username);
        return ResultUtil.success(groups);
    }

}
