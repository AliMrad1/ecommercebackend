package com.mrad.ecommercebackend.user.dao;

import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenDao {

  int insertToken(VerificationToken token);
  Optional<VerificationToken> findByToken(String token);
  void deleteByUser(UserModel user);
}
