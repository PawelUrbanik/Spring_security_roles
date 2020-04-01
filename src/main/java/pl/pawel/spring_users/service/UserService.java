package pl.pawel.spring_users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pawel.spring_users.model.Token;
import pl.pawel.spring_users.model.User;
import pl.pawel.spring_users.repository.TokenRepository;
import pl.pawel.spring_users.repository.UserRepository;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private TokenRepository tokenRepository;
    private MailService mailService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder, TokenRepository tokenRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public void addUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(false);
        userRepository.save(user);
        sendToken(user);
        
    }

    private void sendToken(User user) {
        String tokenFromUUID = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenFromUUID);
        token.setUser(user);
        tokenRepository.save(token);
        String url = "http://localhost:8080/token?value=" + tokenFromUUID;
        try {
            mailService.sendSimpleMessage(user.getUsername(), "Potwierdzenie rezerwacji", url);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public User changeUserStatus(String value) {
        Token token = tokenRepository.findByValue(value);
        User user = token.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }
}
