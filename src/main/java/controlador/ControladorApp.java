package controlador;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Ejercito.Ejercito;
import Excepciones.ReclutaNoValidoException;
import Personajes.Bestia;
import Personajes.Heroe;
import TiposYInterfaces.tipoPersonajes;
import vista.VistaApp;

public class ControladorApp {

	Ejercito<Heroe> ejercitoHeroes = new Ejercito<>();
	Ejercito<Bestia> ejercitoBestias = new Ejercito<>();

	public ControladorApp(VistaApp vista) {

		vista.getBtnAnadirHeroe().addActionListener(e -> {

			// Nombre Heroe
			String nombre = vista.getTextFieldNombreHeroe().getText();

			// Tipo Heroe.
			int indexTipoSeleccionado = vista.getComboBoxTipoHeroe().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoHeroes()[indexTipoSeleccionado];

			// Vida Heroe.
			int vida = Integer.valueOf(vista.getTextFieldVidaHeroe().getText());

			// Armadura Heroe.
			int armadura = Integer.valueOf(vista.getTextFieldVidaHeroe().getText());

			Heroe heroe;

			try {
				// Crear heroe.
				heroe = new Heroe.Builder().nombre(nombre).nivelDeResistencia(armadura).puntosDeVida(vida)
						.tipoPersonaje(tipo.getESPECIE()).build();

				// Añadir heroe a ejercito.
				ejercitoHeroes.anadirRecluta(heroe);

				// Modelo Visual de la lista.
				vista.getModeloHeroes().addElement(ejercitoHeroes.getReclutas().get(ejercitoHeroes.length() - 1));
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(), String.format("Recuerda \n %s", e1.getMessage()),
						"Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (ReclutaNoValidoException e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha Ocurrido un Error!", e2.getMessage()), "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e3) {
				e3.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha Ocurrido un Error!", e3.getMessage()), "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		});

		ActionListener listenerHeroes = e -> {
			Heroe heroe;

			switch (e.getActionCommand()) {
			case "eliminar" -> {
				heroe = obtenerHeroeSeleccionado(vista);
				ejercitoHeroes.retirarRecluta(heroe);
				vista.getModeloHeroes().removeElement(heroe);
			}
			case "subir" -> {
				cambiarOrdenHeroe(vista, -1);
			}
			case "bajar" -> {
				cambiarOrdenHeroe(vista, 1);
			}
			}
		};

		// Configurar comandos.
		vista.getBtnEliminarHeroe().setActionCommand("eliminar");
		vista.getBtnSubirHeroe().setActionCommand("subir");
		vista.getBtnBajarHeroe().setActionCommand("bajar");

		// Vincular Listeners.
		vista.getBtnEliminarHeroe().addActionListener(listenerHeroes);
		vista.getBtnSubirHeroe().addActionListener(listenerHeroes);
		vista.getBtnBajarHeroe().addActionListener(listenerHeroes);

		vista.getBtnAnadirBestia().addActionListener(e -> {

			// Nombre Bestia.
			String nombre = vista.getTextFieldNombreHeroe().getText();

			// Tipo Bestia.
			int indexTipoSeleccionado = vista.getComboBoxTipoHeroe().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoHeroes()[indexTipoSeleccionado];

			// Vida Bestia.
			int vida = Integer.valueOf(vista.getTextFieldVidaHeroe().getText());

			// Armadura Bestia.
			int armadura = Integer.valueOf(vista.getTextFieldVidaHeroe().getText());

			Bestia bestia;

			try {
				// Crear bestia.
				bestia = new Bestia.Builder().nombre(nombre).nivelDeResistencia(armadura).puntosDeVida(vida)
						.tipoPersonaje(tipo.getESPECIE()).build();

				// Añadir bestia a ejercito.
				ejercitoBestias.anadirRecluta(bestia);

				// Modelo Visual de la lista.
				vista.getModeloBestias().addElement(ejercitoBestias.getReclutas().get(ejercitoBestias.length() - 1));
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(), String.format("Recuerda \n %s", e1.getMessage()),
						"Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (ReclutaNoValidoException e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha Ocurrido un Error!", e2.getMessage()), "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e3) {
				e3.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(), String.format("Recuerda \n %s", e3.getMessage()),
						"Aviso", JOptionPane.WARNING_MESSAGE);
			}
		});

		ActionListener listenerBestias = e -> {
			Bestia bestia;

			switch (e.getActionCommand()) {
			case "eliminar" -> {
				bestia = obtenerBestiaSeleccionada(vista);
				ejercitoBestias.retirarRecluta(bestia);
				vista.getModeloHeroes().removeElement(bestia);
			}
			case "subir" -> {
				cambiarOrdenBestia(vista, -1);
			}
			case "bajar" -> {
				cambiarOrdenBestia(vista, 1);
			}
			}
		};

		// Configurar comandos.
		vista.getBtnEliminarBestia().setActionCommand("eliminar");
		vista.getBtnSubirBestia().setActionCommand("subir");
		vista.getBtnBajarBestia().setActionCommand("bajar");

		// Vincular Listeners.
		vista.getBtnEliminarBestia().addActionListener(listenerBestias);
		vista.getBtnSubirBestia().addActionListener(listenerBestias);
		vista.getBtnBajarBestia().addActionListener(listenerBestias);

	}

	private Heroe obtenerHeroeSeleccionado(VistaApp vista) {
		return vista.getListHeroes().getSelectedValue();
	}

	private int obtenerIndiceHeroeSeleccionado(VistaApp vista) {
		return vista.getListHeroes().getSelectedIndex();
	}

	private void cambiarOrdenHeroe(VistaApp vista, int desplazamiento) {
		int indiceActual = obtenerIndiceHeroeSeleccionado(vista);

		if (indiceActual < 0) {
			return;
		}

		int nuevaPosicion = indiceActual + desplazamiento;

		if (nuevaPosicion < 0 || nuevaPosicion >= vista.getModeloHeroes().getSize()) {
			return;
		}

		// Actualizar el orden del ejército.
		ejercitoHeroes.cambiarOrden(indiceActual, nuevaPosicion);

		// Actualizar el orden del modelo visual.
		Heroe heroe = vista.getModeloHeroes().remove(indiceActual);
		vista.getModeloHeroes().add(nuevaPosicion, heroe);

		// Mantener seleccionado el heroe desplazado.
		vista.getListHeroes().setSelectedIndex(nuevaPosicion);
	}

	private Bestia obtenerBestiaSeleccionada(VistaApp vista) {
		return vista.getListBestias().getSelectedValue();
	}

	private int obtenerIndiceBestiaSeleccionada(VistaApp vista) {
		return vista.getListBestias().getSelectedIndex();
	}

	private void cambiarOrdenBestia(VistaApp vista, int desplazamiento) {
		int indiceActual = obtenerIndiceBestiaSeleccionada(vista);

		if (indiceActual < 0) {
			return;
		}

		int nuevaPosicion = indiceActual + desplazamiento;

		if (nuevaPosicion < 0 || nuevaPosicion >= vista.getModeloBestias().getSize()) {
			return;
		}

		// Actualizar el orden del ejército.
		ejercitoBestias.cambiarOrden(indiceActual, nuevaPosicion);

		// Actualizar el orden del modelo visual.
		Bestia bestia = vista.getModeloBestias().remove(indiceActual);
		vista.getModeloBestias().add(nuevaPosicion, bestia);

		// Mantener seleccionado el heroe desplazado.
		vista.getListHeroes().setSelectedIndex(nuevaPosicion);
	}
}
