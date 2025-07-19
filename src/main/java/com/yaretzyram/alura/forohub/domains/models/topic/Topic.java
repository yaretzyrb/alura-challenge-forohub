package com.yaretzyram.alura.forohub.domains.models.topic;

import com.yaretzyram.alura.forohub.domains.models.answer.Answer;
import com.yaretzyram.alura.forohub.domains.models.course.Course;
import com.yaretzyram.alura.forohub.domains.models.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

    public Topic(){}

    public Topic(TopicInputDTO topicInputDTO) {
        this.title= topicInputDTO.title();
        this.message = topicInputDTO.message();
        this.createdAt = LocalDateTime.now();
        this.author = null;
        this.course = null;
        this.answers = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
