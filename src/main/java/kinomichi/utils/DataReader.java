package kinomichi.utils;

import kinomichi.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DataReader {
    public static ParticipantsList participantList = new ParticipantsList();
    public static FullEvent kinomichiEvent = new FullEvent();

    public static String[] readSingleLineCSV(String toRead){
        return toRead.split(";");
    }

    public static void readFromCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/kinomichi/rsc/data.csv"))) {
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
                new KinomichiSesison(
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
}
