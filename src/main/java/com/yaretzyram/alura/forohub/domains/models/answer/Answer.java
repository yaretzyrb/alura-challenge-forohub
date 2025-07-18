package com.yaretzyram.alura.forohub.domains.models.answer;
import com.yaretzyram.alura.forohub.domains.models.topic.Topic;
import com.yaretzyram.alura.forohub.domains.models.username.Username;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "answers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    private Username author;

}
