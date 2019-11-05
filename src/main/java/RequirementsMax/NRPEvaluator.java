package RequirementsMax;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NRPEvaluator implements Evaluator<String> {
    private final String fileName = "/nrp1classic.txt";
    private List<Customer> customers;
    private int[] requirementsCost;

    public NRPEvaluator() {
        CustomerReader cr = new CustomerReader();
        customers = cr.getCustomers(fileName);
        RequirementReader rr = new RequirementReader();
        requirementsCost = rr.getRequirementCosts(fileName);
    }

    @Override
    public Objectives evaluate(String phenotype) {
        Objectives objectives = new Objectives();
        objectives.add("Customer Value", Sign.MAX, evaluateCustomerValueFitness(phenotype));
        objectives.add("Requirement Cost", Sign.MIN, evaluateReqCostFitness(phenotype));
        return objectives;
    }

    private double evaluateReqCostFitness(String phenotype) {
        double count = 0.0;
        int phenotypeLength = phenotype.length();
        for (int i = 0; i < phenotypeLength; i++) {
            if (phenotype.charAt(i) == '1')
                count += requirementsCost[i]; // Requirements number is also its weight
        }
        return count;
    }

    private double evaluateCustomerValueFitness(String phenotype) {

        double score = 0.0;
        String reqId = "";
        List<Customer> customersTemp = new ArrayList<Customer>();
        for (Customer c : customers) { // Deep copy
            customersTemp.add(new Customer(c.getValue(), c.getDesiredRequirements()));
        }
        int phenotypeLength = phenotype.length();
        for (int i = 0; i < phenotypeLength; i++) {
            if (phenotype.charAt(i) == '1')
                reqId = Integer.toString(i + 1);
            for (Customer c : customersTemp) {
                Set<String> dr = c.getDesiredRequirements();
                dr.remove(reqId);
                if (dr.isEmpty()) { // Customer satisfied
                    score += c.getValue();
                }
            }
        }
        return score;
    }
}