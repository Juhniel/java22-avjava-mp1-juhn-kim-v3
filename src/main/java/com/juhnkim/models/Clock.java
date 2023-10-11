package com.juhnkim.models;

import com.juhnkim.interfaces.ClockInterface;
import com.juhnkim.interfaces.StateInterface;
import com.juhnkim.utils.ClockState;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Clock implements ClockInterface, StateInterface {

    // Define the format for displaying time and date
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Initialize the current time, date, and state
    private LocalTime localTime = LocalTime.now();
    private LocalDate localDate = LocalDate.now();
    private ClockState currentState = ClockState.DisplayTime;

    /**
     * Display the current time.
     */
    @Override
    public void displayTime() {
        if (currentState == ClockState.DisplayTime) {
            System.out.println(getLocalTime().format(TIME_FORMATTER));
        }
    }

    /**
     * Display the current date.
     */
    @Override
    public void displayDate() {
        if (currentState == ClockState.DisplayDate) System.out.println(getLocalDate().format(DATE_FORMATTER));
    }

    /**
     * Change the current time based on user input.
     *
     * @param userInput The new time in HH:mm:ss format.
     */
    @Override
    public void changeTime(String userInput) {
        try {
            LocalTime newTime = LocalTime.parse(userInput, TIME_FORMATTER);
            setLocalTime(newTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:mm:ss");
        }
    }


    /**
     * Change the current date based on user input.
     *
     * @param userInput The new date in yyyy-MM-dd format.
     */
    @Override
    public void changeDate(String userInput) {
        if (currentState == ClockState.ChangeDate) {
            try {
                LocalDate newDate = LocalDate.parse(userInput, DATE_FORMATTER);
                setLocalDate(newDate);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    /**
     * Toggle the state between displaying time and displaying date.
     */
    @Override
    public void changeState() {
        if (currentState == ClockState.DisplayTime) {
            currentState = ClockState.DisplayDate;
            displayDate();
            return;
        }
        if (currentState == ClockState.DisplayDate) {
            currentState = ClockState.DisplayTime;
            displayTime();
            return;
        }
        System.out.println("Invalid State");
    }

    /**
     * Prepare the clock to change the time or date.
     */
    @Override
    public void readyToSet() {
        if (currentState == ClockState.DisplayTime) {
            currentState = ClockState.ChangeTime;
            return;
        }
        if (currentState == ClockState.DisplayDate) {
            currentState = ClockState.ChangeDate;
            return;
        }
        System.out.println("Invalid State");
    }

    /**
     * Reset the clock to display mode after changing the time or date.
     */
    @Override
    public void set() {
        if (currentState == ClockState.ChangeTime) {
            currentState = ClockState.DisplayTime;
            displayTime();
            return;
        }

        if (currentState == ClockState.ChangeDate) {
            currentState = ClockState.DisplayDate;
            displayDate();
        }
    }


    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public ClockState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ClockState currentState) {
        this.currentState = currentState;
    }
}
