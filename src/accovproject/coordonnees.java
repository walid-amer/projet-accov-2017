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
class coordonnees {
    
    private int x;
    private int y;
    private int altitude;

    public coordonnees(int x, int y, int altitude) {
        this.x = x;
        this.y = y;
        this.altitude = altitude;
    }
    
        
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
    
    
    
}
    
