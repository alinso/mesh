package bussiness;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import config.ConfigProperties;
import lib.SimplifyMesh;


public class Visualize3DApp extends SimpleApplication {

    private Mesh originalMesh;
    private Mesh simlifiedMesh;
    private Mesh selectedMesh;
    private Geometry meshGeometry;


    private SimplifyMesh simplifyMesh;


    @Override
    public void simpleInitApp() {

        selectedMesh=originalMesh;
        renderScene();
    }

    private void writeKeyboardExplanations(){
        BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText txt = new BitmapText(fnt, false);
        txt.setBox(new Rectangle(0, 0, settings.getWidth(), settings.getHeight()));
        txt.setSize(fnt.getPreferredSize());
        txt.setColor(ColorRGBA.Black);
        txt.setText("Yön Tuslari : kamera döner \n 1 mesh sola döner \n 2: mesh  saga döner \n 3: Decimation yapar");
        txt.setLocalTranslation(0, txt.getHeight(), 0);
        guiNode.attachChild(txt);

    }
    private void writeMeshDetails(){
        BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText txt = new BitmapText(fnt, false);
        txt.setBox(new Rectangle(0, -100, settings.getWidth(), settings.getHeight()));
        txt.setSize(fnt.getPreferredSize());
        txt.setColor(ColorRGBA.Black);
        txt.setText("Toplam Ucgen Sayisi:"+selectedMesh.getTriangleCount());
        txt.setLocalTranslation(0, txt.getHeight(), 0);
        guiNode.attachChild(txt);
    }

    private void attachMeshToRoot(){
        meshGeometry = new Geometry("DTEDMesh", selectedMesh);
        Material mat = new Material(assetManager, "Common/MatDefs/Shadow/BasicPostShadow.j3md");


       // mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        mat.setReceivesShadows(true);
        meshGeometry.setMaterial(mat);
        rootNode.attachChild(meshGeometry);

    }

    public void renderScene(){

        rootNode.detachAllChildren();
        guiNode.detachAllChildren();
        attachMeshToRoot();
        writeKeyboardExplanations();
        writeMeshDetails();
        setInputListeners();
        cam.setLocation(new Vector3f(500, 350, 1200));
        viewPort.setBackgroundColor(ColorRGBA.LightGray);

    }


    public void setInputListeners() {
        inputManager.addMapping("RotateL", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("RotateR", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping("Simplify", new KeyTrigger(KeyInput.KEY_3));
        inputManager.addListener(analogListener, "RotateL", "RotateR","Simplify");
    }


    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals("RotateL")) {
                meshGeometry.rotate(0, tpf, 0);
            }
            if (name.equals("RotateR")) {
                meshGeometry.rotate(0, -tpf, 0);
            }
            if (name.equals("Simplify")) {
                updateMesh();
            }

        }
    };


    public void updateMesh() {

        Integer targetTriangleCount = (selectedMesh.getTriangleCount() *(100-Integer.parseInt(ConfigProperties.getInstance().getConfig("decimationPercent"))))/100;
        simlifiedMesh = simplifyMesh.simplify(targetTriangleCount,7,false);
        selectedMesh=simlifiedMesh;
        rootNode.detachAllChildren();

        renderScene();

    }


    public Mesh getOriginalMesh() {
        return originalMesh;
    }

    public void setOriginalMesh(Mesh originalMesh) {
        this.originalMesh = originalMesh;
    }


    public SimplifyMesh getSimplifyMesh() {
        return simplifyMesh;
    }

    public void setSimplifyMesh(SimplifyMesh simplifyMesh) {
        this.simplifyMesh = simplifyMesh;
    }


}



















