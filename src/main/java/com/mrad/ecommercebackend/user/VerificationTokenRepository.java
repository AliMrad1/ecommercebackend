package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken,Long> {
}
