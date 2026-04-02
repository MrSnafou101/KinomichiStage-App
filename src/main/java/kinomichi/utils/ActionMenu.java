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
            T => Take a room
            A => Add activity
            U => Update activity
            L => list participants
            I => Other infos
            S => Save data to CSV
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
            
            press B or b to go back
           ===========================================
            """;
    private static final String UPDATE_ACTIVITY_MENU = """
            **** Update activity ****
            ==========================================
            A => Set/update session's animator
            T => Update activity
            D => Delete activity
            
            press B or b to go back
            ==========================================
            """;
    private static final String DELETE_OPTION = """
            **** deleteing ****
            ==========================================
            Select an activity to delete by typing the Id
            
            press B or b to go back
            ==========================================
            """;
    private static final String UPDATE_ACTIVITY = """
            **** updating ****
            ==========================================
            Select an activity to update
            Change values or press enter to keep the current one
            
            
            press B or b to go back
            ==========================================
            """;
    private static final String LISTING_MENU = """
            #### Listing ####
            ==========================================
            What do you want to see
            
            P => participants
            P2 => participants + registered sessions
            S => sessions
            S2 => sessions + participants
            D => Dinners
            D2 => Dinners + participants
            L => lodgement
            
            press B or b to go back
            ==========================================
            """;

    public static void displayMainMenu(){System.out.println(MAIN_MENU);}
    public static void displayAddParticipant(){System.out.println(ADD_PARTICIPANT);}
    public static void displayAddActivityMenu(){System.out.println(ADD_ACTIVITY);}
    public static void displayAddParticipantToSession(){System.out.println(ADD_PARTICIPANT_TO_SESSIONS);}
    public static void displayUpdateMenu(){System.out.println(UPDATE_ACTIVITY_MENU);}
    public static void displayDeleteOption(){System.out.println(DELETE_OPTION);}
    public static void displayUpdateOption(){System.out.println(UPDATE_ACTIVITY);}
    public static void displayListingMenu(){System.out.println(LISTING_MENU);}

}
