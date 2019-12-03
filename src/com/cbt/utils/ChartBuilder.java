package com.cbt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Symetryn
 */
public class ChartBuilder {

    private final String URL = "https://quickchart.io/chart";

    public Image build(String type, String data, String color, int width, int height) throws MalformedURLException {
        switch (type) {
            case "radialGauge":
                URL imageUrl = new URL(URL + "?w=" + width + "&h=" + height + "&c={type:'radialGauge',data:{datasets:[{data:[" + data + "],backgroundColor:'" + color + "'}]}}");
                return getImageFromByteStream(imageUrl);
            default:
                return null;
        }

    }

    private Image getImageFromByteStream(URL imageUrl) {
        try {
            URLConnection ucon = imageUrl.openConnection();
            ucon.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            InputStream is = ucon.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();

            Image img = new Image(new ByteArrayInputStream(baos.toByteArray()));
            return img;
        } catch (IOException ex) {
            Logger.getLogger(ChartBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}