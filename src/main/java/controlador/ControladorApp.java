package controlador;

import TiposYInterfaces.tipoPersonajes;
import vista.VistaApp;

public class ControladorApp {

	public ControladorApp(VistaApp vista) {
		vista.getBtnAnadirHeroe().addActionListener(e -> {

			// Nombre Heroe
			String nombre = vista.getTextFieldNombreHeroe().getText();

			// Tipo Heroe.
			int indexTipoSeleccionado = vista.getComboBoxTipoHeroe().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoHeroes()[indexTipoSeleccionado];

			// Vida Heroe.
			String vida = vista.getTextFieldVidaHeroe().getText();

			// Armadura Heroe.
			String armadura = vista.getTextFieldVidaHeroe().getText();
		});

		vista.getBtnAnadirBestia().addActionListener(e -> {

			// Nombre Bestia.
			String nombre = vista.getTextFieldNombreHeroe().getText();

			// Tipo Bestia.
			int indexTipoSeleccionado = vista.getComboBoxTipoHeroe().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoHeroes()[indexTipoSeleccionado];

			// Vida Bestia.
			String vida = vista.getTextFieldVidaHeroe().getText();

			// Armadura Bestia.
			String armadura = vista.getTextFieldVidaHeroe().getText();
		});
	}
}
