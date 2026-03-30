package kinomichi;

import kinomichi.model.FullEvent;
import kinomichi.model.ParticipantsList;
import kinomichi.utils.ActionMenu;

import java.util.*;

public class MainController {
    InputController controller;
    DisplayController displayer;
    private ParticipantsList participantList = new ParticipantsList();
    private FullEvent kinomichiEvent = new FullEvent();


    MainController(){
        this.controller = new InputController(participantList, kinomichiEvent);
        this.displayer = new DisplayController(participantList, kinomichiEvent);
    }

    public String readUserInput(){return this.controller.readUserInput();}

    public void handleMainMenuAction(String input){
        switch (input.toLowerCase()){
            case "p" -> addParticipant();
            case "r" -> registerParticipant();//System.out.println("register");
            case "a" -> displayActivityAction();
            case "u" -> System.out.println("update");
            case "l" -> System.out.println("listing");
            case "i" -> System.out.println("infos");
            default -> System.out.println("Error : action unknown, please try again");
        }
    }

    public void displayActivityAction(){
        String input = "";
        while (!input.equalsIgnoreCase("b")){
            ActionMenu.displayAddActivityMenu();
            input = readUserInput().toLowerCase();
            switch (input){
                case "s" -> addActivity("session");
                case "d" -> addActivity("dinner");
                case "l" -> addActivity("lodgement");
                case "b" -> System.out.println("Go back...");
                default -> System.out.println("Error : action unknown, please try again");
            }
        }
    }

    public void addParticipant(){
        this.controller.addParticipant();
    }
    public void addActivity(String input){this.controller.addActivity(input);}
    public void registerParticipant(){this.controller.addPaticipantToSession();}
    //public void listParticipant(){this.displayer.listParticipant();}



}
