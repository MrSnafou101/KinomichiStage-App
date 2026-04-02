package kinomichi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public abstract class Activity {
    private String activityName;
    private LocalDateTime activityDate;
    private ActivityPricing pricing;
    private ParticipantsList participants;

    public Activity(String name, LocalDateTime startDate, ActivityPricing pricing){
        this.activityName = name;
        this.activityDate = startDate;
        this.pricing = pricing;
        this.participants = new ParticipantsList();
    }
    public String getActivityName() {return activityName;}
    public void setActivityName(String name){this.activityName = name;}
    public LocalDateTime getActivityDate() {return activityDate;}
    public void setDate(LocalDateTime newDate){this.activityDate = newDate;}
    public LocalDate getDate(){return this.activityDate.toLocalDate();}
    public LocalTime getTime(){return this.activityDate.toLocalTime();}

    public boolean addParticipantToActivity(Participant toAdd){return this.participants.addParticipant(toAdd);}
    public void addParticipantToActivity(String[] toAdd){this.participants.addParticipant(toAdd);}
    public ActivityPricing getPricing() {return pricing;}
    public int getNbrRegistered(){return this.participants.getParticipants().size();}
    public Set<Participant> getParticipants(){return this.participants.getParticipants();}

    public boolean isParticipantRegistered(String firstname, String lastname){
        return this.participants.participantExist(firstname, lastname);
    }

    public String toString(){
        return this.activityName + " on " + this.activityDate;
    }

    public boolean equals(Object o){
        return o!= null && o.getClass() == this.getClass()
                && Objects.equals(this.activityName, ((Activity) o).activityName);
    }
    public int hashCode(){
        return this.activityName.hashCode();
    }

}
