
package com.techdev.sdg.GeneralForum;

import com.techdev.sdg.GeneralForum.Answer.*;
import com.techdev.sdg.GeneralForum.Question.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@javax.persistence.Entity
@Table(name = "GeneralForum")
public class GeneralForum implements Serializable {
        final public static String ID = "id";
        final public static String QUESTION = "question";
        final public static String ANSWER = "answer";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @OneToOne
        @JoinColumn(name="question_id")
        private Question question;

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "answer_id", nullable = false)
        private Set<Answer> answers = new HashSet<>();

        public GeneralForum() {
        }

        public void setQuestion(Question question) {
        this.question = question;
    }

        public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }


        public Question getQuestion() {
            return question;
        }

        public Set<Answer> getAnswers() {
            return answers;
        }


        @Override
        public String toString() {
            return "Entity: {\n" +
                    "\tid: " + id + ",\n" +
                    "\tquestion: " + question + ",\n" +
                    "\tanswers: " + answers + ",\n" +
                    '}';
        }

        public Map<String, Object> toMap() {
            Map<String, Object> GeneralForum = new HashMap<>();
            GeneralForum.put("id", id);
            GeneralForum.put("question", question);
            GeneralForum.put("answers", answers);


            return GeneralForum;
        }

}
