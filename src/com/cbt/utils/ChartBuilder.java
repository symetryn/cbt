package com.cbt.utils;

import com.cbt.model.ChartItem;
import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ChartBuilder {

    Gson g;

    private final String URL = "https://quickchart.io/chart";

    /**
     * Initializing the Gson object in constructor
     */
    public ChartBuilder() {
        g = new Gson();
    }

    /**
     * builds chart according to the parameters passed
     * @param type type of diagram 
     * @param label string array of labels
     * @param data list of chart data
     * @param width preferred width
     * @param height preferred height
     * @return gives back image object
     * @throws MalformedURLException
     */
    public Image build(String type, String[] label, ArrayList<ChartItem> data, int width, int height) throws MalformedURLException {
        URL imageUrl;

        switch (type) {
            case "radialGauge":
                imageUrl = new URL(URL + "?w=" + width + "&h=" + height + "&c={type:'radialGauge',data:{datasets:" + g.toJson(data) + "}}");
                return getImageFromByteStream(imageUrl);

            case "line":
                System.out.println("here");
                System.out.println(g.toJson(data));
                imageUrl = new URL(URL + "?w=" + width + "&h=" + height + "&c={type:'line',"
                        + "data:{"
                        + "labels:"
                        + g.toJson(label).replaceAll("\\s+","%20")
                        + ","
                        + "datasets:"
                        + g.toJson(data).replaceAll("\\s+","%20")
                        + "}}");

                return getImageFromByteStream(imageUrl);
            case "pie":
                System.out.println("here");
                System.out.println(g.toJson(data));
                imageUrl = new URL(URL + "?w=" + width + "&h=" + height + "&c={type:'pie',"
                        + "data:{"
                        + "labels:"
                        + g.toJson(label)
                        + ","
                        + "datasets:"
                        + g.toJson(data)
                        + "}}");
                
                return getImageFromByteStream(imageUrl);
            case "bar":
                System.out.println("here");
                System.out.println(g.toJson(data));
                imageUrl = new URL(URL + "?w=" + width + "&h=" + height + "&c={type:'bar',"
                        + "data:{"
                        + "labels:"
                        + g.toJson(label).replaceAll("\\s+","%20")
                        + ","
                        + "datasets:"
                        + g.toJson(data).replaceAll("\\s+","%20")
                        + "}}");
                System.out.println(imageUrl);
              
                
                return getImageFromByteStream(imageUrl);
            default:
                return null;
        }

    }
/*
    Fetches image from Image kit as a byte array stream and returns as image object
*/
    private Image getImageFromByteStream(URL imageUrl) {
        try {
            URLConnection ucon = imageUrl.openConnection();
            ucon.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            InputStream is = ucon.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
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
