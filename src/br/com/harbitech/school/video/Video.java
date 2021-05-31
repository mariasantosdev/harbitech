package br.com.harbitech.school.video;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.activity.StatusIndicationActivity;
import br.com.harbitech.school.section.Section;

import java.time.OffsetDateTime;

public class Video extends Activity {

   private String url;
   private OffsetDateTime minutesOfVideo;
   private String transition;

   public Video() {
    super();
   }

   String getUrl() {
      return url;
   }

   OffsetDateTime getMinutesOfVideo() {
      return minutesOfVideo;
   }

   String getTransition() {
      return transition;
   }
}
