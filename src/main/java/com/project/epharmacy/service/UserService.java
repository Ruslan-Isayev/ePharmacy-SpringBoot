package com.project.epharmacy.service;

import com.project.epharmacy.dto.request.ReqLogin;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespUser;
import com.project.epharmacy.dto.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Response<RespUser> login(ReqLogin reqLogin);

    Response logout(ReqToken reqToken);
}
