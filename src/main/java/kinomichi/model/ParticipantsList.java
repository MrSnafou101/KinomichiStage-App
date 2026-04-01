package kinomichi.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParticipantsList {
    private Set<Participant> participants = new HashSet<>();

    public Set<Participant> getParticipants() {return participants;}

    public boolean addParticipant(Participant toAdd){return this.participants.add(toAdd);}
    public boolean addParticipant(String[] data){
        return this.participants.add(new Participant(
                data[0], //firstname
                data[1], //lastname
                data[2], //phone
                data[3], //email
                data[4], //club
                ParticipantType.fromString(data[5]))
        );
    }

    public void removeParticipant(Participant toRemove){this.participants.remove(toRemove);}
    public boolean participantExist(String firstname, String lastname){
        for(Participant p : this.participants){
            if(p.getFirstName().equalsIgnoreCase(firstname) && p.getLastName().equalsIgnoreCase(lastname))return true;
        }
        return false;
    }

    public Map<Integer, Participant> toMap(){
        return IntStream.range(0, participants.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        participants.stream().toList()::get
                ));
    }
}
