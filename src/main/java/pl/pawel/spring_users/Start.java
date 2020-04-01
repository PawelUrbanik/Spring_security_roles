package pl.pawel.spring_users;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pawel.spring_users.model.User;
import pl.pawel.spring_users.repository.UserRepository;

@Configuration
public class Start {


    public Start(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername("Pawel");
        user.setPassword(passwordEncoder.encode("pawel"));
        user.setRole("ROLE_ADMIN");
        user.setEnabled(true);
        userRepository.save(user);

        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("pass"));
        user1.setRole("ROLE_USER");
        user1.setEnabled(true);
        userRepository.save(user1);
    }


}
