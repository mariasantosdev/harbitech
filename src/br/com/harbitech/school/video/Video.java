package br.com.harbitech.school.video;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;

public class Video extends Activity {

   private String url;
   private int timeInMinutes;
   private String transcription;

   public Video(String title, String codeUrl, Section section, String url) {
      super(title, codeUrl, section);
      validateNonBlankText(url, "O código do curso não pode estar em branco.");
      this.url = url;
   }

   String getUrl() {
      return url;
   }

   int getTimeInMinutes() {
      return timeInMinutes;
   }

   String getTranscription() {
      return transcription;
   }

   @Override
   public String toString() {
      return "Video{" +
              "url='" + url + '\'' +
              ", timeInMinutes=" + timeInMinutes +
              ", transcription='" + transcription + '\'' +
              '}';
   }
}
