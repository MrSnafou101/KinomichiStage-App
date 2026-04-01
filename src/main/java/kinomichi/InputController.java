package kinomichi;

import kinomichi.customExceptions.InvalidInputExceptions;
import kinomichi.model.*;
import kinomichi.utils.ActionMenu;
import kinomichi.utils.DataReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static kinomichi.utils.DataParser.*;

public class InputController {
    private final String PARTICIPAN_REGEX = "^[\\p{L}]+;[\\p{L}]+;\\+?\\d{7,15};[\\w.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,};[\\p{L}0-9 ]+;[\\p{L}]+$";
    private Scanner scan;
    private ParticipantsList participantList;
    private FullEvent kinomichiEvent;

    InputController(ParticipantsList participantList, FullEvent event){
        this.participantList = participantList;
        this.kinomichiEvent = event;

        scan = new Scanner(System.in);
        scan.useDelimiter("\\R");
    }
    public String readUserInput(){
        return this.scan.next();
    }

    public void addParticipant(){
        boolean succes = false;
        String input = "";
        while (!succes){
            ActionMenu.displayAddParticipant();
            input = readUserInput();
            if(!input.equalsIgnoreCase("b")){
                String[] toStore = handleAddParticipantAction(input);
                if(toStore != null){
                    succes = participantList.addParticipant(toStore);
                }
                if(succes) System.out.println("Participant added");
            }else{
                System.out.println("Go back...");
                succes = true;
            }
        }
    }
    public String[] handleAddParticipantAction(String input){
        Pattern pattern = Pattern.compile(PARTICIPAN_REGEX);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            return DataReader.readSingleLineCSV(input);
        }else{
            System.out.println("Invalid input, please try again");
            return null;
        }
    }

    public void addActivity(String input){
        switch (input.toLowerCase()){
            case "session" -> addSession();
            case "dinner" -> addDinner();
            case "lodgement" -> addLodgement();
            default -> System.out.println("Oops! there was an error");
        }
    }
    /*
    * will see if refactor to only use 1 string a input and transfer the creation to FullEvent
    * */
    public void addSession(){
        String name, strDate ,strTime;
        long duration;
        LocalDate date = null;
        LocalTime time = null;

        System.out.println("##### session #####");
        System.out.println("Enter the name of the session");
        name = this.readUserInput();
        while(date == null){
            System.out.println("Enter the date like so : dd/mm/yyyy");
            strDate = this.readUserInput();
            date = isDatePassed(makeDateFromString(strDate));
        }
        while(time == null){
            System.out.println("Enter the time for the start of the session like so : hh:mm");
            strTime = this.readUserInput();
            time = makeTimeFromString(strTime);
        }
        System.out.println("Enter the duration of the session in minutes");
        duration = Long.parseLong(this.readUserInput());
        KinomichiSesison session = new KinomichiSesison(name, LocalDateTime.of(date, time), duration, null);
        if(kinomichiEvent.addActivity(session)){
            System.out.println(session);
        }else{
            System.out.println("Its seems this session already exist");
            System.out.println(kinomichiEvent.getActivityFromName(name).toString());
        }
    }
    public void addDinner(){
        String name, strDate ,strTime, description;
        LocalDate date = null;
        LocalTime time = null;

        System.out.println("#### dinner ####");
        System.out.println("Enter the name of the Dinner");
        name = this.readUserInput();
        while(date == null){
            System.out.println("Enter the date like so : dd/mm/yyyy");
            strDate = this.readUserInput();
            date = isDatePassed(makeDateFromString(strDate));
        }
        while(time == null){
            System.out.println("Enter the time for the start of the session like so : hh:mm");
            strTime = this.readUserInput();
            time = makeTimeFromString(strTime);
        }
        System.out.println("Enter a small description in one line");
        description = this.readUserInput();
        EventDinner dinner = new EventDinner(name, LocalDateTime.of(date, time), description, null);
        if(kinomichiEvent.addActivity(dinner)){
            System.out.println(dinner);
        }else{
            System.out.println("Its seems this dinner already exist");
            System.out.println(kinomichiEvent.getActivityFromName(name).toString());
        }
    }
    public void addLodgement(){
        String name, strDate;
        int beds;
        LocalDate date = null;
        LocalTime time;

        System.out.println("#### lodgement ####");
        System.out.println("Enter the name of the lodgement");
        name = this.readUserInput();
        while(date == null){
            System.out.println("Enter the date like so : dd/mm/yyyy");
            strDate = this.readUserInput();
            date = isDatePassed(makeDateFromString(strDate));
        }
        time = LocalTime.of(0,0); //arbitrary value because it's available for the whole event
        System.out.println("How many bed are available ?");
        beds = Integer.parseInt(this.readUserInput());
        Lodgement room = new Lodgement(name, LocalDateTime.of(date, time), beds, null);
        if(kinomichiEvent.addActivity(room)){
            System.out.println(room);
        }else{
            System.out.println("Its seems this dinner already exist");
            System.out.println(kinomichiEvent.getActivityFromName(name).toString());
        }
    }

    public void addPaticipantToSession(){
        Participant selected = null;
        List<Activity> selectedActivity = new ArrayList<>();
        String input = "";
        int parsedInput;
        Map<Integer, Participant> partMap = participantList.toMap();
        Map<Integer, Activity> actiMap = kinomichiEvent.ActivityToMapForSelection("session");

        ActionMenu.displayAddParticipantToSession();
        partMap.forEach((key, val) -> System.out.println(key+". "+val));
        input = this.readUserInput();
        if(!input.equalsIgnoreCase("b")){
            try{
                parsedInput = Integer.parseInt(input);
                selected = partMap.get(parsedInput);
                System.out.println("selected => "+selected);

                actiMap.forEach((key, val) -> System.out.println(key+". "+val));
                input = this.readUserInput();
                String[] tmp = DataReader.readSingleLineCSV(input);
                for (String key : tmp) {
                    Activity value = actiMap.get(Integer.parseInt(key));
                    if (value != null) {
                        selectedActivity.add(value);
                    }
                }
                for (Activity activity : selectedActivity) {
                    if(activity.addParticipantToActivity(selected))System.out.printf("REGISTERED to %s \n", activity);
                    else System.out.printf("ERR failed to register to %s \n", activity);
                }
            } catch (InvalidInputExceptions e) {
                System.out.println("Invalid input, please try again");
            }
        }
    }
}
