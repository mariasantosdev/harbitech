package br.com.harbitech.school.alternative;

import br.com.harbitech.school.question.Question;

import javax.persistence.*;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;
import static br.com.harbitech.school.validation.ValidationUtil.validateNonNullClass;
@Entity
public class Alternative {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(columnDefinition = "TEXT")
   private String text;
   @Column(name = "order_visualization")
   private int orderVisualization;
   private boolean correct;
   private String justification;
   @ManyToOne
   private Question question;

   public Alternative(String text, Question question, boolean correct) {
      validateNonBlankText(text, "O texto explicativo não pode estar em branco.");
      validateNonNullClass(question, "Não existe uma questão associada a essa alternativa.");
      this.text = text;
      this.question = question;
      this.correct = correct;
   }

   Long getId() {
      return id;
   }

   String getText() {
      return text;
   }

   boolean isCorrect() {
      return correct;
   }

   int getOrderVisualization() {
      return orderVisualization;
   }

   String getJustification() {
      return justification;
   }

   Question getQuestion() {
      return question;
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
