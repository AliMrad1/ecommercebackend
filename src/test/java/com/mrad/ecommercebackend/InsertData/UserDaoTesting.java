package com.mrad.ecommercebackend.InsertData;

import com.mrad.ecommercebackend.user.UserDao;
import com.mrad.ecommercebackend.user.VerificationTokenDao;
import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;


public class UserDaoTesting {


    private VerificationTokenDao verificationTokenDao;
    private UserDao userDao;

    public UserDaoTesting(VerificationTokenDao verificationTokenDao, UserDao userDao) {
        this.verificationTokenDao = verificationTokenDao;
        this.userDao = userDao;
    }

    @Test
    public void insertData(){

        UserModel user = new UserModel("alimrad123","alimrad@gmail.com","ali", "mrad","017943");
        userDao.insertUser(user);

        VerificationToken token = new VerificationToken();
        token.setUser(user);
        token.setCreatedTimeStamp(new Timestamp((System.currentTimeMillis())));
        token.setToken("lkrlkerlkjrwlkrjewlrkjwlkrjerlkjwe.rewrewrew.rw.rer.wer.erw");
        verificationTokenDao.insertToken(
                token
        );

        Assertions.assertThat(userDao.insertUser(user)).isEqualTo(1);
    }
}
