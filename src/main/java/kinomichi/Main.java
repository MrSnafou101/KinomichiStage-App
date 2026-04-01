package kinomichi;

import kinomichi.utils.ActionMenu;
import kinomichi.utils.DataReader;


public class Main {
    MainController actionHandler;

    void main(){
        String userInput = "";
        System.out.println("App starting ....");
        System.out.println("Loading data...");
        DataReader.readFromCSV();
        System.out.println("Data Loaded");

        //actionHandler = new MainController();
        actionHandler = new MainController(DataReader.participantList, DataReader.kinomichiEvent);

        while(!(userInput).equalsIgnoreCase("q")){
            ActionMenu.displayMainMenu();
            userInput = actionHandler.readUserInput();
            actionHandler.handleMainMenuAction(userInput);
        }
    }
}
