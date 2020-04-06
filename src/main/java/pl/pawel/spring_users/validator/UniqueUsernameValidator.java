package pl.pawel.spring_users.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.pawel.spring_users.model.User;
import pl.pawel.spring_users.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

   @Autowired
   private UserRepository userRepository;

   public void initialize(UniqueUsername constraint) {
   }

   public boolean isValid(String username, ConstraintValidatorContext context) {
      if (userRepository == null)
      {
         return true;
      }

      Optional<User> user = userRepository.findUserByUsername(username);
      return user.isEmpty();
   }
}
