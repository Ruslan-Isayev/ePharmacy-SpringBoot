package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.request.ReqLogin;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespStatus;
import com.project.epharmacy.dto.response.RespToken;
import com.project.epharmacy.dto.response.RespUser;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.entity.User;
import com.project.epharmacy.entity.UserToken;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.UserRepository;
import com.project.epharmacy.repository.UserTokenRepository;
import com.project.epharmacy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    @Override
    public Response<RespUser> login(ReqLogin reqLogin) {
        Response<RespUser> response = new Response<>();
        RespUser respUser = new RespUser();
        try {
            String username = reqLogin.getUsername();
            String password = reqLogin.getPassword();
            if (username == null || password == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            User user = userRepository.findUserByUsernameAndPasswordAndActive(username, password, EnumAvavilableStatus.ACTIVE.value);
            if (user == null) {
                throw new MyException(ExceptionConstants.USER_NOT_FOUND, "User not found");
            }
            String token = UUID.randomUUID().toString();
            UserToken userToken = UserToken.builder()
                    .user(user)
                    .token(token)
                    .build();
            userTokenRepository.save(userToken);
            respUser.setUsername(username);
            respUser.setFullName(user.getFullName());
            respUser.setRespToken(new RespToken(user.getId(), token));
            response.setT(respUser);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Response logout(ReqToken reqToken) {
        return null;
    }
}
