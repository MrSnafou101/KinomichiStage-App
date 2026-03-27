package kinomichi;

import kinomichi.model.FullEvent;
import kinomichi.model.Participant;

import java.util.Set;

public class DisplayController {
    private Set<Participant> participantList;
    private FullEvent kinomichiEvent;

    DisplayController(Set<Participant> participantList, FullEvent event){
        this.participantList = participantList;
        this.kinomichiEvent = event;
    }

    public void listParticipant(){
        participantList.forEach(System.out::println);
    }
}
