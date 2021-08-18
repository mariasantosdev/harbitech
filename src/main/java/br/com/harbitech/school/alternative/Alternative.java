package br.com.harbitech.school.alternative;

import br.com.harbitech.school.question.Question;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ToString
public class Alternative {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(columnDefinition = "TEXT")
   @NotBlank(message = "O texto explicativo não pode estar em branco.")
   private String text;
   private int orderVisualization;
   private boolean correct;
   @Size(max = 500, message = "Ops! O justificativa da alternativa não pode ter mais do que 500 caracteres.")
   private String justification;
   @ManyToOne
   @NotNull(message = "A alternativa precisa ter uma questão associada.")
   private Question question;

   @Deprecated
   public Alternative() {}

   public Alternative(String text, Question question, boolean correct) {
      this.text = text;
      this.question = question;
      this.correct = correct;
   }
}
