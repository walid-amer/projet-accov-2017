/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accovproject;

/**
 *
 * @author walid
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aless
 */
public class Radar {

    public static Socket socket = null;
    public static String message = "";
    public static Thread t1;

    public static void main(String[] args) {

        try {
            System.out.println("Demande de connexion du radar");
            socket = new Socket("127.0.0.1", 2010);
            BufferedReader inFromSaca = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {

                message = inFromSaca.readLine();
                System.out.println(message);

            }

        } catch (IOException ex) {
            Logger.getLogger(Radar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
