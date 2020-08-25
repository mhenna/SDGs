package com.techdev.sdg.GeneralForum.Answer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.techdev.sdg.GeneralForum.Question.Question;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@javax.persistence.Entity
@Table(name = "Answer")
public class Answer implements Serializable {
    final public static String ID = "id";
    final public static String ANSWER = "answer";
    final public static String SUBMITTER = "submitter";
    final public static String QUESTION = "question";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "answer", nullable = false)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id", nullable = false)
    @JsonBackReference
    private com.techdev.sdg.Entity.Entity submitter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    public Answer(){}
    public Answer(String answer ) {
        setAnswer(answer);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setSubmitter(com.techdev.sdg.Entity.Entity submitter) {
        this.submitter = submitter;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public com.techdev.sdg.Entity.Entity getSubmitter() {
        return submitter;
    }


    @Override
    public String toString() {
        return "Answer: {\n" +
                "\tid: " + id + ",\n" +
                "\tanswer: " + answer + ",\n" +
                "\tsubmitter: " + submitter + ",\n" +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> Answer = new HashMap<>();
        Answer.put("id", id);
        Answer.put("answer", answer);
        Answer.put("submitter", submitter);


        return Answer;
    }

}
