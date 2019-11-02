package TestMin;

import java.io.*;

class FaultMatrix {

    int[][] loadFaultMatrix() {
        String st;
        String[] tokens;
        int[][] tests = new int[1000][38];
        int row = 0;
        InputStream is = getClass().getResourceAsStream("/bigfaultmatrix.txt");
        InputStreamReader isr = new InputStreamReader(is);
        try {
            BufferedReader br = new BufferedReader(isr);
            while ((st = br.readLine()) != null) {
                tokens = st.split(",");
                int numberOfFaults = tokens.length - 1;
                for (int i = 0; i < numberOfFaults; i++) {
                    tests[row][i] = Integer.parseInt(tokens[i + 1]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tests;
    }

}
