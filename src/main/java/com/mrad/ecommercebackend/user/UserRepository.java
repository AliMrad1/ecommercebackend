package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.user.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<UserModel,Long> {

    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
}
