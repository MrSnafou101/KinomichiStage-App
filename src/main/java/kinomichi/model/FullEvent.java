package kinomichi.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FullEvent {
    private Set<Activity> activitySet = new HashSet<>();
    private Map<String, Class> classMap = Map.of(
            "session", KinomichiSession.class,
            "dinner", EventDinner.class,
            "lodgement", Lodgement.class
    );

    public Set<Activity> getActivitySet() {return activitySet;}

    public boolean addActivity(Activity toAdd){
        return this.activitySet.add(toAdd);
    }
    public Activity getActivityFromName(String name){
        return this.activitySet.stream()
                .filter(a -> Objects.equals(a.getActivityName(), name))
                .findFirst().get();
    }
    public void removeActivity(Activity toRemove){this.activitySet.remove(toRemove);}
    public void displayActivity(){
        this.activitySet.forEach(System.out::println);
    }

    public Activity stillAvailableRoomsForNbrBed(int nbrBeds){
        List<Activity> rooms = getFilteredActivities("lodgement");
        return rooms.stream().filter(r ->
                ((Lodgement) r).isAvailabe() && ((Lodgement)r).getAvailableBed() == nbrBeds)
                .findFirst().get();
    }

    public List<Activity> getFilteredActivities(String classStr){
//        Class c = (classStr.equalsIgnoreCase("lodgement"))? Lodgement.class :
//                (classStr.equalsIgnoreCase("dinner"))? EventDinner.class : KinomichiSession.class;

        Class c = classMap.get(classStr);

        return activitySet.stream()
                .filter(a -> a.getClass() == c)
                .toList();
    }

    public Map<Integer, Activity> ActivityToMapForSelection(){
        return IntStream.range(0, activitySet.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        activitySet.stream().toList()::get
                ));
    }
    public Map<Integer, Activity> ActivityToMapForSelection(String classStr){
        List<Activity> tmpList = getFilteredActivities(classStr);

        return IntStream.range(0, tmpList.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        tmpList::get
                ));
    }

    public List<Activity> getSessionsForParticipants(String firstname, String lastname){
        return this.activitySet.stream()
                .filter(a -> a.getClass() == KinomichiSession.class)
                .filter(s -> s.isParticipantRegistered(firstname, lastname))
                .toList();
    }

}
