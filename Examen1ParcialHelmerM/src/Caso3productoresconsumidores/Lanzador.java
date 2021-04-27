package Caso3productoresconsumidores;

public class Lanzador {
	 public static void main(String[] args) throws InterruptedException {
         int MAX_PRODUCTORES     = 5;
         int MAX_CONSUMIDORES    = 7;
         int MAX_ELEMENTOS       = 10;

         Thread[] hilosProductor;
         Thread[] hilosConsumidor;

         hilosProductor   = new Thread[MAX_PRODUCTORES];
         hilosConsumidor  = new Thread[MAX_CONSUMIDORES];

         Cola colaCompartida=new Cola(MAX_ELEMENTOS);

         /*Construimos los productores*/
         for (int i=0; i<MAX_PRODUCTORES; i++){
                 Productor productor=new Productor(colaCompartida);
                 hilosProductor[i]=new Thread(productor);
                 hilosProductor[i].start();
         }
         /*Construimos los consumidores*/
         for (int i=0; i<MAX_CONSUMIDORES; i++){
                 Consumidor consumidor=new Consumidor(colaCompartida);
                 hilosConsumidor[i]=new Thread(consumidor);
                 hilosConsumidor[i].start();
         }

         /* Esperamos a que acaben todos los hilos, primero
         productores y luego consumidores
         */
         for (int i=0; i<MAX_PRODUCTORES; i++){
                 hilosProductor[i].join();
         }
         for (int i=0; i<MAX_CONSUMIDORES; i++){
                 hilosConsumidor[i].join();
         }
 }
}
