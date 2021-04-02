package TOOLS1;

import static TOOLS1.DbTools.getUserFromUserPool;
import WebHost.UserData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajay
 */
public class RegisterServer {

    static private Connection connection;

    static public Connection getConnect() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/gym31", "yogesh", "java");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return connection;

    }

    public static void ReadFromStream() {
        try {
            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(4444);
            } catch (IOException ex) {
                System.out.println("Can't setup server on this port number. ");
            }

            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;

            try {
                socket = serverSocket.accept();
            } catch (IOException ex) {
                System.out.println("Can't accept client connection. ");
            }

            try {
                in = socket.getInputStream();
            } catch (IOException ex) {
                System.out.println("Can't get socket input stream. ");
            }

            try {
                out = new FileOutputStream("Udata2.blah");
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
            }

            byte[] bytes = new byte[16 * 1024];

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

            out.close();
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(RegisterServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean register_user(Object values[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gym31", "yogesh", "java");
            Object poolValues[] = getUserFromUserPool(values);
            String query = ("insert into usertable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String) poolValues[0]);
            ps.setString(2, (String) values[0]);
            ps.setString(3, (String) values[1]);
            ps.setInt(4, Integer.parseInt((String) values[2]));
            ps.setString(5, (String) values[3]);
            ps.setString(6, (String) values[4]);

            ps.setString(7, (String) values[5]);

            ps.setString(8, (String) values[6]);

            ps.setString(9, (String) values[7]);

            ps.setString(10, (String) values[8]);
            ps.setString(11, (String) values[9]);
            ps.setString(12, (String) values[10]);
            ps.setString(13, CryptUtility.encryptString((String) values[11]));
            ps.setString(14, (String) poolValues[1]);
            ps.setInt(15, (Integer) poolValues[2]);
            ps.executeUpdate();
            Statement st = connection.createStatement();
            st.execute("Delete from userpool where id='" + poolValues[0] + "'");
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    static Object[] getValues(UserData object) {
        Object values[] = new Object[12];
        values[0] = object.fname;
        values[1] = object.lname;
        values[2] = object.age;
        values[3] = object.date;
        values[4] = object.mail;
        values[5] = object.number;
        values[6] = object.gender;
        values[7] = object.occupation;
        values[8] = object.address;
        values[9] = object.health;
        values[10] = object.dynamicfield;
        values[11] = object.pass;
        return values;
    }

    public static void main(String[] args) {
        ReadFromStream();
        UserData object = null;
        try {

            // Reading the object from a file
            FileInputStream file = new FileInputStream("Udata2.blah");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object = (UserData) in.readObject();

            in.close();
            file.close();
            System.out.println("Object has been deserialized\n"
                    + "Data after Deserialization.");

        } catch (Exception ex) {
            System.out.println("IOException is caught");
        }
        Object values[] = getValues(object);
        register_user(values);
    }
}
