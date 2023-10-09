package com.juhnkim.interfaces;

public interface StateInterface {
    public void changeState(); // Method for changing state between DisplayTime and DisplayDate STATE
    public void readyToSet(); // Method for changing state between DisplayTime to ChangeTime or DisplayDate to ChangeDate STATE
    public void set(); // Method for changing state between ChangeTime to DisplayTime or ChangeDate to DisplayDate STATE

}
