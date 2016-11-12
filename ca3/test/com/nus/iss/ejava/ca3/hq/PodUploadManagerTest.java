/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.hq;

import com.nus.iss.ejava.ca3.entity.Delivery;
import com.nus.iss.ejava.ca3.entity.Pod;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mugunthan
 */
public class PodUploadManagerTest {

    public PodUploadManagerTest() {
    }

    @Test
    public void testPodUploadManager() throws IOException {
        Pod pod = new Pod();
        pod.setPodId(1);
        pod.setDelivery(new Delivery("delivery1", "add", "123456"));
        BufferedImage image = ImageIO.read(new URL("http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));
        ByteArrayOutputStream b = new ByteArrayOutputStream();

        // do whatever with the array...
        byte[] jpgByteArray = b.toByteArray();

        pod.setImage(jpgByteArray);
        pod.setNote("Some info");
        PodUploadManager manager = new PodUploadManager();
        manager.podUpload(pod);
    }
}
