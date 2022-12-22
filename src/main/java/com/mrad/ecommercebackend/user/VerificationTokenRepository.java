package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken,Long> {

  Optional<VerificationToken> findByToken(String token);
  void deleteByUser(UserModel user);
}
