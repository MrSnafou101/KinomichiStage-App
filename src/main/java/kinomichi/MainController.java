package kinomichi;

import kinomichi.model.FullEvent;
import kinomichi.model.ParticipantsList;
import kinomichi.utils.ActionMenu;
import kinomichi.utils.DataReader;

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
    MainController(ParticipantsList list, FullEvent event){
        this.participantList = list;
        kinomichiEvent = event;
        this.controller = new InputController(list, event);
        this.displayer = new DisplayController(list, event);
    }

    public String readUserInput(){return this.controller.readUserInput();}

    public void handleMainMenuAction(String input){
        switch (input.toLowerCase()){
            case "p" -> addParticipant();
            case "r" -> registerParticipant();//System.out.println("register");
            case "t" -> rentARoom();
            case "a" -> displayActivityAction();
            case "u" -> updateOptions();//System.out.println("update");
            case "l" -> listingMenu();//System.out.println("listing");
            case "i" -> System.out.println("infos");
            case "s" -> DataReader.saveIntoCSV(participantList, kinomichiEvent);
            default -> System.out.println("Error : action unknown, please try again");
        }
    }
    public void addParticipant(){
        this.controller.addParticipant();
    }
    public void registerParticipant(){this.controller.addPaticipantToSession();}

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
    public void addActivity(String input){this.controller.addActivity(input);}

    public void listingMenu(){
        String input = "";
        while (!input.equalsIgnoreCase("b")){
            ActionMenu.displayListingMenu();
            input = readUserInput().toLowerCase();
            switch (input){
                case "p" -> this.displayer.listParticipants();
                case "p2" -> this.displayer.listParticipantsWithSessions();
                case "s" -> this.displayer.listSesions();
                case "s2" -> this.displayer.listSesionsWithParticipants();
                case "d" -> this.displayer.listDinner();
                case "d2" -> this.displayer.listDinnersWithParticipants();
                case "l" -> this.displayer.listlodgement();
                default -> System.out.println("Error : action unknown, please try again");
            }
        }
    }

    public void rentARoom(){
        this.controller.rentARoom();
    }

    public void updateOptions(){
        String input = "";
        while (!input.equalsIgnoreCase("b")){
            ActionMenu.displayUpdateMenu();
            input = readUserInput().toLowerCase();
            switch (input){
                case "a" -> this.controller.updateSessionAnimator();
                case "t" -> this.controller.updateActivity();
                case "d" -> this.controller.deleteActivity();
                default -> System.out.println("Error : action unknown, please try again");
            }
        }
    }

}
