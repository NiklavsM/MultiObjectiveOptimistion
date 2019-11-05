package RequirementsMax;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NRPEvaluator implements Evaluator<String> {
    private final String fileName = "/nrp1realistic.txt";
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
        String reqId;
        int phenotypeLength = phenotype.length();
        for (int i = 0; i < phenotypeLength; i++) {
            if (phenotype.charAt(i) == '1') {
                reqId = Integer.toString(i + 1);
                for (Customer c : customers) {
                    ArrayList<String> dr = c.getDesiredRequirements();
                    int index = dr.indexOf(reqId);
                    if (index != -1) {
                        score += (c.getValue() * (dr.size() - index)); // the requirements that are first in order and requested by more important customers are prioritised
                    }
                }
            }
        }
        return score;
    }
}