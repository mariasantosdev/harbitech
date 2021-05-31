package br.com.harbitech.school.video;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.activity.StatusIndicationActivity;
import br.com.harbitech.school.section.Section;

import java.time.OffsetDateTime;

public class Video extends Activity {

   private String codeUrl;
   private OffsetDateTime minutesOfVideo;
   private String transition;

   public Video(Long id, String codeUrl, String title, String description, StatusIndicationActivity indication,
                Section section, String codeUrl1, OffsetDateTime minutesOfVideo, String transition) {
      super(id, codeUrl, title, description, indication, section);
      this.codeUrl = codeUrl1;
      this.minutesOfVideo = minutesOfVideo;
      this.transition = transition;
   }

   String getCodeUrl() {
      return codeUrl;
   }

   OffsetDateTime getMinutesOfVideo() {
      return minutesOfVideo;
   }

   String getTransition() {
      return transition;
   }
}
