package kinomichi.model;

import kinomichi.utils.DataParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KinomichiSession extends Activity{
    private long durationInMinute;
    //private ParticipantsList participants;
    private Participant animator;

    public KinomichiSession(String name, LocalDateTime startDate, long duration, ActivityPricing pricing) {
        super(name, startDate, pricing);
        this.durationInMinute = duration;
    }
    public Participant getAnimator(){return this.animator;}
    //public Long activityDuration(){return ChronoUnit.HOURS.between(getActivityDate(), this.endDateAndHour);}
    public long getSessionDuration(){return this.durationInMinute;}
    public LocalDateTime getSessionEnd(){return this.getActivityDate().plusMinutes(this.durationInMinute);}
    public boolean setAnimator(Participant animator){
        if(animator.getType() == ParticipantType.ANIMATOR){
            this.animator = animator;
            return true;
        }else{return false;}
    }

    public String toSaveString() {
        //##name;dd/mm/yyy;hh:mm;duration
        return "##%s;%s;%s%s"
                .formatted(
                        this.getActivityName(),
                        DataParser.makeStringFromDate(this.getDate()),
                        DataParser.makeStringFromTime(this.getTime()),
                        this.durationInMinute
                );
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
