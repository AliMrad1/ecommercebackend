package com.mrad.ecommercebackend.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@AllArgsConstructor
public class UserNotVerifiedException extends Exception {

    private boolean newEmailSend;

    public boolean isNewEmailSend(){
      return newEmailSend;
    }
}
