/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accovproject;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/**
 *
 * @author walid
 */
public class Scada {
    
    public static ArrayList<String> list;

    public static void main(String[] zero) {

        
        ServerSocket serversocketAvion;
        ServerSocket serverSocketController;
        ServerSocket serversocketRadar;

        //int nbrAvion = 1;
        try {

            list = new ArrayList<String>();

            serversocketAvion = new ServerSocket(2009);
            serversocketRadar = new ServerSocket(2010);
            serverSocketController = new ServerSocket(2011);
            
            
            Socket socketRadar = serversocketRadar.accept();

            System.out.println("SACA est Pret!");

                //while (true){
            connecter_avion cA = new connecter_avion(serversocketAvion, socketRadar,list);
            cA.start();
            
            Connecter_Controller cC = new Connecter_Controller(serverSocketController,list);
            cC.start();

                //}
        } catch (IOException e) {
            System.out.println("Fin SACA");
            e.printStackTrace();
        }
    }
    
}
