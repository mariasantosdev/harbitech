package br.com.harbitech.school.alternative;

import br.com.harbitech.school.question.Question;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;
import static br.com.harbitech.school.validation.ValidationUtil.validateNonNullClass;

public class Alternative {

   private Long id;
   private String text;
   private int orderVisualization;
   private AlternativeType type;
   private String justification;
   private Question question;

   public Alternative(String text, Question question) {
      validateNonBlankText(text, "O texto explicativo não pode estar em branco.");
      validateNonNullClass(question, "Não existe uma questão associada a essa alternativa.");
      this.text = text;
      this.question = question;
      this.type = AlternativeType.INCORRECT;
   }

   Long getId() {
      return id;
   }

   String getText() {
      return text;
   }

   AlternativeType getType() {
      return type;
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
              ", type=" + type +
              ", justification='" + justification + '\'' +
              ", question=" + question +
              '}';
   }
}
