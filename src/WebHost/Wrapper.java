package WebHost;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajay
 */
public class Wrapper {

    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new Serverside());
            Thread t2 = new Thread(new ClientSide());
            t1.start();
            t2.start();
        } catch (Exception ex) {
            Logger.getLogger(Wrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
