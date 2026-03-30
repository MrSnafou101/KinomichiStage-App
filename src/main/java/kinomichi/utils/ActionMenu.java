package kinomichi.utils;

import java.util.Scanner;

public class ActionMenu {
    private Scanner scan;
    private static final String MAIN_MENU = """
            <<<<  Welcome to the kinomishi app  >>>>
            =======================================
            What do you want to do ?
            P => Add participant
            R => Register participant
            A => Add activity
            U => Update activity
            L => list participants
            I => Other infos
            Q => Quit
            =======================================
            """;
    private static final String ADD_PARTICIPANT = """
            **** Adding participant ****
            ========================================
            please enter the infos like so :
            firstname;lastname;phoneNumber;email;clubName;participantType
            
            phoneNumber as international standard => +32496632548
            participantType => kid, adult, animator
            
            press B or b to go back
            =========================================
            """;
    private static final String ADD_ACTIVITY = """
            **** Adding activity ****
            =========================================
            What activity do you want to add ?
            S => Kinomichi session
            D => Dinner
            L => Lodgement
            B => Go back
            =========================================
            """;
    private static final String ADD_PARTICIPANT_TO_SESSIONS = """
            **** Adding participant to activity ****
            ==========================================
            Select a participant from the list
            Then select the sessions like so : 1;2;3
           ===========================================
            """;

    public static void displayMainMenu(){System.out.println(MAIN_MENU);}
    public static void displayAddParticipant(){System.out.println(ADD_PARTICIPANT);}
    public static void displayAddActivityMenu(){System.out.println(ADD_ACTIVITY);}
    public static void displayAddPrticipantToSession(){System.out.println(ADD_PARTICIPANT_TO_SESSIONS);}

}
