package RequirementsMax;

import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Decoder;

public class NRPDecoder implements Decoder<BooleanGenotype, String>{
    public String decode(BooleanGenotype genotype) {
        StringBuilder phenotype = new StringBuilder();
        for (Boolean requirementActive : genotype) {
            if (requirementActive)
                phenotype.append("1");
            else
                phenotype.append("0");
        }
        return phenotype.toString();
    }
}