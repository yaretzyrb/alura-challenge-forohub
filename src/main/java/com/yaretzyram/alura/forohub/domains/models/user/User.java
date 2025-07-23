package com.yaretzyram.alura.forohub.domains.models.user;

import com.yaretzyram.alura.forohub.domains.models.answer.Answer;
import com.yaretzyram.alura.forohub.domains.models.topic.Topic;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "author")
    private List<Topic> topics;
    @OneToMany(mappedBy = "author")
    private List<Answer> answers;
    @Column(name = "active")
    private boolean active;

    public  User(){}

    public User(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.name();
        this.email = userRegisterDTO.email();
        this.password = userRegisterDTO.password();
        this.active = true;
    }

    public void updateUserData(UserUpdateDTO userUpdateDTO){
        if (!this.name.equals(userUpdateDTO.name()) && !userUpdateDTO.name().isEmpty()) setName(userUpdateDTO.name());
        if (!this.email.equals(userUpdateDTO.email()) && !userUpdateDTO.email().isEmpty()) setEmail(userUpdateDTO.email());
        if (!this.password.equals(userUpdateDTO.password()) && !userUpdateDTO.password().isEmpty()) setPassword(userUpdateDTO.password());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
