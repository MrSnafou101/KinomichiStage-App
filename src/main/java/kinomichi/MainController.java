package kinomichi;

import kinomichi.model.FullEvent;
import kinomichi.model.Participant;

import java.util.*;

public class MainController {
    InputController controller;
    DisplayController displayer;
    private Set<Participant> participantList = new HashSet<>();
    private FullEvent kinomichiEvent;


    MainController(){
        this.controller = new InputController(participantList, kinomichiEvent);
        this.displayer = new DisplayController(participantList, kinomichiEvent);
    }

    public String readUserInput(){return this.controller.readUserInput();}

    public void handleMainMenuAction(String input){
        switch (input.toLowerCase()){
            case "p" -> addParticipant();
            case "s" -> System.out.println("session");
            case "l" -> listParticipant();
            case "i" -> System.out.println("infos");
            default -> System.out.println("Error : action unknown, please try again");
        }
    }

    public void addParticipant(){
        this.controller.addParticipant();
    }

    public void listParticipant(){
        this.displayer.listParticipant();
    }



}
