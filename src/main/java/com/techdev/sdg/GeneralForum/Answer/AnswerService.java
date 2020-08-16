package com.techdev.sdg.GeneralForum.Answer;
import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.EntityRepository;
import com.techdev.sdg.GeneralForum.Question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Autowired
    private EntityRepository entityRepository;

    public Answer submit(Map<String, Object> body) {
        Answer answer = new Answer(
                Objects.toString(body.get(Answer.ANSWER), null)
        );


        Long SubmitterId = Long.parseLong(Objects.toString(body.get(Answer.SUBMITTER), null));
        Entity submitter = entityRepository.findById(SubmitterId).get();

        answer.setSubmitter(submitter);

        return repository.save(answer);
    }


}
