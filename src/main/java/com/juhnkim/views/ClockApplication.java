package com.juhnkim.views;

import com.juhnkim.models.Clock;
import com.juhnkim.models.ClockState;

import java.util.Scanner;

public class ClockApplication {

    Clock clock = new Clock();

    public void clockMenu() {
        Scanner scan = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("0")) {
            ClockState currentState = clock.getCurrentState();
            System.out.println("------------------------------------------------------");
            System.out.println("Current State: " + currentState);
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
                    if (currentState == ClockState.DisplayDate) {
                        clock.changeState();
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;
                case "2":
                    if (currentState == ClockState.DisplayTime) {
                        clock.changeState();
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;
                case "3":
                    if (currentState == ClockState.DisplayTime) {
                        clock.readyToSet();
                        System.out.println("Enter new time 'HH:MM:SS'");
                        userInput = scan.nextLine();
                        clock.changeTime(userInput);
                        clock.set();
                    } else {
                        System.out.println("Invalid state for editing time.");
                    }
                    break;
                case "4":
                    if (currentState == ClockState.DisplayDate) {
                        clock.readyToSet();
                        System.out.println("Enter new date 'yyyy-mm-dd");
                        userInput = scan.nextLine();
                        clock.changeDate(userInput);
                        clock.set();
                    } else {
                        System.out.println("Invalid state for editing date.");
                    }
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
