package kinomichi.model;

import java.time.LocalDateTime;

public class Lodgement extends Activity {

    private int availableBed;
    private boolean isAvailabe = true;
    private Participant occupent;

    public Lodgement(String name, LocalDateTime startDate, int beds, ActivityPricing pricing) {
        super(name, startDate, pricing);
        this.availableBed = beds;
    }

    public int getAvailableBed(){return this.availableBed;}
    public boolean isAvailabe(){return this.isAvailabe;}
    public void setOccupent(Participant toAdd){
        if(isAvailabe){
            this.occupent = toAdd;
            this.isAvailabe = false;
        }
    }
    public void removeOccupent(){
        this.isAvailabe = true;
        this.occupent = null;
    }

    public String toString(){
        return "Lodgement of "+ this.availableBed + " beds is " + ((isAvailabe)? "available" : "not available");
    }
}
