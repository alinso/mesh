package bussiness;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import config.ConfigProperties;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
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


    private PerspectiveCamera  createCamera(){
      PerspectiveCamera camera = new PerspectiveCamera(false);
      camera.setTranslateX(0);
      camera.setTranslateY(0);
      camera.setTranslateZ(0);
      return  camera;
  }

  private void createRotation(MeshView meshView){
      RotateTransition rt = new RotateTransition(Duration.seconds(18), meshView);
      rt.setCycleCount(Animation.INDEFINITE);
      rt.setFromAngle(0);
      rt.setToAngle(60);
      rt.setAutoReverse(true);
      rt.setAxis(Rotate.X_AXIS);
      rt.play();
  }

  private PointLight createLight(Color color){
      PointLight light = new PointLight();
      light.setColor(color);
      light.setTranslateX(0);
      light.setTranslateY(0);
      light.setTranslateZ(0);
      return light;
  }
    private  void decorateDTEDMesh(){
        RandomCustomDTED randomCustomDTED = new RandomCustomDTED();
        randomCustomDTED.createDTED();


        customMesh.setCoordinates(randomCustomDTED.getCoordinates());

        originalMeshView =  customMesh.getMesh();
    }

    @Override
    public void simpleInitApp() {
        decorateDTEDMesh();
        Geometry geo = new Geometry("OurMesh", customMesh.getMesh()); // using our custom mesh object
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geo.setMaterial(mat);
        rootNode.attachChild(geo);           // make the cube appear in the scene
    }




    public void updateMesh(){
        decorateDTEDMesh();
//        SimplifyMesh simplifyMesh =  new SimplifyMesh(customMesh);
   //     createScene(this.stage);
    }

    private Button createButton(){
        Button button = new Button (); //Creates Button
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



















