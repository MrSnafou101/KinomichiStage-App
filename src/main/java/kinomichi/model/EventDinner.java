package kinomichi.model;

import kinomichi.utils.DataParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventDinner extends Activity{
    //private ParticipantsList participants;
    private String description;

    public EventDinner(String name, LocalDateTime startDate, String description, ActivityPricing pricing) {
        super(name, startDate, pricing);
        this.description = description;
    }

    public String getDescription(){return this.description;}

    public String toSaveString() {
        //&&name;dd/mm/yyy;hh:mm;description

        return "&&%s;%s;%s;%s"
                .formatted(
                        this.getActivityName(),
                        DataParser.makeStringFromDate(this.getDate()),
                        DataParser.makeStringFromTime(this.getTime()),
                        this.description
                        ).concat(stringOfParticipantsId());
    }

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "%s on %s | description: %s".formatted(
                this.getActivityName(), this.getActivityDate().format(formatter), this.description
        );
    }
}
