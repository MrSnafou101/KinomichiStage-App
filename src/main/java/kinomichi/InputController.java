package kinomichi;

import kinomichi.model.*;
import kinomichi.utils.ActionMenu;
import kinomichi.utils.DataReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputController {
    private final String PARTICIPAN_REGEX = "^[\\p{L}]+;[\\p{L}]+;\\+?\\d{7,15};[\\w.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,};[\\p{L}0-9 ]+;[\\p{L}]+$";
    private Scanner scan;
    private Set<Participant> participantList;
    private FullEvent kinomichiEvent;

    InputController(Set<Participant> participantList, FullEvent event){
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
            ActionMenu.displayAddParticipantAction();
            input = readUserInput();
            if(!input.equalsIgnoreCase("b")){
                String[] toStore = handleAddParticipantAction(input);
                if(toStore != null){
                    succes = participantList.add(new Participant(
                            toStore[0], //firstname
                            toStore[1], //lastname
                            toStore[2], //phone
                            toStore[3], //email
                            toStore[4], //club
                            ParticipantType.fromString(toStore[5]) //type
                    ));
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
        LocalTime time = null;

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


    private LocalDate makeDateFromString(String dateToParse){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            return LocalDate.parse(dateToParse, formatter);
        }catch(Exception e){
            System.out.println("Wrong date format please try again");
            return null;
        }
    }
    private LocalDate isDatePassed(LocalDate toCheck){
        if(toCheck == null || toCheck.isBefore(LocalDate.now()))return null;
        else return toCheck;
    }
    private LocalTime makeTimeFromString(String timeToParse){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try{
            return LocalTime.parse(timeToParse, formatter);
        }catch(Exception e){
            System.out.println("Wrong time format please try again");
            return null;
        }
    }
}
