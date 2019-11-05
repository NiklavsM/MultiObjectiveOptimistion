package RequirementsMax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CustomerReader {

    List<Customer> getCustomers(String file) {
        String st;
        String[] tokens;
        List<Customer> customers = new ArrayList<Customer>();
        InputStream is = getClass().getResourceAsStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        try {
            BufferedReader br = new BufferedReader(isr);
            for (int i = 0; i < 2; i++) { // ignore first 2 lines
                br.readLine();
            }
            while ((st = br.readLine()) != null) {
                tokens = st.split(" ");
                Customer c = new Customer(Integer.parseInt(tokens[0]));
                for (int i = 1; i < tokens.length; i++) {
                    c.addDesiredRequirements(tokens[i]);
                }
                customers.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
}