package kinomichi.model;

import java.time.LocalDateTime;

public class Lodgement extends Activity {

    private int availableBed;
    private boolean isAvailabe;

    Lodgement(String name, LocalDateTime startDate, ActivityPricing pricing, int beds) {
        super(name, startDate, pricing);
        this.availableBed = beds;
    }

    public int getAvailableBed(){return this.availableBed;}
    public boolean isAvailabe(){return this.isAvailabe;}

    public String toString(){
        return "Lodgement of "+ this.availableBed + " beds is " + ((isAvailabe)? "available" : "not available");
    }
}
