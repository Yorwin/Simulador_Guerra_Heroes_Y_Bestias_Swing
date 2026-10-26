package controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

	public ControladorApp(VistaApp vista) {

		vista.getBtnAnadirHeroe().addActionListener(e -> {

			// Nombre Heroe
			String nombre = vista.getTextFieldNombreHeroe().getText();

			// Tipo Heroe.
			int indexTipoSeleccionado = vista.getComboBoxTipoHeroe().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoHeroes()[indexTipoSeleccionado];

			try {

				// Vida Heroe.
				int vida = Integer.valueOf(vista.getTextFieldVidaHeroe().getText().trim());

				// Armadura Heroe.
				int armadura = Integer.valueOf(vista.getTextFieldArmaduraHeroe().getText().trim());

				// Crear heroe.
				Heroe heroe = new Heroe.Builder().nombre(nombre).nivelDeResistencia(armadura).puntosDeVida(vida)
						.tipoPersonaje(tipo.getESPECIE()).build();

				// Añadir heroe a ejercito.
				ejercitoHeroes.anadirRecluta(heroe);

				// Modelo Visual de la lista.
				vista.getModeloHeroes().addElement(ejercitoHeroes.getReclutas().get(ejercitoHeroes.length() - 1));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						"La vida y la armadura deben ser números enteros.", "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (IllegalArgumentException e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(), String.format("Recuerda \n %s", e2.getMessage()),
						"Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (ReclutaNoValidoException e3) {
				e3.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha Ocurrido un Error!", e3.getMessage()), "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e4) {
				e4.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha Ocurrido un Error!", e4.getMessage()), "Aviso", JOptionPane.WARNING_MESSAGE);
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

			try {

				// Vida Bestia.
				int vida = Integer.valueOf(vista.getTextFieldVidaBestia().getText());

				// Armadura Bestia.
				int armadura = Integer.valueOf(vista.getTextFieldArmaduraBestia().getText());

				// Crear bestia.
				Bestia bestia = new Bestia.Builder().nombre(nombre).nivelDeResistencia(armadura).puntosDeVida(vida)
						.tipoPersonaje(tipo.getESPECIE()).build();

				// Añadir bestia a ejercito.
				ejercitoBestias.anadirRecluta(bestia);

				// Modelo Visual de la lista.
				vista.getModeloBestias().addElement(ejercitoBestias.getReclutas().get(ejercitoBestias.length() - 1));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						"La vida y la armadura deben ser números enteros.", "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (IllegalArgumentException e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(), String.format("Recuerda \n %s", e2.getMessage()),
						"Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (ReclutaNoValidoException e3) {
				e3.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha Ocurrido un Error!", e3.getMessage()), "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e4) {
				e4.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(), String.format("Recuerda \n %s", e4.getMessage()),
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

		vista.getBtnLucha().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				vista.getTextAreaResultadoGuerra().setText("");
				vista.getBtnLucha().setEnabled(false);

				if (ejercitoHeroes.length() <= 0 || ejercitoBestias.length() <= 0) {
					return;
				}

				RegistroCombates registro = new RegistroCombates();
				Guerra guerra = new Guerra(ejercitoHeroes.copiar(), ejercitoBestias.copiar(), registro);

				new SwingWorker<ArrayList<String>, String>() {

					@Override
					protected ArrayList<String> doInBackground() throws Exception {
						InterpretadorGuerra interpretador = new InterpretadorGuerra(registro, texto -> publish(texto));
						guerra.start();
						return interpretador.call();
					}

					@Override
					protected void process(List<String> lineas) {
						for (String linea : lineas) {
							vista.getTextAreaResultadoGuerra().append(linea);
						}
					}

					@Override
					protected void done() {
						try {
							get();
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
				}.execute();

			}
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