package com.juhnkim.models;

import com.juhnkim.interfaces.ClockInterface;
import com.juhnkim.interfaces.StateInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Clock implements ClockInterface, StateInterface {

    // Define the format for displaying time and date
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Initialize the current time, date, and state
    private LocalTime localTime = LocalTime.now();
    private LocalDate localDate = LocalDate.now();
    private ClockState currentState = ClockState.DisplayTime;

    /**
     * Display the current time.
     * The behavior depends on the current state of the clock.
     */
    @Override
    public void displayTime() {
        if(currentState == ClockState.DisplayTime) {
            System.out.println(localTime.format(TIME_FORMATTER));
        } else if(currentState == ClockState.DisplayDate) {
            changeState();
        } else if(currentState == ClockState.ChangeTime) {
            set();
        } else {
            System.out.println("Invalid State");
        }
    }

    /**
     * Display the current date.
     * The behavior depends on the current state of the clock.
     */
    @Override
    public void displayDate() {
        if(currentState == ClockState.DisplayDate) {
            System.out.println(localDate.format(DATE_FORMATTER));
        } else if(currentState == ClockState.DisplayTime) {
            changeState();
        } else if(currentState == ClockState.ChangeDate) {
            set();
        } else {
            System.out.println("Invalid State");
        }
    }

    /**
     * Change the current time based on user input.
     * The behavior depends on the current state of the clock.
     * @param userInput The new time in HH:MM format.
     */
    @Override
    public void changeTime(String userInput) {
        if(currentState == ClockState.ChangeTime) {
            try {
                LocalTime newTime = LocalTime.parse(userInput, TIME_FORMATTER);
                setLocalTime(newTime);
            } catch(DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM.");
            }
        } else if(currentState == ClockState.DisplayTime) {
            readyToSet();
        } else {
            System.out.println("Invalid State");
        }
    }

    /**
     * Change the current date based on user input.
     * The behavior depends on the current state of the clock.
     * @param userInput The new date in yyyy-MM-dd format.
     */
    @Override
    public void changeDate(String userInput) {
        if(currentState == ClockState.ChangeDate) {
            try {
                LocalDate newDate = LocalDate.parse(userInput, DATE_FORMATTER);
                setLocalDate(newDate);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        } else if(currentState == ClockState.DisplayDate) {
            readyToSet();
        } else {
            System.out.println("Invalid State");
        }
    }

    /**
     * Change the state between displaying time and displaying date.
     */
    @Override
    public void changeState() {
        if(currentState == ClockState.DisplayTime) {
            currentState = ClockState.DisplayDate;
        } else if(currentState == ClockState.DisplayDate){
            currentState = ClockState.DisplayTime;
        }
    }

    /**
     * Prepare the clock to change the time or date.
     * This sets the state to either ChangeTime or ChangeDate.
     */
    @Override
    public void readyToSet() {
        if(currentState == ClockState.DisplayTime) {
            currentState = ClockState.ChangeTime;
        } else if(currentState == ClockState.DisplayDate) {
            currentState = ClockState.ChangeDate;
        }
    }

    /**
     * Set the clock back to display mode after a time or date has been changed.
     */
    @Override
    public void set() {
        if(currentState == ClockState.ChangeTime) {
            currentState = ClockState.DisplayTime;
        } else if(currentState == ClockState.ChangeDate) {
            currentState = ClockState.DisplayDate;
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
