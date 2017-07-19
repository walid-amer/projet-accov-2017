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
import java.util.logging.Level;
import java.util.logging.Logger;
import static accovproject.avion.socket;
/**
 *
 * @author walid
 */
public class connecter_avion extends Thread {

   // String msg="";
    ServerSocket SacaSocket;
    Socket SocketRadar;
    ArrayList<String> list;
    Socket socket;
    

    public connecter_avion(ServerSocket SacaSocket, Socket SocketRadar, ArrayList<String> list) {
        this.SacaSocket = SacaSocket;
        this.SocketRadar = SocketRadar;
        this.list = list;
    }
    
    

    @Override
    public void run() {
        int nbrAvion = 1;
        while (true) {

            
            try {
                
                socket = SacaSocket.accept();
                Thread t = new Thread(new Accepter_Avion(socket, SocketRadar,list));
                t.start();
                AfficherMessage("L'avion numero " + nbrAvion + "a demarré");
                nbrAvion++;
                //list.add("Avion"+nbrAvion);
                
            } catch (IOException ex) {                
                try {
                socket.close();
                System.out.println("Fin");
            } catch (IOException ex2) {
                Logger.getLogger(Accepter_Avion.class.getName()).log(Level.SEVERE, null, ex2);
            }               
                Logger.getLogger(connecter_avion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void AfficherMessage(String msg) {
        System.out.println(msg);        
    }

}
