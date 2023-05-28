package com.project.epharmacy.controller;

import com.project.epharmacy.dto.request.ReqLogin;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespUser;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Response<RespUser> login(@RequestBody ReqLogin reqLogin) {
        return userService.login(reqLogin);
    }

    @PostMapping("/logout")
    public Response logout(@RequestBody ReqToken reqToken) {
        return userService.logout(reqToken);
    }
}
