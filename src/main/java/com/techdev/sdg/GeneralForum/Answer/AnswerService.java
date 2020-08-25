package com.techdev.sdg.GeneralForum.Answer;
import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.EntityRepository;
import com.techdev.sdg.GeneralForum.Question.Question;
import com.techdev.sdg.GeneralForum.Question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Answer submit(Map<String, Object> body) throws Exception{
        Answer answer = new Answer(
                Objects.toString(body.get(Answer.ANSWER), null)
        );


        Long SubmitterId = Long.parseLong(Objects.toString(body.get(Answer.SUBMITTER), null));
        Entity submitter = entityRepository.findById(SubmitterId).get();
        answer.setSubmitter(submitter);

        Optional<Question> question = questionRepository.findById(Long.parseLong(Objects.toString(body.get(Answer.QUESTION))));

        if(question.isEmpty()){
            throw new Exception ("Project with specified id does not exist");
        }
        else {
            answer.setQuestion(question.get());
            repository.save(answer);
            return answer;
        }

    }


}
