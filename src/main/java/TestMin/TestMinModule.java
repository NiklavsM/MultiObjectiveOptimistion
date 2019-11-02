package TestMin;

import org.opt4j.core.problem.ProblemModule;
public class TestMinModule extends ProblemModule {
    protected void config() {
        bindProblem(TestMinCreator.class, TestMinDecoder.class, TestMinEvaluator.class);
    }
}