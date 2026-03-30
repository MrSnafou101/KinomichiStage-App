package kinomichi.model;

import java.lang.classfile.Attribute;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

}
