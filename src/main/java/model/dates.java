package model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class dates {

    public static String[] datesTable() {
        String[] mainDates = new String[4];
        String url1 = "https://www.gpw.pl/archiwum-notowan-full?type=10&instrument=&date=02-04-2020";
        String url2 = "https://www.gpw.pl/archiwum-notowan-full?type=10&instrument=&date=01-04-2020";
        String url3 = "https://www.gpw.pl/archiwum-notowan-full?type=10&instrument=&date=31-03-2020";
        String url4 = "https://www.gpw.pl/archiwum-notowan-full?type=10&instrument=&date=30-03-2020";
        mainDates[0] = url1;
        mainDates[1] = url2;
        mainDates[2] = url3;
        mainDates[3] = url4;
        return mainDates;
    }
}