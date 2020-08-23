package data;

import com.jme3.scene.Mesh;

public class MeshFactory {


    public static Mesh getMesh() {
//        DTEDFromRandom randomDTED = new DTEDFromRandom();
//        randomDTED.createDTED();

        DTEDFromValues  dtedFromValues = new DTEDFromValues();
        dtedFromValues.createDTED();

        CustomMesh customMesh  = new CustomMesh();
        customMesh.setCoordinates(dtedFromValues.getCoordinates());

        return customMesh.getMesh();
    }

}
