package com.project.epharmacy.repository;

import com.project.epharmacy.entity.User;
import com.project.epharmacy.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    UserToken findUserTokenByUserAndTokenAndActive(User user, String token, Integer active);
}
