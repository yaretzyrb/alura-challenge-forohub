package com.yaretzyram.alura.forohub.domains.models.answer;
import com.yaretzyram.alura.forohub.domains.models.topic.Topic;
import com.yaretzyram.alura.forohub.domains.models.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    @ManyToOne()
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private LocalDateTime createdAt;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User author;

    public  Answer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
