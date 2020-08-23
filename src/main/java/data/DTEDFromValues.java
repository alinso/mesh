package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class DTEDFromValues extends AbstractDtedCreator {


    private void populateDTEDArray() {


        InputStream is = null;
        is = getClass().getClassLoader().getResourceAsStream("agri_dted.txt");

        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = null;
        StringBuilder sb = new StringBuilder();

        try {
            line = buf.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String fileAsString = sb.toString();
        String[] values = fileAsString.split(" ");

        Integer[] intValues = new Integer[dimensionY*dimensionY];
        for(int i =0;i<dimensionY*dimensionX;i++){
            intValues[i] = Integer.parseInt(values[i]);
        }


       int min = (int) Collections.min(Arrays.asList(intValues));

        int index = 0;
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                elevationValues[i][j] = intValues[index]/10;
                index++;
            }
        }
    }


    public void createDTED() {
        populateDTEDArray();
        createCoordinatedSystemForMesh();
    }
}
