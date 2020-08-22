package randomdata;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;
import config.ConfigProperties;

public class CustomMesh {

    private int dimensionX = Integer.parseInt(ConfigProperties.getInstance().getConfig("dimensionX"));
    private int dimensionY = Integer.parseInt(ConfigProperties.getInstance().getConfig("dimensionY"));
    private float coordinates[];
    private int[] facePointsArray = new int[(dimensionX - 1) * (dimensionY - 1) * 2 * 3];


    private int[] calculateMeshFacePoints() {

        int pointsArrayIndex = 0;
        for (int j = 1; j < dimensionX; j++) {
            int multiplyerBig = j * dimensionX;
            int multiplyerSmall = (j - 1) * dimensionX;

            boolean even = true;

            for (int i = 0; i < dimensionY; i++) {
                if (i == (dimensionY - 1) && even)
                    break;

                if (even) {

                    facePointsArray[pointsArrayIndex++] = (multiplyerBig + i);
                    facePointsArray[pointsArrayIndex++] = (multiplyerSmall + i + 1);
                    facePointsArray[pointsArrayIndex++] = (multiplyerSmall + i);


                } else {
                    facePointsArray[pointsArrayIndex++] = (multiplyerBig + i);
                    facePointsArray[pointsArrayIndex++] = (multiplyerBig + i - 1);
                    facePointsArray[pointsArrayIndex++] = (multiplyerSmall + i);

                    i--;
                }


                even = !even;
            }
        }
        return facePointsArray;
    }

    public Mesh getMesh() {
        Mesh mesh =  new Mesh();
        Vector3f [] vertices = new Vector3f[dimensionX*dimensionY];
        int verticesIndex = 0;

        for(int i=0;i<coordinates.length;i++){

            float x = coordinates[i++];
            float y = coordinates[i++];
            float z = coordinates[i];
            vertices[verticesIndex]= new Vector3f(x,y,z);
            verticesIndex++;
        }


        mesh.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(VertexBuffer.Type.Index,   2, BufferUtils.createIntBuffer(calculateMeshFacePoints()));
        mesh.setBuffer(VertexBuffer.Type.Normal,   3, BufferUtils.createFloatBuffer(vertices));
        mesh.updateBound();

        return mesh;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }
}
