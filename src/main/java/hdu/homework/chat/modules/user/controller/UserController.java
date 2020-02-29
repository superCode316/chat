package hdu.homework.chat.modules.user.controller;

import hdu.homework.chat.entity.bean.Msg;
import hdu.homework.chat.utils.ResultUtil;
import hdu.homework.chat.entity.bean.request.UserPost;
import hdu.homework.chat.modules.user.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthenticationService service;

    public UserController(AuthenticationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> postLogin(@RequestBody UserPost user) {
        boolean loginSuccess = service.validUser(user.getUsername(), user.getPassword());
        if (!loginSuccess){
            return ResultUtil.result(HttpStatus.FORBIDDEN, 1001,  "yonghuminghuomimachucuo");
        } else {
            return ResultUtil.success();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Msg<?>> postRegister(@RequestBody UserPost user) {

        String checkRegister = service.checkRegister(user);

        if (checkRegister != null)
            return ResultUtil.error(HttpStatus.BAD_REQUEST, checkRegister);

        service.addUser(user);

        return ResultUtil.success();
    }
}
