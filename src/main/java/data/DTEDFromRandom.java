package data;

import config.ConfigProperties;

public class DTEDFromRandom extends AbstractDtedCreator {


    private void populateArray() {
        for (int i = 0; i < elevationValues.length; i++) {
            for (int j = 0; j < elevationValues[i].length; j++) {
                elevationValues[i][j] = ((int) (Math.random() * Integer.parseInt(ConfigProperties.getInstance().getConfig("maxHeightOfTerrain"))));
            }
        }
    }

    public void createDTED(){
        populateArray();
        createCoordinatedSystemForMesh();
    }

}
