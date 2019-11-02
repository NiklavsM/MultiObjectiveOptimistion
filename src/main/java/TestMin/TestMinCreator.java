package TestMin;

import java.util.Random;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.genotype.BooleanGenotype;
public class TestMinCreator implements Creator<BooleanGenotype>{
    private Random random = new Random();
    public BooleanGenotype create() {
        BooleanGenotype genotype = new BooleanGenotype();
        genotype.init(random, 1000); // Better to use injection
        return genotype;
    }
}