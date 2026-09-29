package co.edu.univalle.poe.mvc.model;

import java.util.Random;
/*
Clase que representa un dado de 6 caras y genera un
número aleatorio entre 1 y 6 cada vez que se requiere un
lanzamiento de los dados
 */
public class Dado {
    private final int NUMERO_CARAS = 6;
    private Random random;

    public Dado(){
        random = new Random();
    }

    public int lanzar(){
       return  random.nextInt(NUMERO_CARAS)+1;
    }
}
