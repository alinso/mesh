package bussiness;

import config.ConfigProperties;

public class RandomCustomDTED {


    private int dimensionX = Integer.parseInt(ConfigProperties.getConfig("dimensionX"));
    private int dimensionY = Integer.parseInt(ConfigProperties.getConfig("dimensionY"));
    private int spaceBetweenPoints = Integer.parseInt(ConfigProperties.getConfig("spaceBetweenPoints"));

    private int[][] elevationValues = new int[dimensionX][dimensionY];
    private float[] coordinates = new float[dimensionX * dimensionY * 3];


    private void populateArray() {
        for (int i = 0; i < elevationValues.length; i++) {
            for (int j = 0; j < elevationValues[i].length; j++) {
                elevationValues[i][j] = ((int) (Math.random() * Integer.parseInt(ConfigProperties.getConfig("maxHeightOfTerrain")))+Math.abs(i+j-100));
            }
        }
    }


    private void createCoordinatedSystemForMesh() {

        int coodinateArrayIndex = 0;

        for (int i = 0; i < elevationValues.length; i++) {
            for (int j = 0; j < elevationValues[i].length; j++) {
                coordinates[coodinateArrayIndex++] = i * spaceBetweenPoints;
                coordinates[coodinateArrayIndex++] = elevationValues[i][j];
                coordinates[coodinateArrayIndex] = j * spaceBetweenPoints;
            }
        }
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void createDTED(){
        populateArray();
        createCoordinatedSystemForMesh();
    }




}
