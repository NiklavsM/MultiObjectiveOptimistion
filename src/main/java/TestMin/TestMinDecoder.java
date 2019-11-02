package TestMin;

import org.opt4j.core.problem.Decoder;
import org.opt4j.core.genotype.BooleanGenotype;
public class TestMinDecoder implements Decoder<BooleanGenotype, String>{
    public String decode(BooleanGenotype genotype) {
        StringBuilder phenotype = new StringBuilder();
        for (Boolean testActive : genotype) {
            if (testActive)
                phenotype.append("1");
            else
                phenotype.append("0");
        }
        return phenotype.toString();
    }
}
