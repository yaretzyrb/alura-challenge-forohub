package com.yaretzyram.alura.forohub.domains.models.user;

import com.yaretzyram.alura.forohub.domains.models.answer.Answer;
import com.yaretzyram.alura.forohub.domains.models.topic.Topic;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "author")
    private List<Topic> topics;
    @OneToMany(mappedBy = "author")
    private List<Answer> answers;
    @Column(name = "active", nullable = false)
    private boolean active;

    public  User(){}

    public User(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.name();
        this.email = userRegisterDTO.email();
        setPassword(userRegisterDTO.password());
        this.active = true;
    }

    public void updateUserData(UserUpdateDTO userUpdateDTO){
        if (!this.name.equals(userUpdateDTO.name()) && !userUpdateDTO.name().isEmpty()) setName(userUpdateDTO.name());
        if (!this.email.equals(userUpdateDTO.email()) && !userUpdateDTO.email().isEmpty()) setEmail(userUpdateDTO.email());
    }

    public void deactivateUser(){
        this.active = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setPassword(String password) {
        String salt = BCrypt.gensalt(12);
        this.password = BCrypt.hashpw(password, salt);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
