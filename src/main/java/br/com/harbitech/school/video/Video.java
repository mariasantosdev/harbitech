package br.com.harbitech.school.video;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIDEO")
public class Video extends Activity {

   private String url;
   @Column(name = "time_in_minutes")
   private int timeInMinutes;
   @Column(columnDefinition = "TEXT")
   private String transcription;

   public Video(String title, String codeUrl, Section section, String url) {
      super(title, codeUrl, section);
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
