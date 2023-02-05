package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserModel> selectUsers();
    int insertUser(UserModel user);
    int deleteUser(int id);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);

    int update_verifiedUser(UserModel userModel);

    Optional<UserModel> findById(Long id);
}
