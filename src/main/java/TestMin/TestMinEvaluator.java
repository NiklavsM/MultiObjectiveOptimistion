package TestMin;

import org.opt4j.core.problem.Evaluator;
import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;

public class TestMinEvaluator implements Evaluator<String> {
    private int[][] faultMatrix;
    public TestMinEvaluator(){
        FaultMatrix fm = new FaultMatrix();
        faultMatrix = fm.loadFaultMatrix();
    }
    @Override
    public Objectives evaluate(String phenotype) {
        Objectives objectives = new Objectives();
        objectives.add("TestSize", Sign.MIN, evaluateTestSizeFitness(phenotype));
        objectives.add("FaultCoverage", Sign.MAX, evaluateFaultCovFitness(phenotype));
        return objectives;
    }

    private int evaluateTestSizeFitness(String phenotype) {
        int count = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1')
                count++;
        }
        return count;
    }

    private double evaluateFaultCovFitness(String phenotype) {
//        FM.readData(1000, 38);// Ugh! Do this once somewhere...
        int faults = 38; // Again, should be injected
        boolean[] faultsHit = new boolean[faults];
        for (int i = 0; i < faults; i++)
            faultsHit[i] = false;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
// mark the faults found by the test
                for (int j = 0; j < faults; j++) {
                    if (faultMatrix[i][j] == 1)
                        faultsHit[j] = true;
                }
            }
        }
// total collection of faults found
        int count = 0;
        for (int i = 0; i < faults; i++) {
            if (faultsHit[i])
                count++;
        }
        return count;
    }
}