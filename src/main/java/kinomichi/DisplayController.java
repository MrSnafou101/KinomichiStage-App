package kinomichi;

import kinomichi.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
                a -> a.getClass() == KinomichiSession.class
        ).forEach(System.out::println);
    }

    public void listSesionsWithParticipants(){
        List<Activity> toShow = this.kinomichiEvent.getActivitySet().stream().filter(
                a -> a.getClass() == KinomichiSession.class
        ).toList();

        StringBuilder str = new StringBuilder();
        for (Activity a : toShow){
            str.append(a.toString()).append("\n");
            Set<Participant> resutl = a.getParticipants();
            if(resutl.isEmpty()){
                str.append("- no registration yet").append("\n");
            }else{
                for(Participant p : resutl){
                    if(((KinomichiSession)a).getAnimator() != null && ((KinomichiSession)a).getAnimator().equals(p)){
                        str.append("- ").append(p).append(" ANIMATOR ").append("\n");
                    }else{
                        str.append("- ").append(p).append("\n");
                    }
                }
            }
        }
        System.out.println(str);
    }

    public void listDinner(){
        this.kinomichiEvent.getActivitySet().stream().filter(
                a -> a.getClass() == EventDinner.class
        ).forEach(a -> System.out.printf("%s | Nbr meals : %s%n", a.toString(),a.getNbrRegistered()));
    }

    public void listDinnersWithParticipants(){
        List<Activity> dinners = this.kinomichiEvent.getActivitySet().stream().filter(
                a -> a.getClass() == EventDinner.class
        ).toList();

        StringBuilder str = new StringBuilder();
        for (Activity a : dinners){
            str.append(a.toString()).append("\n");
            Set<Participant> resutl = a.getParticipants();
            if(resutl.isEmpty()){
                str.append("- no registration yet").append("\n");
            }else{
                for(Participant p : resutl){
                    str.append("- ").append(p).append("\n");
                }
            }
        }
        System.out.println(str);
    }

    public void listlodgement(){
        this.kinomichiEvent.getActivitySet().stream().filter(
                a -> a.getClass() == Lodgement.class
        ).forEach(System.out::println);
    }

}
