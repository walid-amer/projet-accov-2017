/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accovproject;

import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;
import java.util.concurrent.TimeUnit;
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




public class avion_ {
  
double x;
double y ;
double altitude;
double cap;
double vitesse;  
double VITMAX =1000;
double VITMIN=200;
double ALTMAX =1000;
int numero_vol =0;
int Pause = 2;
  
int ouvrircommunication()
{
    // fonction à implémenter qui permet d'entrer en communication via TCP
  // avec le gestionnaire de vols

return 1;
}
  
void fermercommunication()

{
    // fonction à implémenter qui permet de fermer la communication
  // avec le gestionnaire de vols

}

void envoyer_caracteristiques()
{
  // fonction à implémenter qui envoie l'ensemble des caractéristiques
  // courantes de l'avion au gestionnaire de vols
}
void initialiser_avion()
{	
  // initialisation aléatoire du compteur aléatoire
  //int seed;
  //time (&seed);
  //srandom(seed);

  // intialisation des paramètres de l'avion
  x = 1000 + random() %1000;
  y = 1000 + random() % 1000;
  altitude = 900 + random() % 100;
  
   cap = random() % 360;
  vitesse = 600 + random() % 200;
  
  // initialisation du numero de l'avion : chaine de 5 caractères 
  // formée de 2 lettres puis 3 chiffres
  //numero_vol[0] = (random() % 26) + 'A';
  //numero_vol[1] = (random() % 26) + 'A';
  //sprintf (&numero_vol[2], "%03d", (random() % 999) + 1);
  //numero_vol[5] = 0;
}

// modifie la valeur de l'avion avec la valeur passée en paramètre
void changer_vitesse(int vitess)
{
  if (vitess < 0)
    vitesse = 0;
  else if (vitess > VITMAX) 
    vitesse = VITMAX;
  else vitesse = vitess;
}
// modifie le cap de l'avion avec la valeur passée en paramètre
void changer_cap(int cap1)
{
  if ((cap1 >= 0) && (cap1 < 360))
    cap = cap1;
}


// modifie l'altitude de l'avion avec la valeur passée en paramètre
void changer_altitude(int alt)
{
  if (alt < 0) 
  altitude = 0;
  else if (alt > ALTMAX) 
    altitude = ALTMAX;
  else altitude = alt;
}

// affiche les caractéristiques courantes de l'avion
void afficher_donnees()
{
  System.out.println("Avion " +numero_vol +" -> localisation :" + x + "->" + y +"-> altitude" + altitude +"-> vitesse" + vitesse +"-> cap :" + cap);

}


// recalcule la localisation de l'avion en fonction de sa vitesse et de son cap
void calcul_deplacement()
{
  float cosinus, sinus;
  float dep_x, dep_y;
  int nb;
  
  if (vitesse < VITMIN)
    {
      System.out.println("Vitesse trop faible : crash de l'avion\n");
      fermercommunication();
      System.exit(2);
    }
  if (altitude == 0)
    {
      System.out.println("L'avion s'est ecrase au sol\n");
      fermercommunication();
     System.exit(3);
    }
     double M_PI = 0;
  //M_pi??
  cosinus = (float) cos(cap * 2 * M_PI / 360);
  sinus = (float) sin(cap * 2 * M_PI / 360);
  
  dep_x = (float) (cos(cap * 2 * M_PI / 360) * vitesse * 10 / VITMIN);
  dep_y = (float) (sin(cap * 2 * M_PI / 360) * vitesse * 10 / VITMIN);

  // on se déplace d'au moins une case quels que soient le cap et la vitesse
  // sauf si cap est un des angles droit
  if ((dep_x > 0) && (dep_x < 1)) dep_x = 1;
  if ((dep_x < 0) && (dep_x > -1)) dep_x = -1;
  
  if ((dep_y > 0) && (dep_y < 1)) dep_y = 1;
  if ((dep_y < 0) && (dep_y > -1)) dep_y = -1;
  
  //printf(" x : %f y : %f\n", dep_x, dep_y);
  
  x = x + (int)dep_x;
  y = y + (int)dep_y;
  
  afficher_donnees();
}
// fonction principale : gère l'exécution de l'avion au fil du temps
void se_deplacer()
{
  while(true)
    {
      try {
          TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException ex) {
          Logger.getLogger(avion_.class.getName()).log(Level.SEVERE, null, ex);
      }
      calcul_deplacement();
      envoyer_caracteristiques();
    }
}

}

