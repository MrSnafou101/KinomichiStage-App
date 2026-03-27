package kinomichi.model;

import java.time.LocalDateTime;
import java.util.Set;

public class EventDinner extends Activity{
    private Set<Participant> participants;
    private String description;

    EventDinner(String name, LocalDateTime startDate, ActivityPricing pricing, String description) {
        super(name, startDate, pricing);
        this.description = description;
    }
}
