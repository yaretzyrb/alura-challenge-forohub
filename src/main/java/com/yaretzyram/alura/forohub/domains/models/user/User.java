package com.yaretzyram.alura.forohub.domains.models.user;

import com.yaretzyram.alura.forohub.domains.models.answer.Answer;
import com.yaretzyram.alura.forohub.domains.models.topic.Topic;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @OneToMany(mappedBy = "author")
    @Column(name = "topics")
    private List<Topic> topics;
    @OneToMany(mappedBy = "author")
    @Column(name = "answers")
    private List<Answer> answers;
    @Column(name = "active")
    private boolean active;

    public  User(){}

    public User(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.name();
        this.email = userRegisterDTO.email();
        this.password = userRegisterDTO.password();
        this.topics = new ArrayList<Topic>();
        this.answers = new ArrayList<Answer>();
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
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
                ", topics=" + topics +
                ", answers=" + answers +
                '}';
    }
}
