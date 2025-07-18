package com.yaretzyram.alura.forohub.controllers;

import com.yaretzyram.alura.forohub.domains.models.topic.TopicDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Transactional
    @PostMapping
    private ResponseEntity createTopic(@RequestBody TopicDTO data){
        return ResponseEntity.ok().body(data);

    }

}
