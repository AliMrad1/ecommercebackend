package com.mrad.ecommercebackend.user.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    private PasswordEncoder underTest;

    @BeforeEach
    void setUp(){
        underTest = new PasswordEncoder();
    }
    @Test
    @Disabled
    void encryptPassword() {
    }

    @Test
    void canVerifyPassword() {
        //given
        String password = "01792385@Back";
        String encrypt = underTest.encryptPassword(password);

        //when
        boolean isVerified = underTest.verifyPassword(password,encrypt);

        Assertions.assertThat(isVerified).isTrue();
    }
}