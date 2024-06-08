package test;

import java.util.Random;

import model.OrdenamientoBitonicoConcurrente;
import model.OrdenamientoBitonicoSecuencial;

public class Test {
    public static void main(String[] args) throws Exception {
        int tamaño = 524288; // Puedes cambiar el tamaño del arreglo aquí, potencias de 2
        // 1024, 8192, 16384, 32768, 65536, 131072 , 262144, 524288, 1048576

        int[] arreglo1 = generarArregloAleatorio(tamaño);
        int[] arreglo2 = arreglo1.clone();

        OrdenamientoBitonicoSecuencial ordenamientoSecuencial = new OrdenamientoBitonicoSecuencial();
        OrdenamientoBitonicoConcurrente ordenamientoConcurrente = new OrdenamientoBitonicoConcurrente();
        
     // Mostrar el arreglo antes del ordenamiento
        System.out.println("Arreglo original:");
        //imprimirArreglo(arreglo1);

        // Medir tiempo de ordenamiento secuencial
        long tiempoInicio = System.currentTimeMillis();
        ordenamientoSecuencial.ordenarBitonico(arreglo1, 0, arreglo1.length, true);
        long tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo de ordenamiento secuencial: " + (tiempoFin - tiempoInicio) + " ms");
        
     // Mostrar el arreglo después del ordenamiento secuencial
        System.out.println("Arreglo después del ordenamiento secuencial:");
        //imprimirArreglo(arreglo1);

        // Medir tiempo de ordenamiento concurrente
        tiempoInicio = System.currentTimeMillis();
        ordenamientoConcurrente.ordenarBitonico(arreglo2, 0, arreglo2.length, true);
        tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo de ordenamiento concurrente: " + (tiempoFin - tiempoInicio) + " ms");
        
     // Mostrar el arreglo después del ordenamiento concurrente
        System.out.println("Arreglo después del ordenamiento concurrente:");
        //imprimirArreglo(arreglo2);
    }

    private static int[] generarArregloAleatorio(int tamaño) {
        Random random = new Random();
        int[] arreglo = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = random.nextInt(101); // Números entre 0 y 100
        }
        return arreglo;
    }
    
    @SuppressWarnings("unused")
	private static void imprimirArreglo(int[] arreglo) {
        for (int i : arreglo) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

