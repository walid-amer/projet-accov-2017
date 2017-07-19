/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accovproject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author walid
 */

    
    public class Accepter_Avion implements Runnable {

    BufferedReader inFromAvion;
    //private ServerSocket socketserver;
    private Socket socket;
    private Socket socketRadar;
    private int nbrAvion = 1;
    private PrintWriter outToRadar = null;
    ArrayList<String> list;

    public Accepter_Avion(Socket socket, Socket socketRadar, ArrayList<String> list) {
        this.socket = socket;
        this.socketRadar = socketRadar;
        this.list = list;
    }

    @Override
    public void run() {

        try {
            // while (true) {
            // socket = socketserver.accept(); // Un avion se connecte on l'accepte

            while (true) {

                inFromAvion = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message_From_Avion = inFromAvion.readLine();
                String message_distant = "";

                String nom_avion = message_From_Avion.substring(6, 11);

               //String s = list.toString();
                // message_From_Avion.substring(6,10)
                if (!list.contains(nom_avion.trim())) {
                
                    list.add(nom_avion.trim());
                }

                outToRadar = new PrintWriter(socketRadar.getOutputStream());
                outToRadar.println(message_From_Avion); //evoie id/nom/arrivee
                outToRadar.flush();

                //System.out.println(message_distant);
            }

            //nbrAvion++;
            //socket.close();
            //}
        } catch (IOException e) {

            System.out.println("Fin accepter avion");
            e.printStackTrace();
        }
    }
}
    

