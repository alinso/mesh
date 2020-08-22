package randomdata;

import com.jme3.scene.Mesh;

public class MeshFactory {


    public static Mesh getMesh() {
        RandomDTED randomDTED = new RandomDTED();
        randomDTED.createDTED();

        CustomMesh customMesh  = new CustomMesh();
        customMesh.setCoordinates(randomDTED.getCoordinates());

        return customMesh.getMesh();
    }

}
