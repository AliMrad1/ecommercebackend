package com.mrad.ecommercebackend.user;

import com.mrad.ecommercebackend.email.EmailHtmlCustom;
import com.mrad.ecommercebackend.email.EmailService;
import com.mrad.ecommercebackend.user.exception.UserExistException;
import com.mrad.ecommercebackend.user.exception.UserNotVerifiedException;
import com.mrad.ecommercebackend.user.model.LoginBody;
import com.mrad.ecommercebackend.user.model.RegistrationBody;
import com.mrad.ecommercebackend.user.model.UserModel;
import com.mrad.ecommercebackend.user.model.VerificationToken;
import com.mrad.ecommercebackend.user.security.JWTService;
import com.mrad.ecommercebackend.user.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserDao userRepository;
    private final JWTService jwtService;
    private final EmailService emailService;
    private VerificationTokenRepository verificationTokenRepository;

    public UserModel register(RegistrationBody body) throws UserExistException {

        var encryptPassword = bCryptPasswordEncoder.encryptPassword(body.password());

        UserModel user = new UserModel();

        user.setUsername(body.username());
        user.setEmail(body.email());


        user.setFirst_name(body.first_name());
        user.setLast_name(body.last_name());

        // encrypt password with BCrypt
        user.setPassword(encryptPassword);

        Predicate<String> userExistByUsername = username -> userRepository.findByUsername(username).isPresent();
        Predicate<String> userExistByEmail = email -> userRepository.findByEmail(email).isPresent();

        if(userExistByUsername.test(user.getUsername()) || userExistByEmail.test(user.getEmail())){
            throw new UserExistException("user already exist");
        }

        VerificationToken verificationToken = createVerificationToken(user);

        var link = "http://localhost:8080/auth/confirm?token=" + verificationToken.getToken();
        emailService.send(verificationToken.getUser().getEmail(), EmailHtmlCustom.buildEmail(body.first_name(),link));

        userRepository.insertUser(user);
        verificationTokenRepository.save(verificationToken);

        return user;
    }

    public String loginUser(LoginBody body) throws UserNotVerifiedException {
        Optional<UserModel> username = userRepository.findByUsername(body.username());
        if(username.isPresent()){
            UserModel user = username.get();
            if(bCryptPasswordEncoder.verifyPassword(body.password(), user.getPassword())){
              if(user.isEmailVerified()){
                return jwtService.generateJWT(user);
              }
              else{
                List<VerificationToken> tokens = user.getVerificationTokens();
                var resend = tokens.size() == 0 ||
                  tokens.get(0).getCreatedTimeStamp()
                    .before(new Timestamp(System.currentTimeMillis() - (60 * 60 *1000)));
                if(resend){
                  VerificationToken verificationToken = createVerificationToken(user);
                  verificationTokenRepository.save(verificationToken);
                  var link = "http://localhost:8080/auth/confirm?token=" + verificationToken.getToken();
                  emailService.send(
                    verificationToken.getUser().getEmail(),
                    EmailHtmlCustom.buildEmail(body.username(),link)
                  );

                }
                throw new UserNotVerifiedException(resend);
              }
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

    @Transactional
    public boolean verifyUser(String token){
      Optional<VerificationToken> opToken =
        verificationTokenRepository.findByToken(token);
      if(opToken.isPresent()){
        VerificationToken verificationToken = opToken.get();
        UserModel user = verificationToken.getUser();
        if(!user.isEmailVerified()){
          user.setEmailVerified(true);
          userRepository.insertUser(user);
          verificationTokenRepository.deleteByUser(user);
          return true;
        }
      }

      return false;
    }
}
