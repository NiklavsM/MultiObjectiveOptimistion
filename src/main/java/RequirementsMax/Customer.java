package RequirementsMax;

import java.util.ArrayList;

public class Customer {
    private double value;
    private ArrayList<String> desiredRequirements;

    public Customer(double value) {
        this.value = value;
        desiredRequirements = new ArrayList<String>();
    }

    public double getValue() {
        return value;
    }

    public void addDesiredRequirements(String reqId) {
        desiredRequirements.add(reqId);
    }

    public ArrayList<String> getDesiredRequirements() {
        return desiredRequirements;
    }
}