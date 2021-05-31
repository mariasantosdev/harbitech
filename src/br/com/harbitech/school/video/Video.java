package br.com.harbitech.school.video;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.activity.StatusIndicationActivity;
import br.com.harbitech.school.section.Section;

import java.time.OffsetDateTime;

public class Video extends Activity {

   private String url;
   private OffsetDateTime minutesOfVideo;
   private String transition;


   public Video(Long id, String codeUrl, String title, String description, StatusIndicationActivity indication,
                Section section, String url, OffsetDateTime minutesOfVideo, String transition) {
      super(id, codeUrl, title, description, indication, section);
      this.url = url;
      this.minutesOfVideo = minutesOfVideo;
      this.transition = transition;
   }

   OffsetDateTime getMinutesOfVideo() {
      return minutesOfVideo;
   }

   String getTransition() {
      return transition;
   }
}
