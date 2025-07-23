package com.yaretzyram.alura.forohub.domains.models.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("""
            SELECT t FROM User u 
            JOIN u.topics t 
            WHERE u.id = :id 
            AND t.title LIKE :title 
            AND t.message LIKE :message""")
    List<Topic> getSameAuthorDuplicatedTopics(Long id, String title, String message);

}
