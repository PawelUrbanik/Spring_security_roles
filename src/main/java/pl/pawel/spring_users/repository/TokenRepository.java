package pl.pawel.spring_users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawel.spring_users.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByValue(String value);
}
