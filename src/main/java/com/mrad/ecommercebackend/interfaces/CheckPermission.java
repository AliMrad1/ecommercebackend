package com.mrad.ecommercebackend.interfaces;

import com.mrad.ecommercebackend.user.model.UserModel;

import java.util.Objects;

public class CheckPermission {

    public static boolean userHasPermission(UserModel user, Long id) {
        return Objects.equals(user.getId(), id);
    }

}
