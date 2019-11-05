package RequirementsMax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequirementReader {
    int[] getRequirementCosts(String file) {
        String st;
        String[] tokens;
        InputStream is = getClass().getResourceAsStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        try {
            BufferedReader br = new BufferedReader(isr);
            int[] cost = new int[Integer.parseInt(br.readLine())];
            st = br.readLine();
            tokens = st.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                cost[i] = Integer.parseInt(tokens[i]);
            }
            return cost;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[0];
    }
}
