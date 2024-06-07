package model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OrdenamientoBitonicoConcurrente {
	private static final int UMBRAL = 1_000;

	public void ordenarBitonico(int[] arreglo, int inicio, int cantidad, boolean direccion) throws Exception {
		if (cantidad > 1) {
			int k = cantidad / 2;

			// Simulación de carga de trabajo
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Ordenar en orden ascendente
			if (cantidad > UMBRAL) {
				ExecutorService executor = Executors.newFixedThreadPool(2);
				Future<?> f1 = executor.submit(() -> {
					try {
						ordenarBitonico(arreglo, inicio, k, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				Future<?> f2 = executor.submit(() -> {
					try {
						ordenarBitonico(arreglo, inicio + k, k, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				f1.get();
				f2.get();
				executor.shutdown();
			} else {
				ordenarBitonico(arreglo, inicio, k, true);
				ordenarBitonico(arreglo, inicio + k, k, false);
			}

			// Combinar todo el arreglo en orden ascendente o descendente
			combinarBitonico(arreglo, inicio, cantidad, direccion);
		}
	}

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
			combinarBitonico(arreglo, inicio, k, direccion);
			combinarBitonico(arreglo, inicio + k, k, direccion);
		}
	}
}
