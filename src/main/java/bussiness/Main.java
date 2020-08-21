package bussiness;

import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;
import config.ConfigProperties;

public class Main {


    public static void main(String[] args){
        FrameContainer app  = new FrameContainer();

        app.setShowSettings(false);

        AppSettings settings = new AppSettings(true);

        settings.put("Width", 1280);

        settings.put("Height", 720);

        settings.put("Title", ConfigProperties.getConfig("title"));

        //settings.put("VSync", true);

        //settings.put("Samples", 10);

        app.setSettings(settings);

        app.start();
    }


}
