package kinomichi.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FullEvent {
    private Set<Activity> activitySet = new HashSet<>();

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

    public Map<Integer, Activity> ActivityToMapForSelection(String classStr){
        Class c = (classStr.equalsIgnoreCase("lodgement"))? Lodgement.class :
                (classStr.equalsIgnoreCase("dinner"))? EventDinner.class : KinomichiSesison.class;

        List<Activity> tmpList = activitySet.stream()
                .filter(a -> a.getClass() == c)
                .toList();

        return IntStream.range(0, tmpList.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        tmpList::get
                ));
    }

    public List<Activity> getSessionsForParticipants(String firstname, String lastname){
        return this.activitySet.stream()
                .filter(a -> a.getClass() == KinomichiSesison.class)
                .filter(s -> s.isParticipantRegistered(firstname, lastname))
                .toList();
    }

}
