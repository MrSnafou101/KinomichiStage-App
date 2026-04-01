package kinomichi;

import kinomichi.model.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DisplayController {
    private ParticipantsList participantList;
    private FullEvent kinomichiEvent;

    DisplayController(ParticipantsList participantList, FullEvent event){
        this.participantList = participantList;
        this.kinomichiEvent = event;
    }

    public void listParticipants(){
        this.participantList.getParticipants().forEach(System.out::println);
    }

    public void listParticipantsWithSessions(){
        Map<Participant, List<Activity>> toShow = this.participantList.getParticipants().stream()
                .collect(Collectors.toMap(
                        p -> p,
                        p -> this.kinomichiEvent.getSessionsForParticipants(p.getFirstName(), p.getLastName())
                ));
        System.out.println(mapToString(toShow));
    }

    private String mapToString(Map<Participant, List<Activity>> toShow){
        StringBuilder str = new StringBuilder();
        for (Participant p : toShow.keySet()){
            str.append(p.toString()).append("\n");
            List<Activity> resutl = toShow.get(p);
            if(resutl.isEmpty()){
                str.append("  => not registered yet").append("\n");
            }else{
                for(Activity a : resutl){
                    str.append("  => ").append(a.toString()).append("\n");
                }
            }
        }
        return str.toString();
    }

    public void listSesions(){
        this.kinomichiEvent.getActivitySet().stream().filter(
                a -> a.getClass() == KinomichiSesison.class
        ).forEach(System.out::println);
    }

    public void listSesionsWithParticipants(){
    }

    public void listDinner(){
        this.kinomichiEvent.getActivitySet().stream().filter(
                a -> a.getClass() == EventDinner.class
        ).forEach(System.out::println);
    }

    public void listDinnersWithParticipants(){
    }

    public void listlodgement(){
        this.kinomichiEvent.getActivitySet().stream().filter(
                a -> a.getClass() == Lodgement.class
        ).forEach(System.out::println);
    }

}
