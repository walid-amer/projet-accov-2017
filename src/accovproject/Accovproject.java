/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accovproject;
/**
 *
 * @author walid
 * 
 */
public class Accovproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // on initialise l'avion
        avion_ lebanon1 =new avion_();
        avion_ lebanon2 = new avion_();
        avion_ lebanon3 = new avion_();
  lebanon1.initialiser_avion(); 
  lebanon2.initialiser_avion(); 
  lebanon3.initialiser_avion(); 
  lebanon1.afficher_donnees();
  lebanon2.afficher_donnees();
  lebanon3.afficher_donnees();
  
  // on quitte si on arrive à pas contacter le gestionnaire de vols
  
  
  /*
  if (!ouvrir_communication())
    {
      System.out.println("Impossible de contacter le gestionnaire de vols\n");
      System.exit(1);
    }
*/
  // on se déplace une fois toutes les initialisations faites
  
  
  /*se_deplacer();*/   
}

        
    }
    
/*}*/


