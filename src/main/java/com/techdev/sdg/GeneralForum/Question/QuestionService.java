package com.techdev.sdg.GeneralForum.Question;
import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private EntityRepository entityRepository;

    public Question submit(Map<String, Object> body) {
        Question question = new Question(
                Objects.toString(body.get(Question.QUESTION), null),
                Objects.toString(body.get(Question.TITLE), null)
        );


        Long SubmitterId = Long.parseLong(Objects.toString(body.get(Question.SUBMITTER), null));
        Entity submitter = entityRepository.findById(SubmitterId).get();

        question.setSubmitter(submitter);

        repository.save(question);
        return question;
    }

    public List<Map<String, Object>> findAll() {
        List<Question> questionsList = repository.findAll();
        List<Map<String, Object>> questions = new ArrayList<>();

        for (Question e : questionsList)
            questions.add(e.toMap());

        return questions;
    }

    public Question findByid(Long id) {
        Question question = repository.findById(id).get();
        return question;
    }


}
