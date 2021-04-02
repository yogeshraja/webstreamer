package WebHost;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Serverside extends Thread {

    OutputStream os = null;
    ServerSocket server = null;

    public Serverside() throws Exception {
        System.out.println("Creating server and waiting");
    }

    public void startCam() throws IOException {
        synchronized (this) {
            this.server = new ServerSocket(7000);
            System.out.println("Created and Notifying client");
            this.notifyAll();
            System.out.println("Notified waiting for client");
            Socket client = server.accept();
            System.out.println("Connected....");
            os = client.getOutputStream();
            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            webcam.open();
            while (true) {

                BufferedImage bImg = webcam.getImage();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bImg, "jpg", bos);
                byte[] imageSize = ByteBuffer.allocate(10000).putInt(bos.size()).array();
                this.os.write(imageSize);
                this.os.write(bos.toByteArray());
                this.os.flush();
            }
        }
    }

    @Override
    public void run() {
        try {
            this.startCam();
        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
