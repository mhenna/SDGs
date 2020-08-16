package com.techdev.sdg.GeneralForum.Question;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@javax.persistence.Entity
@Table(name = "Question")
public class Question implements Serializable {
    final public static String ID = "id";
    final public static String QUESTION = "question";
    final public static String SUBMITTER = "submitter";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entity_id", nullable = false)
    @JsonBackReference
    private com.techdev.sdg.Entity.Entity submitter;

    public Question(){}
    public Question(String question) {
        setQuestion(question);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setSubmitter(com.techdev.sdg.Entity.Entity submitter) {
        this.submitter = submitter;
    }

    public String getQuestion() {
        return question;
    }
    public com.techdev.sdg.Entity.Entity getSubmitter(com.techdev.sdg.Entity.Entity submitter) {
        return this.submitter;
    }


    @Override
    public String toString() {
        return "question: {\n" +
                "\tid: " + id + ",\n" +
                "\tquestion: " + question + ",\n" +
                "\tsubmitter: " + submitter + ",\n" +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> Question = new HashMap<>();
        Question.put("id", id);
        Question.put("question", question);
        Question.put("submitter", submitter);


        return Question;
    }

}
