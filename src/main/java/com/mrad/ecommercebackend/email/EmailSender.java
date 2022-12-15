package com.mrad.ecommercebackend.email;

public interface EmailSender {

    void send(String to, String email);
}
