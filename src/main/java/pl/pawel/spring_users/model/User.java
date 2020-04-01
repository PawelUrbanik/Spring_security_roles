package pl.pawel.spring_users.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.pawel.spring_users.validator.UniqueUsername;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;


@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{pl.pawel.spring_users.model.User.username.NotEmpty.message}")
    @UniqueUsername(message = "{pl.pawel.spring_users.model.User.username.UniqueUsername.message}")
    @Size(min = 3, message = "{pl.pawel.spring_users.model.User.username.Size.message}")
    private String username;
    @NotEmpty(message = "{pl.pawel.spring_users.model.User.password.NotEmpty.message}")
    @Size(min = 3, message = "{pl.pawel.spring_users.model.User.password.Size.message}")
    private String password;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() {
    }

    public User(String username, String password, String  role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
