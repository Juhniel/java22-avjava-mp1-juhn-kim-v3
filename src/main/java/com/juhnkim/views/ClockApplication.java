package com.juhnkim.views;

import com.juhnkim.models.Clock;

import java.util.Scanner;

public class ClockApplication {

    Clock clock = new Clock();

    public void clockMenu() {
        Scanner scan = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("0")) {
            System.out.println("------------------------------------------------------");
            System.out.println("Current State: " + clock.getCurrentState());
            System.out.println("------------------------------------------------------");
            System.out.println("""
                    -- CLOCK --\s
                    1. Display Time \s
                    2. Display Date\s
                    3. Edit time\s
                    4. Edit Date\s
                    0. Exit""");

            userInput = scan.nextLine();
            switch (userInput) {
                case "1":
                    clock.displayTime();
                    break;
                case "2":
                    clock.displayDate();
                    break;
                case "3":
                    System.out.println("Enter new time");
                    userInput = scan.nextLine();
                    clock.changeTime(userInput);
                    break;
                case "4":
                    System.out.println("Enter new date");
                    userInput = scan.nextLine();
                    clock.changeDate(userInput);
                    break;
                case "0":
                    System.out.println("Bye :(");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
