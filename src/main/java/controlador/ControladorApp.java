package controlador;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import Ejercito.Ejercito;
import Excepciones.ReclutaNoValidoException;
import Guerra.Guerra;
import Guerra.InterpretadorGuerra;
import Guerra.RegistroCombates;
import Personajes.Bestia;
import Personajes.Heroe;
import TiposYInterfaces.tipoPersonajes;
import vista.VistaApp;

public class ControladorApp {

	private Ejercito<Heroe> ejercitoHeroes = new Ejercito<>();
	private Ejercito<Bestia> ejercitoBestias = new Ejercito<>();
	private RegistroCombates registroCombates = new RegistroCombates();
	private ArrayList<String> narracion = new ArrayList<String>();
	private ExecutorService executor = Executors.newSingleThreadExecutor();

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
			int armadura = Integer.valueOf(vista.getTextFieldArmaduraHeroe().getText());

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
			String nombre = vista.getTextFieldNombreBestia().getText();

			// Tipo Bestia.
			int indexTipoSeleccionado = vista.getComboBoxTipoBestia().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoBestias()[indexTipoSeleccionado];

			// Vida Bestia.
			int vida = Integer.valueOf(vista.getTextFieldVidaBestia().getText());

			// Armadura Bestia.
			int armadura = Integer.valueOf(vista.getTextFieldArmaduraBestia().getText());

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
				vista.getModeloBestias().removeElement(bestia);
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

		// Si se selecciona un héroe, deseleccionar las bestias y refrescar botones.
		vista.getListHeroes().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) {
				return;
			}

			if (vista.getListHeroes().getSelectedIndex() != -1) {
				vista.getListBestias().clearSelection();
			}

			actualizarBotonesHeroes(vista);
			actualizarBotonesBestias(vista);
		});

		// Si cambia la cantidad de héroes, refrescar botones.
		vista.getModeloHeroes().addListDataListener(new ListDataListener() {
			@Override
			public void intervalAdded(ListDataEvent e) {
				actualizarBotonesHeroes(vista);
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				actualizarBotonesHeroes(vista);
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				actualizarBotonesHeroes(vista);
			}
		});

		// Si se selecciona una bestia, deseleccionar los héroes y refrescar botones.
		vista.getListBestias().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) {
				return;
			}

			if (vista.getListBestias().getSelectedIndex() != -1) {
				vista.getListHeroes().clearSelection();
			}

			actualizarBotonesHeroes(vista);
			actualizarBotonesBestias(vista);
		});

		// Si cambia la cantidad de bestias, refrescar botones.
		vista.getModeloBestias().addListDataListener(new ListDataListener() {
			@Override
			public void intervalAdded(ListDataEvent e) {
				actualizarBotonesBestias(vista);
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				actualizarBotonesBestias(vista);
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				actualizarBotonesBestias(vista);
			}
		});

		SwingWorker<ArrayList<String>, Void> worker = new SwingWorker<ArrayList<String>, Void>() {

			private Future<ArrayList<String>> futuro;

			@Override
			protected ArrayList<String> doInBackground() throws Exception {
				futuro = executor.submit(new InterpretadorGuerra(registroCombates));
				Guerra guerra = new Guerra(ejercitoHeroes.copiar(), ejercitoBestias.copiar(), registroCombates);
				guerra.start();
				return futuro.get();
			}

			protected void done() {
				try {
					narracion = get();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					vista.getTextAreaResultadoGuerra().setText("Se interrumpio la batalla");
				} catch (ExecutionException e) {
					e.getCause().printStackTrace();
					vista.getTextAreaResultadoGuerra().setText("Error durante la batalla");
				} finally {
					vista.getBtnLucha().setEnabled(true);
				}
			}
		};

		vista.getBtnLucha().addActionListener(e -> {
			vista.getTextAreaResultadoGuerra().setText("");
			vista.getBtnLucha().setEnabled(false);
			worker.execute();
			narracion.forEach(element -> vista.getTextAreaResultadoGuerra().append(element));
		});

		// Estado inicial de los botones.
		actualizarBotonesHeroes(vista);
		actualizarBotonesBestias(vista);
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

		// Mantener seleccionada la bestia desplazada.
		vista.getListBestias().setSelectedIndex(nuevaPosicion);
	}

	private void actualizarBotonesHeroes(VistaApp vista) {
		actualizarBotones(vista.getListHeroes(), vista.getModeloHeroes(), vista.getBtnEliminarHeroe(),
				vista.getBtnSubirHeroe(), vista.getBtnBajarHeroe());
	}

	private void actualizarBotonesBestias(VistaApp vista) {
		actualizarBotones(vista.getListBestias(), vista.getModeloBestias(), vista.getBtnEliminarBestia(),
				vista.getBtnSubirBestia(), vista.getBtnBajarBestia());
	}

	private <E> void actualizarBotones(JList<E> lista, DefaultListModel<E> modelo, JButton botonEliminar,
			JButton botonSubir, JButton botonBajar) {

		int cantidad = modelo.getSize();
		boolean haySeleccion = lista.getSelectedIndex() != -1;

		boolean puedeEliminar = cantidad > 0 && haySeleccion;
		botonEliminar.setEnabled(puedeEliminar);

		boolean puedeReordenar = cantidad >= 2 && haySeleccion;
		botonSubir.setEnabled(puedeReordenar);
		botonBajar.setEnabled(puedeReordenar);
	}
}