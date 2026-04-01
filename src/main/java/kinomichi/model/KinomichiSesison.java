package kinomichi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class KinomichiSesison extends Activity{
    private long durationInMinute;
    //private ParticipantsList participants;
    private Participant animator;

    public KinomichiSesison(String name, LocalDateTime startDate, long duration, ActivityPricing pricing) {
        super(name, startDate, pricing);
        this.durationInMinute = duration;
    }

    //public Long activityDuration(){return ChronoUnit.HOURS.between(getActivityDate(), this.endDateAndHour);}
    public long getSessionDuration(){return this.durationInMinute;}
    public LocalDateTime getSessionEnd(){return this.getActivityDate().plusMinutes(this.durationInMinute);}
    public boolean setAnimator(Participant animator){
        if(animator.getType() == ParticipantType.ANIMATOR){
            this.animator = animator;
            return true;
        }else{return false;}
    }

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "Session %s on %s for %s minutes".formatted(
                this.getActivityName(),this.getActivityDate().format(formatter), this.durationInMinute
        );
    }

    public String toStringWithDates(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return this.getActivityName() + " form "
                + this.getActivityDate().format(formatter) + " to "
                + this.getSessionEnd().format(formatter);
    }
}
