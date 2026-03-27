package kinomichi.utils;

public class DataReader {

    public static String[] readSingleLineCSV(String toRead){
        return toRead.split(";");
    }

}
