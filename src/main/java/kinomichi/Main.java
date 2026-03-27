package kinomichi;

import kinomichi.utils.ActionMenu;


public class Main {
    MainController actionHandler;

    void main(){
        String userInput = "";
        System.out.println("App starting ....");

        actionHandler = new MainController();

        while(!(userInput).equalsIgnoreCase("q")){
            ActionMenu.displayMainMenu();
            userInput = actionHandler.readUserInput();
            actionHandler.handleMainMenuAction(userInput);
        }

    }
}
