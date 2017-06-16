/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.application;

import java.util.ResourceBundle;



/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class States {
    
    private static final States instance = new States();
    private ResourceBundle bundle;
    private Difficulty diff;
    
    public static States getInstance() {
        return instance;
    }
    public Difficulty getDifficulty() {
        return diff;
    }
    public void setDifficulty(final Difficulty diff) {
        this.diff=diff;
    }
    
    public ResourceBundle getBundle() {
        return bundle;
    }
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    
}
