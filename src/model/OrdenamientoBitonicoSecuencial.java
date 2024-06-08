package model;

public class OrdenamientoBitonicoSecuencial {
	public void ordenarBitonico(int[] arreglo, int inicio, int cantidad, boolean direccion) {
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
			ordenarBitonico(arreglo, inicio, k, true);

			// Ordenar en orden descendente
			ordenarBitonico(arreglo, inicio + k, k, false);

			// Combinar todo el arreglo en orden ascendente o descendente
			combinarBitonico(arreglo, inicio, cantidad, direccion);
		}
	}

	private void combinarBitonico(int[] arreglo, int inicio, int cantidad, boolean direccion) {
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
