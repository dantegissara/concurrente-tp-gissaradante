package test;

import java.util.Random;

import model.OrdenamientoBitonicoConcurrente;
import model.OrdenamientoBitonicoSecuencial;

public class Test {
    public static void main(String[] args) throws Exception {
        int tamaño = 5; // Puedes cambiar el tamaño del arreglo aquí

        int[] arreglo1 = generarArregloAleatorio(tamaño);
        int[] arreglo2 = arreglo1.clone();

        OrdenamientoBitonicoSecuencial ordenamientoSecuencial = new OrdenamientoBitonicoSecuencial();
        OrdenamientoBitonicoConcurrente ordenamientoConcurrente = new OrdenamientoBitonicoConcurrente();

        // Medir tiempo de ordenamiento secuencial
        long tiempoInicio = System.currentTimeMillis();
        ordenamientoSecuencial.ordenarBitonico(arreglo1, 0, arreglo1.length, true);
        long tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo de ordenamiento secuencial: " + (tiempoFin - tiempoInicio) + " ms");

        // Medir tiempo de ordenamiento concurrente
        tiempoInicio = System.currentTimeMillis();
        ordenamientoConcurrente.ordenarBitonico(arreglo2, 0, arreglo2.length, true);
        tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo de ordenamiento concurrente: " + (tiempoFin - tiempoInicio) + " ms");
    }

    private static int[] generarArregloAleatorio(int tamaño) {
        Random random = new Random();
        int[] arreglo = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = random.nextInt(101); // Números entre 0 y 100
        }
        return arreglo;
    }
}
