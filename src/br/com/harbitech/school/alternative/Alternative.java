package br.com.harbitech.school.alternative;

import br.com.harbitech.school.question.Question;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;
import static br.com.harbitech.school.validation.ValidationUtil.validateNonNullClass;

public class Alternative {

   private Long id;
   private String text;
   private int orderVisualization;
   private boolean correct;
   private String justification;
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
