/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheSleepingBarberProblem;

import java.time.Clock;

/**
 *
 * @author beyza
 */
public class Monitor {

    boolean barber = true; //berberin musaitlik durumu
    int chairs = 0; //dolu sandalye sayisi
    int max = 3; //bekleme koltugu max sayisi

    public synchronized void seekCustomer() throws InterruptedException {
        while (true) {
            System.out.println("seekCustomer() calisti");
            while (chairs == 0) { //kimse yoksa
                barber = false; //uyuduğu için müsait değil
                System.out.println("berber uyudu");
                wait(); //berber uyur //seekCustomer() metodu bekleyecek
                System.out.println("berber uyandı");
                barber = true; //artık müsait
            }
            while (chairs > 0 && chairs <= 3) {
                if (barber == true) { //müşteri varsa ve barber müsaitse
                    barber = false;//saç kesiyor, müsait değil
                    chairs -= 1; //sandalye boşaldı
                    notifyAll();//saç kesildi //metod serbest kalacak
                    barber = true; //artık müsait
                    System.out.println("sac kesildi, " + chairs + " dolu sandalye var.");
                    wait(); //seekCustomer() metodu bekleyecek

                }
            }

        }

    }

    public synchronized void getHaircut() throws InterruptedException {
        while (true) {

            System.out.println("getHaircut() calisti");
            if (chairs < max) { //boş yer varsa
                chairs += 1; //müşteri geldi, chairs doldu 
                System.out.println("musteri geldi,  " + chairs + " dolu sandalye var");
                if (barber == false) { //berber uyuyorsa
                    notifyAll(); //berberi uyandırdı. //metod serbest kalacak
                    wait(); // getHaircut() metodu bekleyecek
                }
                notifyAll();

            }
            if (chairs == max) {//boş yer yoksa bekleyecek
                System.out.println("Bos yer olmadigindan musteri alınamadı");
                wait(); //getHairCut() bekletilecek
                notifyAll(); //method serbest

            }
        }

    }
}
