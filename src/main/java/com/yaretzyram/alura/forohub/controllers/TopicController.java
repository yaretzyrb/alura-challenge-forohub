package com.yaretzyram.alura.forohub.controllers;

import com.yaretzyram.alura.forohub.domains.models.course.Course;
import com.yaretzyram.alura.forohub.domains.models.topic.*;
import com.yaretzyram.alura.forohub.domains.models.user.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService service;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid TopicInputDTO topicInputDTO, UriComponentsBuilder uriComponentsBuilder){

        boolean isTopicDuplicated = service.duplicatedTopic(topicInputDTO);

        if(isTopicDuplicated){
            return ResponseEntity.badRequest().body("Duplicated topic");
        }

        Topic topic = service.createTopic(topicInputDTO);
        topicRepository.save(topic);
        TopicOutputDTO createdTopic = new TopicOutputDTO(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatus(), topic.getAuthor().getName(), topic.getCourse().getName(), topic.getCreatedAt());
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(createdTopic.id()).toUri();
        return ResponseEntity.created(url).body(createdTopic);
    }

    @GetMapping
    public ResponseEntity<List<TopicOutputDTO>> getTopics(){
        return ResponseEntity.ok(topicRepository.findByActiveTrue().stream().sorted(Comparator.comparing(TopicOutputDTO::createdAt)).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicOutputDTO> getTopicByID(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        TopicOutputDTO foundTopic = new TopicOutputDTO(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatus(), topic.getAuthor().getName(), topic.getCourse().getName(), topic.getCreatedAt());
        return ResponseEntity.ok(foundTopic);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<TopicOutputDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO updatedTopicData){
        Topic existingTopic = topicRepository.getReferenceById(id);
        existingTopic.updateTopicData(updatedTopicData);
        TopicOutputDTO updatedTopic = new TopicOutputDTO(existingTopic.getId(), existingTopic.getTitle(), existingTopic.getMessage(), existingTopic.getStatus(), existingTopic.getAuthor().getName(), existingTopic.getCourse().getName(), existingTopic.getCreatedAt());
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic existingTopic = topicRepository.getReferenceById(id);
        existingTopic.deactivateTopic();
        return  ResponseEntity.ok("Topic deleted");
    }

}
