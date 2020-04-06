package pl.pawel.spring_users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawel.spring_users.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
