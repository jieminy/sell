package com.imooc.common.audio;


import javax.swing.*;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 10:40 2018/5/19
 */
public class Music {
    public static AudioClip loadSound(String fileName) {
        URL url = null;
        try {
            url = new URL("http", "localhost", 8082, "/sell/music/" + fileName);
//            url = new URL("/sell/music/"+fileName);
        } catch (MalformedURLException e) {

        }
        return JApplet.newAudioClip(url);
    }

    public static void play() {
        AudioClip audioClip = loadSound("remind.mp3");
        audioClip.play();
    }
}
