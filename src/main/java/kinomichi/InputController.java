package kinomichi;

import kinomichi.model.FullEvent;
import kinomichi.model.Participant;
import kinomichi.model.ParticipantType;
import kinomichi.utils.ActionMenu;
import kinomichi.utils.DataReader;

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
                if(succes) System.out.println("Participant added ");
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
}
