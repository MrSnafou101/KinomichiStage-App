package kinomichi.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Activity {
    private String ActivityName;
    private LocalDateTime activityDate;
    private ActivityPricing pricing;
    private ParticipantsList participants;

    public Activity(String name, LocalDateTime startDate, ActivityPricing pricing){
        this.ActivityName = name;
        this.activityDate = startDate;
        this.pricing = pricing;
        this.participants = new ParticipantsList();
    }
    public String getActivityName() {return ActivityName;}

    public boolean addParticipantToActivity(Participant toAdd){return this.participants.addParticipant(toAdd);}
    public void addParticipantToActivity(String[] toAdd){this.participants.addParticipant(toAdd);}

    public LocalDateTime getActivityDate() {return activityDate;}

    public ActivityPricing getPricing() {return pricing;}

    public boolean isParticipantRegistered(String firstname, String lastname){
        return this.participants.participantExist(firstname, lastname);
    }


    public String toString(){
        return this.ActivityName + " on " + this.activityDate;
    }

    public boolean equals(Object o){
        return o!= null && o.getClass() == this.getClass()
                && Objects.equals(this.ActivityName, ((Activity) o).ActivityName);
    }
    public int hashCode(){
        return this.ActivityName.hashCode();
    }

}
