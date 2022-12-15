package com.mrad.ecommercebackend.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotVerifiedException extends Exception {

    private boolean newEmailSend;



}
