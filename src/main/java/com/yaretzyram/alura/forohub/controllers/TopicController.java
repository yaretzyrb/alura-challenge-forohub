package com.yaretzyram.alura.forohub.controllers;

import com.yaretzyram.alura.forohub.domains.models.topic.Topic;
import com.yaretzyram.alura.forohub.domains.models.topic.TopicInputDTO;
import com.yaretzyram.alura.forohub.domains.models.topic.TopicOutputDTO;
import com.yaretzyram.alura.forohub.domains.models.topic.TopicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository repository;

    @Transactional
    @PostMapping
    private ResponseEntity<Topic> createTopic(@RequestBody TopicInputDTO topicInputDTO ){
        Topic topic = new Topic(topicInputDTO);
        repository.save(topic);


        return ResponseEntity.ok().body(topic);

    }

}
