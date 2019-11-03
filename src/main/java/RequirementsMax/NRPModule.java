package RequirementsMax;

import org.opt4j.core.problem.ProblemModule;

public class NRPModule extends ProblemModule {
    protected void config() {
        bindProblem(NRPCreator.class, NRPDecoder.class, NRPEvaluator.class);
    }
}