package com.techdev.sdg.GeneralForum.Question;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techdev.sdg.GeneralForum.Answer.Answer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@javax.persistence.Entity
@Table(name = "Question")
public class Question implements Serializable {
    final public static String ID = "id";
    final public static String QUESTION = "question";
    final public static String SUBMITTER = "submitter";
    final public static String TITLE = "title";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "entity_id", nullable = false)
    @JsonBackReference
    private com.techdev.sdg.Entity.Entity submitter;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Answer> answers = new HashSet<>();

    public Question(){}
    public Question(String question, String title) {
        setQuestion(question);
        setTitle(title);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubmitter(com.techdev.sdg.Entity.Entity submitter) {
        this.submitter = submitter;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getTitle() {
        return title;
    }

    public Set<Answer> getAnswers() {
        return answers;
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
                "\ttitle: " + title + ",\n" +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> Question = new HashMap<>();
        Question.put("id", id);
        Question.put("question", question);
        Question.put("submitter", submitter);
        Question.put("title", title);


        return Question;
    }

}
