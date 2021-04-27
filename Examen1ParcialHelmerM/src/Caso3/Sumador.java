package Caso3;

import java.util.concurrent.RecursiveTask;

public class Sumador extends RecursiveTask<Long>{
	int[] numeros;
    int pos1, pos2;
    final int NUM_MAX_ELEMENTOS=2;
    private int longitud;
    private int posMitad;
    private int limiteIzquierdo;
    private int limiteDerecho;
    /*Por simplificar solo aceptamos vectores
    con un número de elementos que sea una
    potencia de 2*/
    public Sumador(int[] numeros, int pos1, int pos2)  {
        this.numeros    = numeros;
        this.pos1       = pos1;
        this.pos2       = pos2;
        longitud        = 1 + (pos2- pos1);
        posMitad        = longitud/2;
        limiteIzquierdo = pos1+(posMitad-1);
        limiteDerecho   = limiteIzquierdo+1;
    }

    @Override
    protected Long compute() {
        /* Si el vector tiene estos elementos,
        simplemente sumamos*/
        if (longitud==NUM_MAX_ELEMENTOS){
            long suma=0;
            for (int i=pos1; i<=pos2; i++){
                suma=suma + numeros[i];
            }
            return suma;
        }

        /*Pero si tiene más elementos,
        dividimos el vector en dos y sumamos
        las dos mitades de forma independiente
        y paralela*/
        Sumador sumadorIzq=
                new Sumador(numeros, pos1, limiteIzquierdo);
        Sumador sumadorDer=
                new Sumador(numeros, limiteDerecho, pos2);

        /*Lanzamos los sumadores...*/
        sumadorIzq.fork();
        sumadorDer.fork();

        /* Y recogemos sus resultados*/
        long sumaIzq=sumadorIzq.join();
        long sumaDer=sumadorDer.join();

        return sumaIzq + sumaDer;
    } /*Fin del compute*/

}
