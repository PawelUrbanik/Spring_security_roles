package pl.pawel.spring_users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawel.spring_users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
