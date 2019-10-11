package exercise9.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person
{
    private SimpleStringProperty name;
    private SimpleIntegerProperty amountOfSteps;
    
    public Person() {
        this("", 0);
    }
    
    public Person(final String name, final int amountOfTries) {
        this.name = new SimpleStringProperty();
        this.amountOfSteps = new SimpleIntegerProperty();
        this.setName(name);
        this.setAmountOfSteps(amountOfTries);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public void setName(final String name) {
        this.name.set(name);
    }
    
    public int getAmountOfSteps() {
        return this.amountOfSteps.get();
    }
    
    public void setAmountOfSteps(final int amountOfTries) {
        this.amountOfSteps.set(amountOfTries);
    }
}