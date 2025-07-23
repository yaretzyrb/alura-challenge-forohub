package com.yaretzyram.alura.forohub.domains.models.topic;
import com.yaretzyram.alura.forohub.domains.models.answer.Answer;
import com.yaretzyram.alura.forohub.domains.models.course.Course;
import com.yaretzyram.alura.forohub.domains.models.user.User;
import com.yaretzyram.alura.forohub.domains.models.user.UserOutputDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column(name = "active")
    private boolean active;

    public Topic(){}

    public Topic(TopicInputDTO topicInputDTO, User user, Course course){
        this.title = topicInputDTO.title();
        this.message = topicInputDTO.message();
        this.createdAt = LocalDateTime.now();
        this.author = user;
        this.course = course;
        this.answers = new ArrayList<Answer>();
        this.active = true;
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

    public User getAuthor() {
        return author;
    }

    public Course getCourse() {
        return course;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", author=" + author +
                ", course=" + course +
                ", answers=" + answers +
                '}';
    }

    public void updateTopicData(TopicUpdateDTO updatedTopicData) {
        if(!this.title.equals(updatedTopicData.title()) && !updatedTopicData.title().isEmpty()) setTitle(updatedTopicData.title());
        if(!this.message.equals(updatedTopicData.message()) && !updatedTopicData.message().isEmpty()) setMessage(updatedTopicData.message());
    }
}
