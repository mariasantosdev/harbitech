package br.com.harbitech.school.alternative;

import br.com.harbitech.school.question.Question;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Alternative {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(columnDefinition = "TEXT")
   private String text;
   private int orderVisualization;
   private boolean correct;
   private String justification;
   @ManyToOne
   private Question question;

   @Deprecated
   public Alternative() {}

   public Alternative(String text, Question question, boolean correct) {
      this.text = text;
      this.question = question;
      this.correct = correct;
   }

   @Override
   public String toString() {
      return "Alternative{" +
              "id=" + id +
              ", text='" + text + '\'' +
              ", orderVisualization=" + orderVisualization +
              ", correct=" + correct +
              ", justification='" + justification + '\'' +
              ", question=" + question +
              '}';
   }
}
