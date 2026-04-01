package kinomichi.utils;

import kinomichi.customExceptions.WrongDateFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataParser {

    public static LocalDate makeDateFromString(String dateToParse){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            return LocalDate.parse(dateToParse, formatter);
        }catch(WrongDateFormat e){
            System.out.println("Wrong date format please try again");
            return null;
        }
    }
    public static LocalDate isDatePassed(LocalDate toCheck){
        if(toCheck == null || toCheck.isBefore(LocalDate.now()))return null;
        else return toCheck;
    }
    public static LocalTime makeTimeFromString(String timeToParse){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try{
            return LocalTime.parse(timeToParse, formatter);
        }catch(WrongDateFormat e){
            System.out.println("Wrong time format please try again");
            return null;
        }
    }
}
