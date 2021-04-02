/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebHost;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author rajay
 */
public class module1 extends JFrame implements ActionListener {

    String generateCaptchaString() {
        Random rand = new Random();
        int length = 7;
        //System.out.println(length);

        StringBuffer captcha = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(rand.nextInt(26));
            // System.out.println(baseCharNumber+"************");
            int charNumber = 0;
            charNumber = 65 + baseCharNumber;
            // System.out.println(charNumber);

            captcha.append((char) charNumber);
        }

        return captcha.toString();
    }
    JFrame j = new JFrame();
    JLabel l1 = new JLabel("E-PASS GENERATION");
    JButton b1 = new JButton("LOGIN");
    JButton b2 = new JButton("REGISTER");

    JFrame j1 = new JFrame();
    JButton b3 = new JButton("ADMIN LOGIN");
    JButton b4 = new JButton("USER LOGIN");

    JFrame j2 = new JFrame();
    JLabel l2 = new JLabel("E-MAIL ID");
    JLabel l3 = new JLabel("PASSWORD");
    JLabel l4 = new JLabel("RE-PASSWORD");
    JLabel l5 = new JLabel("ENTER CAPTCHA");
    JLabel l6 = new JLabel("CAPTCHA  ---");
    String str = generateCaptchaString();
    JLabel l7 = new JLabel(str);
    JTextField t1 = new JTextField();
    JPasswordField p1 = new JPasswordField();
    JPasswordField p2 = new JPasswordField();
    JTextField t2 = new JTextField();
    JButton b5 = new JButton("REGISTER");
    JLabel l8 = new JLabel();
    JButton b = new JButton("LOGIN");

    JFrame j3 = new JFrame();
    JLabel l9 = new JLabel("E-MAIL ID");
    JLabel l10 = new JLabel("PASSWORD");
    JPasswordField p3 = new JPasswordField();
    JTextField t3 = new JTextField();
    JButton b6 = new JButton("LOGIN");
    JLabel l11 = new JLabel();
    JButton b7 = new JButton("REGISTER");

    module1() {
        j.setBounds(280, 90, 850, 550);
        l1.setBounds(320, 150, 1000, 100);
        b1.setBounds(200, 250, 100, 20);
        b2.setBounds(450, 250, 100, 20);
        b1.addActionListener(this);
        b2.addActionListener(this);

        j1.setBounds(280, 90, 850, 550);
        b3.setBounds(300, 150, 120, 20);
        b4.setBounds(300, 250, 120, 20);
        b3.addActionListener(this);
        b4.addActionListener(this);

        j2.setBounds(280, 90, 850, 550);
        l2.setBounds(190, 100, 1000, 20);
        l3.setBounds(190, 150, 1000, 20);
        l4.setBounds(190, 200, 1000, 20);
        l5.setBounds(190, 250, 1000, 20);
        t1.setBounds(320, 100, 150, 20);
        p1.setBounds(320, 150, 150, 20);
        p2.setBounds(320, 200, 150, 20);
        t2.setBounds(320, 250, 150, 20);
        l6.setBounds(200, 300, 1000, 20);
        l7.setBounds(300, 300, 1000, 20);
        b5.setBounds(250, 350, 100, 20);
        l8.setBounds(250, 400, 1000, 20);
        b.setBounds(350, 400, 100, 20);

        j3.setBounds(280, 90, 850, 550);
        l9.setBounds(200, 100, 1000, 90);
        l10.setBounds(190, 200, 1000, 20);
        l11.setBounds(190, 400, 1000, 20);
        t3.setBounds(320, 130, 150, 20);
        p3.setBounds(320, 200, 150, 20);
        b6.setBounds(290, 290, 100, 20);
        b7.setBounds(400, 400, 100, 20);

        j.add(l1);
        j.add(b1);
        j.add(b2);
        j.setLayout(null);
        j.setVisible(true);
    }

    public static void main(String[] args) {
        /*try{


            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/epass?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt=con.createStatement();
                String query="create table epasstable(emailid varchar(20),password varchar(20))";
                stmt.executeUpdate(query);
                con.close();
            }
            catch(Exception ae)
            { System.out.println(ae+" ******************" );
            }*/
        new module1();
    }

    public void actionPerformed(ActionEvent e) {
//        int count=1;
        if (e.getSource() == b1) {
            j1.add(b3);
            j1.add(b4);
            j1.setLayout(null);
            j1.setVisible(true);

        }
        if (e.getSource() == b2) {
            j2.add(l2);
            j2.add(l3);
            j2.add(l4);
            j2.add(l5);
            j2.add(t1);
            j2.add(t2);
            j2.add(p1);
            j2.add(p2);
            j2.add(l6);
            j2.add(l7);
            j2.add(b5);
            j2.add(l8);
            //j2.add(b);
            j2.setLayout(null);
            j2.setVisible(true);
        }

        if (e.getSource() == b4) {
            j3.add(l9);
            j3.add(l10);
            j3.add(l11);
            j3.add(t3);
            j3.add(p3);
            j3.add(b6);
            j3.add(b7);
            j3.setLayout(null);
            j3.setVisible(true);

        }
        if (e.getSource() == b5) {
            //String s=t1.getText();
            //String s1=p1.getText();
            //String s2=p2.getText();
            //String s3=t2.getText();
            int count = 0;
            try {
                String s = t1.getText();
                String s1 = p1.getText();
                String s2 = p2.getText();
                String s3 = t2.getText();
                //int count=0;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/epass?zeroDateTimeBehavior=convertToNull", "root", "");
                Statement stmt = con.createStatement();
                String query = "select * from epasstable";
                ResultSet rs = stmt.executeQuery(query);
                //con.close();
                while (rs.next()) {
                    if (rs.getString("emailid").equals(s)) {
                        l8.setText("ALREADY HAVE AN ACCOUNT CLICK TO LOGIN");
                        //j2.add(l8);
                        j2.add(b);
                        count++;
                        break;
                    }
                }
                //con.close();
                if (count == 0) {

                    if (s1.equals(s2)) {
                        if (str.equals(s3)) {
                            //Class.forName("com.mysql.jdbc.Driver");
                            //// Statement stmt1=con.createStatement();
                            String query1 = "insert into epasstable(emailid,password) values('" + t1.getText() + "','" + p1.getText() + "')";
                            stmt.executeUpdate(query1);
                            con.close();
                            j3.setLayout(null);
                            j3.setVisible(true);
                        } else {
                            l8.setText("RE-ENTER CAPTCHA");
                        }
                    } else {
                        l8.setText("PASSWORD DOES NOT MATCH");
                    }
                }

            } catch (Exception ae) {
                System.out.println(ae);
            }
        }
    }

}
