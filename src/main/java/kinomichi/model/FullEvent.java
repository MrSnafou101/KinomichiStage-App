package kinomichi.model;

import java.lang.classfile.Attribute;
import java.util.Set;

public class FullEvent {
    private Set<Activity> activitySet;

    public Set<Activity> getActivitySet() {return activitySet;}

    public void addActivity(Activity toAdd){
        this.activitySet.add(toAdd);
    }
    public void removeActivity(Activity toRemove){this.activitySet.remove(toRemove);}

    public void displayActivity(){
        this.activitySet.forEach(System.out::println);
    }

}
