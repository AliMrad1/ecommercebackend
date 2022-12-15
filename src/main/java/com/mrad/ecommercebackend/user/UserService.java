package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.email.EmailHtmlCustom;
import com.mrad.ecommercebackend.email.EmailService;
import com.mrad.ecommercebackend.user.exception.UserExistException;
import com.mrad.ecommercebackend.user.model.LoginBody;
import com.mrad.ecommercebackend.user.model.RegistrationBody;
import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;
import com.mrad.ecommercebackend.user.security.JWTService;
import com.mrad.ecommercebackend.user.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private JWTService jwtService;
    private EmailService emailService;

    private VerificationTokenRepository verificationTokenRepository;

    public UserModel register(RegistrationBody body) throws UserExistException {


        UserModel user = new UserModel();

        user.setUsername(body.username());
        user.setEmail(body.email());


        user.setFirst_name(body.first_name());
        user.setLast_name(body.last_name());

        // encrypt password with BCrypt
        var encryptPassword = bCryptPasswordEncoder.encryptPassword(body.password());
        user.setPassword(encryptPassword);

        Predicate<String> userExistByUsername = username -> userRepository.findByUsername(username).isPresent();
        Predicate<String> userExistByEmail = email -> userRepository.findByEmail(email).isPresent();

        if(userExistByUsername.test(user.getUsername()) || userExistByEmail.test(user.getEmail())){
            throw new UserExistException("user already exist");
        }

        VerificationToken verificationToken = createVerificationToken(user);

        String link = "http://localhost:8080/auth/confirm?token=" + verificationToken.getToken();
        emailService.send(verificationToken.getUser().getEmail(), EmailHtmlCustom.buildEmail(body.first_name(),link));

        verificationTokenRepository.save(verificationToken);

        userRepository.save(user);
        return user;
    }

    public String loginUser(LoginBody body){
        Optional<UserModel> username = userRepository.findByUsername(body.username());
        if(username.isPresent()){
            UserModel user = username.get();
            if(bCryptPasswordEncoder.verifyPassword(body.password(), user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }

        return null;
    }

    public VerificationToken createVerificationToken(UserModel user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimeStamp(new Timestamp((System.currentTimeMillis())));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);

        return verificationToken;
    }
}
