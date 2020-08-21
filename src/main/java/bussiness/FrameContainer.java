package bussiness;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FrameContainer extends SimpleApplication {

    private Group root;
    private CustomMesh customMesh = new CustomMesh();
    private Mesh originalMeshView;
    private Stage stage;
    private Geometry meshGeometry;

    private PerspectiveCamera createCamera() {
        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        return camera;
    }

    private void createRotation(MeshView meshView) {
        RotateTransition rt = new RotateTransition(Duration.seconds(18), meshView);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setFromAngle(0);
        rt.setToAngle(60);
        rt.setAutoReverse(true);
        rt.setAxis(Rotate.X_AXIS);
        rt.play();
    }

    private PointLight createLight(Color color) {
        PointLight light = new PointLight();
        light.setColor(color);
        light.setTranslateX(0);
        light.setTranslateY(0);
        light.setTranslateZ(0);
        return light;
    }

    private void decorateDTEDMesh() {
        RandomCustomDTED randomCustomDTED = new RandomCustomDTED();
        randomCustomDTED.createDTED();

        customMesh.setCoordinates(randomCustomDTED.getCoordinates());

        originalMeshView = customMesh.getMesh();
    }

    @Override
    public void simpleInitApp() {
        decorateDTEDMesh();
        meshGeometry = new Geometry("OurMesh", originalMeshView); // using our custom mesh object
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.DarkGray);
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        meshGeometry.setMaterial(mat);
        rootNode.attachChild(meshGeometry);           // make the cube appear in the scene

        SpotLight sl = new SpotLight();
        sl.setSpotRange(100);
        sl.setSpotOuterAngle(20 * FastMath.DEG_TO_RAD);
        sl.setSpotInnerAngle(15 * FastMath.DEG_TO_RAD);
        sl.setDirection(new Vector3f(-0.39820394f, -0.73094344f, 0.55421597f));
        sl.setPosition(new Vector3f(-64.61567f, -87.615425f, -202.41328f));
        rootNode.addLight(sl);

        rotateInput();
        cam.setLocation(new Vector3f(0, 100, 400));
        viewPort.setBackgroundColor(ColorRGBA.White);
    }

    @Override
    public void simpleUpdate(float tpf) {
        meshGeometry.setLocalTranslation(4, 0, 2);
    }

    public void rotateInput() {
        inputManager.addMapping("RotateL", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("RotateR", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addListener(analogListener, new String[]{ "RotateL", "RotateR"});
    }


    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals("RotateL")) {
                meshGeometry.rotate(0, tpf, 0);
            }
            if (name.equals("RotateR")) {
                meshGeometry.rotate(0, -tpf, 0);
            }

        }
    };

    public void updateMesh() {
        decorateDTEDMesh();
//        SimplifyMesh simplifyMesh =  new SimplifyMesh(customMesh);
        //     createScene(this.stage);
    }

    private Button createButton() {
        Button button = new Button(); //Creates Button
        button.setText("Click Me");
        button.setPrefHeight(40.0);
        button.setPrefWidth(100.0);
        button.setLayoutX(1000.0);
        button.setLayoutY(800.0);
        final FrameContainer self = this;
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                self.updateMesh();
            }
        });
        return button;
    }


}



















