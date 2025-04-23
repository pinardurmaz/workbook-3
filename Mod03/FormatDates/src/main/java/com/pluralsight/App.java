package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        dateFormat1(now);
        dateFormat2(now);
        System.out.println(getFormat3(now));
        dateFormat4(now);
        dateFormat5(now);
        dateFormat6(now);
    }

    // MM-dd-yyyy
    static void dateFormat1(LocalDateTime now) {
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(format1));
    }

    // yyyy/MM/dd
    static void dateFormat2(LocalDateTime now) {
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(now.format(format2));
    }

    // "MMMM dd, yyyy"
    static String getFormat3(LocalDateTime now) {
        DateTimeFormatter format3 = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return format3.format(now);
    }

    // "EEEE, MMM d, yyyy"
    static void dateFormat4(LocalDateTime time) {
        DateTimeFormatter format4 = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy");
        System.out.println(time.format(format4));
    }

    // (hh:mm) + GMT
    static void dateFormat5(LocalDateTime now) {
        DateTimeFormatter format5 = DateTimeFormatter.ofPattern("hh:mm");
        System.out.println(now.format(format5) + " ß display in GMT time");
    }

    // Time and date
    static void dateFormat6(LocalDateTime now) {
        DateTimeFormatter format6 = DateTimeFormatter.ofPattern("H:mm 'on' dd MMM yyyy");
        System.out.println(now.format(format6) + " ß display in your local time zone");
    }
}