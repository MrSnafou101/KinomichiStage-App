package kinomichi;

import kinomichi.model.FullEvent;
import kinomichi.model.Participant;
import kinomichi.model.ParticipantsList;

import java.util.Set;

public class DisplayController {
    private ParticipantsList participantList;
    private FullEvent kinomichiEvent;

    DisplayController(ParticipantsList participantList, FullEvent event){
        this.participantList = participantList;
        this.kinomichiEvent = event;
    }

//    public void listParticipant(){
//        participantList.forEach(System.out::println);
//    }
}
