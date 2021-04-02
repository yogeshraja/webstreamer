/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebHost;

import java.io.Serializable;

/**
 *
 * @author rajay
 */
public class UserData implements Serializable {

    public String fname, lname, age, date, mail, number, gender, occupation;
    public String address, health, dynamicfield, pass;

    public UserData(Object values[]) {
        fname = (String) values[0];
        lname = (String) values[1];
        age = (String) values[2];
        date = (String) values[3];
        mail = (String) values[4];
        number = (String) values[5];
        gender = (String) values[6];
        occupation = (String) values[7];
        address = (String) values[8];
        health = (String) values[9];
        dynamicfield = (String) values[10];
        pass = (String) values[11];
    }

}
