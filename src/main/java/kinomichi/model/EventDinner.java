package kinomichi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class EventDinner extends Activity{
    //private ParticipantsList participants;
    private String description;

    public EventDinner(String name, LocalDateTime startDate, String description, ActivityPricing pricing) {
        super(name, startDate, pricing);
        this.description = description;
    }

    public String getDescription(){return this.description;}
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "%s on %s | description: %s".formatted(
                this.getActivityName(), this.getActivityDate().format(formatter), this.description
        );
    }
}
