package WebHost;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClientSide extends Thread {

    JFrame frame = new JFrame("Client Side");
    JLabel label = new JLabel();

    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println("client waiting for ack from server");
                this.wait(3000);
                System.out.println("Resumed");
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Socket client = new Socket(InetAddress.getLocalHost(), 7000);
                InputStream is = client.getInputStream();

                while (true) {
                    byte[] size = new byte[10000];
                    is.read(size);
                    int arSize = ByteBuffer.wrap(size).asIntBuffer().get();

                    byte[] image = new byte[arSize];
                    is.read(image);
                    BufferedImage bimage = ImageIO.read(new ByteArrayInputStream(image));
                    ImageIcon ico = new ImageIcon(bimage);
                    label.setIcon(ico);

                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ClientSide() throws Exception {
        System.out.println("Creating Client and waiting");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setResizable(true);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
