package kinomichi.utils;

import java.util.Scanner;

public class ActionMenu {
    private Scanner scan;
    private static final String MAIN_MENU = """
            <<<<  Welcom to the kinomishi app  >>>>
            What do you want to do ?
            P => Add participant
            A => Add activity
            L => list participants
            I => Other infos
            Q => Quit
            """;
    private static final String ADD_PARTICIPANT = """
            **** Adding participant ****
            please enter the infos like so :
            firstname;lastname;phoneNumber;email;clubName;participantType
            
            phoneNumber as international standard => +32496632548
            participantType => kid, adult, animator
            
            press B or b to go back
            """;
    private static final String ADD_ACTIVITY = """
            **** Adding activity ****
            What activity do you want to add ?
            S => Kinomichi session
            D => Dinner
            L => Lodgement
            B => Go back
            """;

    public static void displayMainMenu(){System.out.println(MAIN_MENU);}
    public static void displayAddParticipantAction(){System.out.println(ADD_PARTICIPANT);}
    public static void displayAddActivityMenu(){System.out.println(ADD_ACTIVITY);}

}
