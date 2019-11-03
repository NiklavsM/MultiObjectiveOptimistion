package RequirementsMax;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private int value;
    private Set<String> desiredRequirements;

    public Customer(int value) {
        this.value = value;
        desiredRequirements = new HashSet<String>();
    }

    public Customer(int value, Set<String> desiredRequirements) {
        this.value = value;
        this.desiredRequirements = new HashSet<String>(desiredRequirements);
    }

    public int getValue() {
        return value;
    }

    public void addDesiredRequirements(String reqId) {
        desiredRequirements.add(reqId);
    }

    public Set<String> getDesiredRequirements() {
        return desiredRequirements;
    }
}