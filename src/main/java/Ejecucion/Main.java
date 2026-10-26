package Ejecucion;

import javax.swing.SwingUtilities;

import controlador.ControladorApp;
import vista.VistaApp;

/**
 * Clase de arranque de la aplicación.
 * <p>
 * Contiene el método {@code main}, que sirve como banco de pruebas para
 * comprobar el funcionamiento conjunto de los ejércitos de héroes y bestias, la
 * simulación de una guerra entre ambos y el registro/narración de los combates
 * generados.
 */
public class Main {

	/**
	 * Punto de entrada de la aplicación.
	 * <p>
	 * Crea un ejército de héroes y un ejército de bestias, los rellena con
	 * personajes de ejemplo, lanza una simulación de {@link Guerra} entre ambos y
	 * muestra por consola la narración generada a partir del registro de combates.
	 *
	 * @param args argumentos de línea de comandos (no se utilizan actualmente)
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			VistaApp vista = new VistaApp();
			new ControladorApp(vista);
			vista.setVisible(true);
		});

	}
}