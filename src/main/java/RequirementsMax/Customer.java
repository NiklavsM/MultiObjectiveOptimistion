package RequirementsMax;

import java.util.ArrayList;

public class Customer {
    private int value;
    private ArrayList<String> desiredRequirements;

    public Customer(int value) {
        this.value = value;
        desiredRequirements = new ArrayList<String>();
    }

    public int getValue() {
        return value;
    }

    public void addDesiredRequirements(String reqId) {
        desiredRequirements.add(reqId);
    }

    public ArrayList<String> getDesiredRequirements() {
        return desiredRequirements;
    }
}