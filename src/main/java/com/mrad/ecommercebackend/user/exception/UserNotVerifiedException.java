package com.mrad.ecommercebackend.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserNotVerifiedException extends Exception {

    private boolean newEmailSend;

    public boolean isNewEmailSend(){
      return newEmailSend;
    }
}
