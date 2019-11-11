package RequirementsMax;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import java.util.*;

public class NRPEvaluator implements Evaluator<String> {
    private final String fileName = "/nrp1realistic.txt";
    private List<Customer> customers;
    private int[] requirementsCost;
    private double totalCustomerValue;
    private double totalRequirementCost;
    private Map<Integer, Double> maxValue = new HashMap<Integer, Double>();
    private Double singleObjectiveMax = 0.0;

    public NRPEvaluator() {
        CustomerReader cr = new CustomerReader();
        customers = cr.getCustomers(fileName);
        totalCustomerValue = 0.0;
        for (Customer c : customers) {
            totalCustomerValue += c.getValue();
        }
        totalRequirementCost = 0.0;
        RequirementReader rr = new RequirementReader();
        requirementsCost = rr.getRequirementCosts(fileName);
        for (int r : requirementsCost) {
            totalRequirementCost += r;
        }
    }

    @Override
    public Objectives evaluate(String phenotype) {
        Objectives objectives = new Objectives();
        objectives.add("Requirement Cost", Sign.MIN, evaluateReqCostFitness(phenotype));
        objectives.add("Customer Value", Sign.MAX, evaluateCustomerValueFitness(phenotype));
//        objectives.add("Single objective score", Sign.MAX, evaluateSingleObjective(phenotype));
        return objectives;
    }

    private double evaluateSingleObjective(String phonotype) {
        double w1 = 0.5; // Can adjust weightings for each of the objectives
        double score = (evaluateCustomerValueFitness(phonotype) * w1) + ((1.0 - w1) * -evaluateReqCostFitness(phonotype));
        if (score > singleObjectiveMax) {
            singleObjectiveMax = score;
            System.out.println("Customer value: " + evaluateCustomerValueFitness(phonotype) + " Requirment cost: " + evaluateReqCostFitness(phonotype));
        }
        return (evaluateCustomerValueFitness(phonotype) * w1) + ((1.0 - w1) * -evaluateReqCostFitness(phonotype));
    }

    private double evaluateReqCostFitness(String phenotype) {
        double count = 0.0;
        int phenotypeLength = phenotype.length();
        for (int i = 0; i < phenotypeLength; i++) {
            if (phenotype.charAt(i) == '1')
                count += requirementsCost[i];
        }
        return count / totalRequirementCost; // normalised to be value between 0 and 1
    }


    // The requirements that are first in order and requested by more important customers are prioritised
    // Also its important to note that if no requirements for the customer is satisfied then the value should be 0 and if all of them are satisfied it should add up to the customer value
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
                        score += (c.getValue() * ((double) (dr.size() - index) / dr.size()) / max(dr.size()));
                    }
                }
            }
        }
        return score / totalCustomerValue; // normalised to be value between 0 and 1
    }

    private double max(int size) { // Used to normalise customer value from 0 to 1.
        double max = 0.0;
        if (maxValue.containsKey(size)) {
            return maxValue.get(size);
        }
        for (double i = 0.0; i < size; i++) {
            max += (size - i) / size;
        }
        maxValue.put(size, max);
        return max;
    }
}