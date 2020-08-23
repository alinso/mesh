package data;

import config.ConfigProperties;

public abstract class AbstractDtedCreator {


    protected int dimensionX = Integer.parseInt(ConfigProperties.getInstance().getConfig("dimensionX"));
    protected int dimensionY = Integer.parseInt(ConfigProperties.getInstance().getConfig("dimensionY"));
    protected int spaceBetweenPoints = Integer.parseInt(ConfigProperties.getInstance().getConfig("spaceBetweenPoints"));

    protected int[][] elevationValues = new int[dimensionX][dimensionY];
    protected float[] coordinates = new float[dimensionX * dimensionY * 3];

    protected void createCoordinatedSystemForMesh() {

        int coodinateArrayIndex = 0;

        for (int i = 0; i < elevationValues.length; i++) {
            for (int j = 0; j < elevationValues[i].length; j++) {
                coordinates[coodinateArrayIndex++] = i * spaceBetweenPoints;
                coordinates[coodinateArrayIndex++] = elevationValues[i][j];
                coordinates[coodinateArrayIndex++] = j * spaceBetweenPoints;
            }
        }
        coordinates.toString();
    }


    public float[] getCoordinates() {
        return coordinates;
    }




}
