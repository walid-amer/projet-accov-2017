/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accovproject;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author walid
 */
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author walid
 */




public class avion {
int altitude;
int altmin=0;
int altmax=1000;
int cap;
int vitesse;  
int vitmax =1000;
int vitmin=200;
int numero_vol =0;
int Pause = 2000;

char[] numvol;
 String numeroavion;
  static Socket socket;
  static final String Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  static final String Numbers = "0123456789";
  static SecureRandom rnd = new SecureRandom();
private coordonnees coord;
private deplacement dep;

public avion(){	
  int x = (int) (1000 + random() % 1000);
        int y = (int) (1000 + random() % 1000);
        int z = (int) (900 + random() % 100);

        int cap = (int) (random() % 360);
        int vitesse = (int) (600 + random() % 200);

          

        numeroavion = randomString();   
        
        coord = new coordonnees(x, y, z);
        dep = new deplacement(cap, vitesse);
}
    
public coordonnees getCoord() {
        return coord;
                    }

    public deplacement getDep() {
        return dep;
    }

    public void setCoord(coordonnees coord) {
        this.coord = coord;
    }

    public void setDep(deplacement dep) {
        this.dep = dep;
    }
    


    
    String randomString( ){
        StringBuilder sb = new StringBuilder(5);
        for( int i = 0; i < 2; i++ ) 
            sb.append( Letters.charAt( rnd.nextInt(Letters.length()) ) );
         for( int i = 0; i < 3; i++ ) 
            sb.append( Numbers.charAt( rnd.nextInt(Numbers.length()) ) );
        
        return sb.toString();
    }
    
Boolean ouvrir_communication() {

        try {
            socket = new Socket("localhost", 2009);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(avion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    void fermer_communication() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(avion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String envoyer_caracteristiques() {
        // fonction à implémenter qui envoie l'ensemble des caractéristiques
        // courantes de l'avion au gestionnaire de vols

        return "Avion:" + numeroavion + "   Localisation:" + coord.getX() + "," + coord.getY() + "," + coord.getAltitude() + "   Vitesse:" + dep.getVitesse() + "   Cap:" + dep.getCap();

    /**
     *
     */
    }

// modifie la valeur de l'avion avec la valeur passée en paramètre

void changer_vitesse (int vitesse)
{
        if (this.vitesse < 0) {
            dep.setVitesse(0);
        } else if (this.vitesse > vitmax) {
            dep.setVitesse(vitmax);
        } else {
            dep.setVitesse(vitesse);
        }
    }



// modifie l'altitude de l'avion avec la valeur passée en paramètre
void changer_cap(int cap) {
        if ((cap >= 0) && (cap < 360)) {
            dep.setCap(cap);
        }
    }

    // modifie l'altitude de l'avion avec la valeur passée en paramètre
    void changer_altitude(int alt) {
        if (alt < 0) {
            coord.setAltitude(0);
        } else if (alt > altmax)
            coord.setAltitude(altmax);
         else {
            coord.setAltitude(alt);
        }
    }

// affiche les caractéristiques courantes de l'avion
void afficher_donnees() {

        // to be changed to display data from server side
        System.out.println("Avion:" + numeroavion + "   Localisation:" + coord.getX() + "," + coord.getY() + "," + coord.getAltitude() + "   Vitesse:" + dep.getVitesse() + "   Cap:" + dep.getCap());

    }


// recalcule la localisation de l'avion en fonction de sa vitesse et de son cap
void calcul_deplacement() {
        float cosinus, sinus;
        float dep_x, dep_y;
        int nb;

        if (dep.getVitesse() < vitmin) {
            System.out.println("Vitesse trop faible : crash de l'avion\n");
            fermer_communication();
            exit(2);
        }
        if (coord.getAltitude() == 0) {
            System.out.println("L'avion s'est ecrase au sol\n");
            fermer_communication();
            exit(3);
        }
cosinus = (float) cos(dep.getCap() * 2 * Math.PI / 360);
        sinus = (float) sin(dep.getCap() * 2 * Math.PI / 360);

        //newPOS = oldPOS + Vt
        dep_x = cosinus * dep.getVitesse() * 10 / vitmin;
        dep_y = sinus * dep.getVitesse() * 10 / vitmax;

        // on se d�place d'au moins une case quels que soient le cap et la vitesse
        // sauf si cap est un des angles droit
        if ((dep_x > 0) && (dep_x < 1)) {
            dep_x = 1;
        }
        if ((dep_x < 0) && (dep_x > -1)) {
            dep_x = -1;
        }

        if ((dep_y > 0) && (dep_y < 1)) {
            dep_y = 1;
        }
        if ((dep_y < 0) && (dep_y > -1)) {
            dep_y = -1;
        }

        //printf(" x : %f y : %f\n", dep_x, dep_y);
        coord.setX(coord.getX() + (int) dep_x);
        coord.setY(coord.getY() + (int) dep_y);

        //afficher_donnees();
    }

// fonction principale : gère l'exécution de l'avion au fil du temps
void se_deplacer() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            //while (true) {
            sleep(Pause);
            calcul_deplacement();
            //envoyer_caracteristiques();
        }
    }
public static void main(String[] args) throws InterruptedException {

        PrintWriter out;
        
        try {

            avion avion = new avion();
            if (avion.ouvrir_communication() == true) {
                
                String caracteristiques = avion.envoyer_caracteristiques();
                
                out = new PrintWriter(avion.socket.getOutputStream());
                out.println(caracteristiques);
                
                
                while (true){
                    sleep(avion.Pause);
                   // out = new PrintWriter(avion.socket.getOutputStream());
                   // out.println(caracteristiques);
                    
                    avion.calcul_deplacement();
                    caracteristiques = avion.envoyer_caracteristiques();
                    out.println(caracteristiques);

                    out.flush();                
                }
                //avion.fermer_communication();
                
            }

        } catch (Exception ex) {
            System.out.println("Fin Avion");
        }

    }

}


