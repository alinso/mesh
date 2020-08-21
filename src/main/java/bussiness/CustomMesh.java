package bussiness;

import com.jme3.bounding.BoundingSphere;
import com.jme3.math.Triangle;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;
import config.ConfigProperties;

public class CustomMesh {

    private int dimensionX = Integer.parseInt(ConfigProperties.getConfig("dimensionX"));
    private int dimensionY = Integer.parseInt(ConfigProperties.getConfig("dimensionY"));
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
//        Vector3f [] vertices = new Vector3f[16];
//        vertices[0] = new Vector3f(0, 5, 0);
//        vertices[1] = new Vector3f(0, 0, 10);
//        vertices[2] = new Vector3f(0, 5, 20);
//        vertices[3] = new Vector3f(0, 5, 30);
//        vertices[4] = new Vector3f(10, 0, 0);
//        vertices[5] = new Vector3f(10, 5, 10);
//        vertices[6] = new Vector3f(10, 0, 20);
//        vertices[7] = new Vector3f(10, 0, 30);
//        vertices[8] = new Vector3f(20, 5, 0);
//        vertices[9] = new Vector3f(20, 0, 10);
//        vertices[10] = new Vector3f(20, 5, 20);
//        vertices[11] = new Vector3f(20, 0, 30);
//        vertices[12] = new Vector3f(30, 5, 30);
//        vertices[13] = new Vector3f(30, 0, 30);
//        vertices[14] = new Vector3f(30, 5, 30);
//        vertices[15] = new Vector3f(30, 0, 30);
//
//
//        int[] facesArray = new int[]{0,1,4,  1,4,5, 1,2,5, 2,5,6, 2,3,6, 3,6,7,  4,5,8,  5,8,9,
//                                     5,6,9,  6,9,10, 6,7,10, 7,10,11, 8,9,12, 9,12,13, 9,10,13, 10,13,14, 10,11,14, 11,14,15};

        mesh.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(VertexBuffer.Type.Index,   2, BufferUtils.createIntBuffer(calculateMeshFacePoints()));
        mesh.setMode(Mesh.Mode.Triangles);
        mesh.updateBound();

        return mesh;
    }


    public float[] getCoordinates() {
        return coordinates;
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
