package com.techdev.sdg.GeneralForum.Question;
import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                Objects.toString(body.get(Question.QUESTION), null)
        );


        Long SubmitterId = Long.parseLong(Objects.toString(body.get(Question.SUBMITTER), null));
        Entity submitter = entityRepository.findById(SubmitterId).get();

        question.setSubmitter(submitter);

        repository.save(question);
        return question;
    }


}
