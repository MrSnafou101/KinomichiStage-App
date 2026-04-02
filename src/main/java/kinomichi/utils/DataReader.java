package kinomichi.utils;

import kinomichi.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class DataReader {
    private static final String FILE_PATH = "src/main/java/kinomichi/rsc/data.csv";
    public static ParticipantsList participantList = new ParticipantsList();
    public static FullEvent kinomichiEvent = new FullEvent();

    public static String[] readSingleLineCSV(String toRead){
        return toRead.split(";");
    }

    public static void readFromCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line, prefix, rest;
            while ((line = br.readLine()) != null) {
                if(!line.startsWith("!!")){
                    //System.out.println(line);
                    prefix = line.substring(0,2);
                    rest = line.substring(2);
                    switch (prefix){
                        //participants
                        case "**" -> buildParticipant(rest);
                        //sessions
                        case "##" -> buildSession(rest);
                        //dinner
                        case "&&" -> buildDinner(rest);
                        //lodgement
                        case "==" -> buildLodgement(rest);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    private static void buildLodgement(String rest) {
        String[] data = readSingleLineCSV(rest);
        LocalDate date = DataParser.makeDateFromString(data[1]);
        LocalTime time = LocalTime.of(0,0);
        kinomichiEvent.addActivity(
                new Lodgement(
                        data[0],
                        LocalDateTime.of(date, time),
                        Integer.parseInt(data[2]),
                        null
                )
        );
    }

    private static void buildDinner(String rest) {
        String[] data = readSingleLineCSV(rest);
        LocalDate date = DataParser.makeDateFromString(data[1]);
        LocalTime time = DataParser.makeTimeFromString(data[2]);
        kinomichiEvent.addActivity(
                new EventDinner(
                        data[0],
                        LocalDateTime.of(date, time),
                        data[3],
                        null
                )
        );

    }

    private static void buildSession(String rest) {
        String[] data = readSingleLineCSV(rest);
        LocalDate date = DataParser.makeDateFromString(data[1]);
        LocalTime time = DataParser.makeTimeFromString(data[2]);
        kinomichiEvent.addActivity(
                new KinomichiSession(
                        data[0],
                        LocalDateTime.of(date, time),
                        Long.parseLong(data[3]),
                        null
                )
        );
    }

    private static void buildParticipant(String rest) {
        String[] data = readSingleLineCSV(rest);
        participantList.addParticipant(data);
    }

    public static void saveIntoCSV(ParticipantsList participantList, FullEvent kinomichiEvent){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("!!participants");
            writer.newLine();
            writer.write("!!firstname;lastname;phoneNumber;email;clubName;participantType");
            writer.newLine();
            for(Participant p : participantList.getParticipants()){
                writer.write(p.toSaveString());
                writer.newLine();
            }
            writer.write("!!session");
            writer.newLine();
            writer.write("!!name;dd/mm/yyy;hh:mm;duration");
            writer.newLine();
            for(Activity a : kinomichiEvent.getFilteredActivities("session")){
                writer.write(a.toSaveString());
                writer.newLine();
            }
            writer.write("!!dinner");
            writer.newLine();
            writer.write("!!name;dd/mm/yyy;hh:mm;description");
            writer.newLine();
            for(Activity a : kinomichiEvent.getFilteredActivities("dinner")){
                writer.write(a.toSaveString());
                writer.newLine();
            }
            writer.write("!!lodgement");
            writer.newLine();
            writer.write("!!name;dd/mm/yyy;nbBed");
            writer.newLine();
            for(Activity a : kinomichiEvent.getFilteredActivities("lodgement")){
                writer.write(a.toSaveString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

}
