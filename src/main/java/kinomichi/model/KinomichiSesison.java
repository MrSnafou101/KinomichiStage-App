package kinomichi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class KinomichiSesison extends Activity{
    private LocalDateTime endDateAndHour;
    private Set<Participant> participants;

    KinomichiSesison(String name, LocalDateTime startDate, LocalDateTime endDate, ActivityPricing pricing) {
        super(name, startDate, pricing);
    }

    public Long activityDuration(){
        return ChronoUnit.HOURS.between(getActivityDate(), this.endDateAndHour);
    }
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.getActivityName() + " form "
                + this.getActivityDate().format(formatter) + " to "
                + this.endDateAndHour.format(formatter);
    }
}
