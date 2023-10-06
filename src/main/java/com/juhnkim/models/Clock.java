package com.juhnkim.models;

import com.juhnkim.interfaces.ClockInterface;
import com.juhnkim.interfaces.StateInterface;

import java.time.LocalDate;
import java.time.LocalTime;

public class Clock implements ClockInterface, StateInterface {

    private LocalTime localTime = LocalTime.now();
    private LocalDate localDate = LocalDate.now();
    private STATE currentState = STATE.DisplayTime;


    public enum STATE {
        DisplayTime,
        ChangeTime,
        DisplayDate,
        ChangeDate
    }


    @Override
    public void displayTime() {
        if(currentState == STATE.DisplayTime) {
            System.out.println(localTime);
        } else if(currentState == STATE.DisplayDate) {
            changeState();
        } else if(currentState == STATE.ChangeTime) {
            set();
        } else {
            System.out.println("Invalid State");
        }
    }

    @Override
    public void displayDate() {
        if(currentState == STATE.DisplayDate) {
            System.out.println(localDate);
        } else if(currentState == STATE.DisplayTime) {
            changeState();
        } else if(currentState == STATE.ChangeDate) {
            set();
        } else {
            System.out.println("Invalid State");
        }
    }

    @Override
    public void changeTime(String userInput) {
        if(currentState == STATE.ChangeTime) {
            setLocalTime(LocalTime.parse(userInput));
        } else if(currentState == STATE.DisplayTime) {
            readyToSet();
        } else {
            System.out.println("Invalid State");
        }
    }

    @Override
    public void changeDate(String userInput) {
        if(currentState == STATE.ChangeDate) {
            setLocalDate(LocalDate.parse(userInput));
        } else if(currentState == STATE.DisplayDate) {
            readyToSet();
        } else {
            System.out.println("Invalid State");
        }
    }


    @Override
    public void changeState() {
        if(getCurrentState() == STATE.DisplayTime) {
            currentState = STATE.DisplayDate;
        } else if(getCurrentState() == STATE.DisplayDate){
            currentState = STATE.DisplayTime;
        }
    }

    @Override
    public void readyToSet() {
        if(getCurrentState() == STATE.DisplayTime) {
            currentState = STATE.ChangeTime;
        } else if(getCurrentState() == STATE.DisplayDate) {
            currentState = STATE.ChangeDate;
        }
    }

    @Override
    public void set() {
        if(getCurrentState() == STATE.ChangeTime) {
            currentState = STATE.DisplayTime;
        } else if(getCurrentState() == STATE.ChangeDate) {
            currentState = STATE.DisplayDate;
        } else {
            System.out.println("Invalid State");
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

    public STATE getCurrentState() {
        return currentState;
    }

    public void setCurrentState(STATE currentState) {
        this.currentState = currentState;
    }
}
