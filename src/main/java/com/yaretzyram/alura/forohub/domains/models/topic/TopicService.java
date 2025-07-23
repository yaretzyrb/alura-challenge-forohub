package com.yaretzyram.alura.forohub.domains.models.topic;

import com.yaretzyram.alura.forohub.domains.models.course.Course;
import com.yaretzyram.alura.forohub.domains.models.course.CourseRepository;
import com.yaretzyram.alura.forohub.domains.models.user.User;
import com.yaretzyram.alura.forohub.domains.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(TopicInputDTO topicInputDTO) {

        User user = userRepository.getReferenceById(topicInputDTO.authorId());
        Course course = courseRepository.getReferenceById((topicInputDTO.courseId()));
        return new Topic(topicInputDTO, user, course);
    }

    public boolean duplicatedTopic(TopicInputDTO topicInputDTO){
        User user = userRepository.getReferenceById(topicInputDTO.authorId());

        List<Topic> topics = topicRepository.getSameAuthorDuplicatedTopics(user.getId(),topicInputDTO.title(), topicInputDTO.message());
        //returns false if topic is NOT duplicated
        return !topics.isEmpty();
    }
}
