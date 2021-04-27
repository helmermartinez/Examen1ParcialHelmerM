package Caso3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class SumadorParalelo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

        /* Construimos un vector de ejemplo...*/
        final int MAX_NUMEROS=1024;
        int[] numeros=new int[MAX_NUMEROS];

        /*Y lo rellenamos con n�meros*/
        for (int i=0; i<MAX_NUMEROS; i++){
            numeros[i]=i;
        }

        /* Esta clase gestionar� el paralelismo de las tareas*/
        ForkJoinPool pool=new ForkJoinPool();

        /* Fabricamos un sumador inicial que intente sumar todo*/
        Sumador sumador=new Sumador(numeros, 0, MAX_NUMEROS-1);

        /*Y la clase ForkJoinPool invocar� a nuestro sumador lanzando
        los hilos, recogiendo los resultados y haciendo todo lo necesario
        para que al final solo tengamos que recoger el resultado*/
        pool.invoke(sumador);

        /* Resultado que podemos ver aqu�*/
        Long resultado = sumador.get();
        System.out.println("La suma es:"+resultado);
    } /*Fin del main*/
}
