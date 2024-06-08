package model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OrdenamientoBitonicoConcurrente {
	
	//Umbral para determinar cuando se debe dividir el trabajo en subprocesos
	private static final int UMBRAL = 1_000;

	public void ordenarBitonico(int[] arreglo, int inicio, int cantidad, boolean direccion) throws Exception {
		//si la cantidad de elementos es mayor que 1, se necesita ordenar
		if (cantidad > 1) {
			int k = cantidad / 2;

			// Simulación de carga de trabajo
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Ordenar en orden ascendente
			if (cantidad > UMBRAL) { //Si la cantidad supera el umbral, se divide el trabajo en subprocesos
				ExecutorService executor = Executors.newFixedThreadPool(2); //Se crea un grupo de hilos con 2 subprocesos
				
                // Se envían los trabajos de ordenamiento como tareas futuras a los subprocesos
				Future<?> f1 = executor.submit(() -> {
					try {
						ordenarBitonico(arreglo, inicio, k, true);// Se ordena la primera mitad
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				Future<?> f2 = executor.submit(() -> {
					try {
						ordenarBitonico(arreglo, inicio + k, k, false);// Se ordena la segunda mitad
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				f1.get();// Se espera a que la primera tarea futura haya terminado
				f2.get();// Se espera a que la segunda tarea futura haya terminado
				executor.shutdown();
			} else {
                // Si la cantidad no supera el umbral, se ordena secuencialmente
				ordenarBitonico(arreglo, inicio, k, true);
				ordenarBitonico(arreglo, inicio + k, k, false);
			}

			// Combinar todo el arreglo en orden ascendente o descendente
			combinarBitonico(arreglo, inicio, cantidad, direccion);
		}
	}

    // Método para combinar los sub-arreglos ordenados en un solo arreglo
	private void combinarBitonico(int[] arreglo, int inicio, int cantidad, boolean direccion) throws Exception {
		if (cantidad > 1) {
			int k = cantidad / 2;
			for (int i = inicio; i < inicio + k; i++) {
				if (direccion == (arreglo[i] > arreglo[i + k])) {
					// Intercambiar elementos
					int temp = arreglo[i];
					arreglo[i] = arreglo[i + k];
					arreglo[i + k] = temp;
				}
			}
            // Llamadas recursivas para combinar las mitades restantes

			combinarBitonico(arreglo, inicio, k, direccion);
			combinarBitonico(arreglo, inicio + k, k, direccion);
		}
	}
}
