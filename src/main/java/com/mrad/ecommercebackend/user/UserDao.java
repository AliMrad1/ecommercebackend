package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserModel> selectUsers();
    int insertUser(UserModel user);
    int deleteUser(int id);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
}
