package RequirementsMax;

import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Creator;

import java.util.Random;

public class NRPCreator implements Creator<BooleanGenotype>{
    private Random random = new Random();
    public BooleanGenotype create() {
        BooleanGenotype genotype = new BooleanGenotype();
        genotype.init(random, 3502); // Better to use injection
        return genotype;
    }
}