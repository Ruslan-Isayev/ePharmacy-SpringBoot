package com.project.epharmacy.util;

import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.entity.User;
import com.project.epharmacy.entity.UserToken;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.UserRepository;
import com.project.epharmacy.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {

    private final UserRepository userRepository;

    private final UserTokenRepository userTokenRepository;

    public UserToken checkToken(ReqToken reqToken) {
        String token = reqToken.getToken();
        Long userId = reqToken.getUserId();
        if (token == null || userId == null) {
            throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAvavilableStatus.ACTIVE.value);
        if (user == null) {
            throw new MyException(ExceptionConstants.USER_NOT_FOUND, "User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndTokenAndActive(user, token, EnumAvavilableStatus.ACTIVE.value);
        if (userToken == null) {
            throw new MyException(ExceptionConstants.INVALID_TOKEN, "Invalid token");
        }
        return userToken;
    }
}
