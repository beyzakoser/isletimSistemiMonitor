/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheSleepingBarberProblem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beyza
 */
public class mainClass {
        public static void main(String[] args) throws InterruptedException {
        Monitor m = new Monitor();
        Thread barberThread = new Thread(() -> {
            try {
                m.seekCustomer();
            } catch (InterruptedException ex) {
                Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Thread customerThread = new Thread(() -> {
            try {
                m.getHaircut();
            } catch (InterruptedException ex) {
                Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        barberThread.start();
        customerThread.start();

        barberThread.join();
        customerThread.join();

    }
}
