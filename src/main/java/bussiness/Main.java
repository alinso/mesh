package bussiness;

import com.jme3.scene.Mesh;
import com.jme3.system.AppSettings;
import config.ConfigProperties;
import lib.SimplifyMesh;
import data.MeshFactory;

public class Main {


    public static void main(String[] args) {

        Mesh mesh = MeshFactory.getMesh();
        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1380);
        settings.put("Height", 800);
        settings.put("Title", ConfigProperties.getInstance().getConfig("title"));

        Visualize3DApp app = new Visualize3DApp();
        app.setShowSettings(false);
        app.setDisplayFps(false);
        app.setDisplayStatView(false);
        app.setSettings(settings);
        app.setOriginalMesh(mesh);
        app.setSimplifyMesh(new SimplifyMesh(mesh));
        app.start();
    }


}
