package controlador;

import java.awt.event.ActionListener;
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

/**
 * Controlador principal de la aplicación.
 * <p>
 * Conecta la vista ({@link VistaApp}) con el modelo: gestiona los ejércitos de
 * héroes y bestias, mantiene sincronizadas las listas visuales con ellos y
 * lanza las guerras en segundo plano para no bloquear la interfaz.
 * </p>
 */
public class ControladorApp {

	// Ejércitos reales: lo que el usuario construye desde la interfaz.
	// La guerra nunca trabaja con ellos directamente, sino con copias.
	private Ejercito<Heroe> ejercitoHeroes = new Ejercito<>();
	private Ejercito<Bestia> ejercitoBestias = new Ejercito<>();
	
	public ControladorApp(VistaApp vista) {

		// ---------------------------------------------------------------------
		// HÉROES
		// ---------------------------------------------------------------------

		vista.getBtnAnadirHeroe().addActionListener(e -> {

			// Nombre Heroe.
			String nombre = vista.getTextFieldNombreHeroe().getText();

			// Tipo Heroe.
			int indexTipoSeleccionado = vista.getComboBoxTipoHeroe().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoHeroes()[indexTipoSeleccionado];

			// Los campos numéricos llegan como texto, así que la conversión puede fallar.
			// Por eso va dentro del try: un valor no numérico se captura como
			// NumberFormatException y no se escapa del listener sin avisar al usuario.
			try {

				// Vida Heroe.
				int vida = Integer.parseInt(vista.getTextFieldVidaHeroe().getText().trim());

				// Armadura Heroe.
				int armadura = Integer.parseInt(vista.getTextFieldArmaduraHeroe().getText().trim());

				// Crear heroe.
				Heroe heroe = new Heroe.Builder().nombre(nombre).nivelDeResistencia(armadura).puntosDeVida(vida)
						.tipoPersonaje(tipo.getESPECIE()).build();

				// Añadir heroe a ejercito.
				ejercitoHeroes.anadirRecluta(heroe);

				// Modelo visual de la lista: se añade el último recluta del ejército, de modo
				// que la lista visual y el ejército contengan exactamente el mismo objeto.
				vista.getModeloHeroes().addElement(ejercitoHeroes.getReclutas().get(ejercitoHeroes.length() - 1));

				// Orden de los catch: de más específico a más general.
				// NumberFormatException es subclase de IllegalArgumentException, por eso va
				// primero; si no, nunca se alcanzaría.
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						"La vida y la armadura deben ser números enteros.", "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (IllegalArgumentException e2) {
				// Datos fuera de rango o inválidos detectados por el Builder.
				e2.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(), String.format("Recuerda \n %s", e2.getMessage()),
						"Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (ReclutaNoValidoException e3) {
				e3.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha ocurrido un error!\n%s", e3.getMessage()), "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e4) {
				// Red de seguridad para cualquier otro fallo inesperado.
				e4.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha ocurrido un error!\n%s", e4.getMessage()), "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		// Un solo listener para los tres botones de la lista: el actionCommand indica
		// cuál de ellos se ha pulsado.
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

		// ---------------------------------------------------------------------
		// BESTIAS (misma lógica que los héroes)
		// ---------------------------------------------------------------------

		vista.getBtnAnadirBestia().addActionListener(e -> {

			// Nombre Bestia.
			String nombre = vista.getTextFieldNombreBestia().getText();

			// Tipo Bestia.
			int indexTipoSeleccionado = vista.getComboBoxTipoBestia().getSelectedIndex();
			tipoPersonajes tipo = tipoPersonajes.getTipoBestias()[indexTipoSeleccionado];

			// Igual que con los héroes: la conversión de texto a número va dentro del try.
			try {

				// Vida Bestia.
				int vida = Integer.parseInt(vista.getTextFieldVidaBestia().getText().trim());

				// Armadura Bestia.
				int armadura = Integer.parseInt(vista.getTextFieldArmaduraBestia().getText().trim());

				// Crear bestia.
				Bestia bestia = new Bestia.Builder().nombre(nombre).nivelDeResistencia(armadura).puntosDeVida(vida)
						.tipoPersonaje(tipo.getESPECIE()).build();

				// Añadir bestia a ejercito.
				ejercitoBestias.anadirRecluta(bestia);

				// Modelo visual de la lista.
				vista.getModeloBestias().addElement(ejercitoBestias.getReclutas().get(ejercitoBestias.length() - 1));

				// El catch más específico va siempre primero.
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
						String.format("¡Ha ocurrido un error!\n%s", e3.getMessage()), "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e4) {
				e4.printStackTrace();
				JOptionPane.showMessageDialog(vista.getContentPane(),
						String.format("¡Ha ocurrido un error!\n%s", e4.getMessage()), "Aviso",
						JOptionPane.WARNING_MESSAGE);
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

		// ---------------------------------------------------------------------
		// SELECCIÓN Y ESTADO DE LOS BOTONES
		// ---------------------------------------------------------------------

		// Solo puede haber un elemento seleccionado entre las dos listas: al elegir en
		// una se limpia la otra. getValueIsAdjusting() ignora los eventos intermedios
		// de un mismo clic y evita ejecutar esto dos veces.
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

		// Los botones dependen del contenido de la lista y no solo de la selección:
		// este listener los refresca cuando se añaden o quitan héroes.
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

		// Si se selecciona una bestia, se deseleccionan los héroes y se refrescan los
		// botones.
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

		// Si cambia la cantidad de bestias, se refrescan los botones.
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

		// ---------------------------------------------------------------------
		// GUERRA
		// ---------------------------------------------------------------------

		// ActionListener en lugar de MouseListener: un botón deshabilitado no dispara
		// ActionEvents, pero sí sigue recibiendo clics de ratón. Con mouseClicked se
		// podría lanzar una segunda guerra mientras la primera sigue en marcha.
		vista.getBtnLucha().addActionListener(e -> {

			// Sin combatientes en alguno de los bandos no hay guerra posible.
			// Se comprueba ANTES de deshabilitar el botón: si se hiciera después, el
			// return dejaría el botón bloqueado para siempre.
			if (ejercitoHeroes.length() == 0 || ejercitoBestias.length() == 0) {
				JOptionPane.showMessageDialog(vista.getContentPane(),
						"Necesitas al menos un héroe y una bestia para luchar.", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}

			vista.getTextAreaResultadoGuerra().setText("");
			vista.getBtnLucha().setEnabled(false);

			// La preparación se hace aquí, en el EDT, y no dentro del worker: así nadie
			// puede modificar los ejércitos mientras se copian. La guerra trabaja con
			// copias profundas, por lo que las listas de la vista no se ven afectadas.
			// Cada guerra usa su propio registro para no heredar el estado de la anterior.
			RegistroCombates registro = new RegistroCombates();
			Guerra guerra = new Guerra(ejercitoHeroes.copiar(), ejercitoBestias.copiar(), registro);

			// T = ArrayList<String>: narración completa (resultado final).
			// V = String: cada línea que se publica mientras la guerra avanza.
			new SwingWorker<ArrayList<String>, String>() {

				// Se ejecuta en un hilo de fondo: aquí NO se puede tocar la interfaz.
				@Override
				protected ArrayList<String> doInBackground() throws Exception {
					// La lambda se escribe aquí porque publish() solo es accesible desde el
					// worker. El intérprete solo conoce un Consumer<String>, no Swing.
					InterpretadorGuerra interpretador = new InterpretadorGuerra(registro, texto -> publish(texto));

					// La batalla corre en su propio hilo...
					guerra.start();

					// ...y este hilo interpreta el registro y espera hasta el final.
					return interpretador.call();
				}

				// Se ejecuta en el EDT: puede modificar la interfaz con seguridad.
				// Recibe un lote con las líneas nuevas desde la última llamada, así que hay
				// que añadirlas todas, no solo la última.
				@Override
				protected void process(List<String> lineas) {
					for (String linea : lineas) {
						vista.getTextAreaResultadoGuerra().append(linea);
					}
				}

				// También en el EDT, al terminar. No se vuelve a pintar la narración porque
				// process() ya la ha mostrado (si no, saldría duplicada). get() se mantiene
				// porque es lo que hace aflorar las excepciones de doInBackground().
				@Override
				protected void done() {
					try {
						get();
					} catch (InterruptedException ex) {
						// Restaura la marca de interrupción que get() ha limpiado.
						Thread.currentThread().interrupt();
						vista.getTextAreaResultadoGuerra().setText("Se interrumpió la batalla");
					} catch (ExecutionException ex) {
						// La causa real del fallo está dentro de la ExecutionException.
						ex.getCause().printStackTrace();
						vista.getTextAreaResultadoGuerra().setText("Error durante la batalla");
					} finally {
						// El botón se reactiva pase lo que pase.
						vista.getBtnLucha().setEnabled(true);
					}
				}
			}.execute();
		});

		// Estado inicial de los botones.
		actualizarBotonesHeroes(vista);
		actualizarBotonesBestias(vista);
	}

	// -------------------------------------------------------------------------
	// MÉTODOS AUXILIARES: HÉROES
	// -------------------------------------------------------------------------

	private Heroe obtenerHeroeSeleccionado(VistaApp vista) {
		return vista.getListHeroes().getSelectedValue();
	}

	private int obtenerIndiceHeroeSeleccionado(VistaApp vista) {
		return vista.getListHeroes().getSelectedIndex();
	}

	/**
	 * Mueve el héroe seleccionado dentro de la lista.
	 *
	 * @param desplazamiento -1 lo sube una posición, +1 lo baja una posición.
	 */
	private void cambiarOrdenHeroe(VistaApp vista, int desplazamiento) {
		int indiceActual = obtenerIndiceHeroeSeleccionado(vista);

		// Sin selección no hay nada que mover.
		if (indiceActual < 0) {
			return;
		}

		int nuevaPosicion = indiceActual + desplazamiento;

		// No se puede salir de los límites de la lista.
		if (nuevaPosicion < 0 || nuevaPosicion >= vista.getModeloHeroes().getSize()) {
			return;
		}

		// Se actualizan a la vez el ejército y el modelo visual para que no se
		// desincronicen.
		ejercitoHeroes.cambiarOrden(indiceActual, nuevaPosicion);

		Heroe heroe = vista.getModeloHeroes().remove(indiceActual);
		vista.getModeloHeroes().add(nuevaPosicion, heroe);

		// Mantener seleccionado el héroe desplazado.
		vista.getListHeroes().setSelectedIndex(nuevaPosicion);
	}

	// -------------------------------------------------------------------------
	// MÉTODOS AUXILIARES: BESTIAS
	// -------------------------------------------------------------------------

	private Bestia obtenerBestiaSeleccionada(VistaApp vista) {
		return vista.getListBestias().getSelectedValue();
	}

	private int obtenerIndiceBestiaSeleccionada(VistaApp vista) {
		return vista.getListBestias().getSelectedIndex();
	}

	/**
	 * Mueve la bestia seleccionada dentro de la lista.
	 *
	 * @param desplazamiento -1 la sube una posición, +1 la baja una posición.
	 */
	private void cambiarOrdenBestia(VistaApp vista, int desplazamiento) {
		int indiceActual = obtenerIndiceBestiaSeleccionada(vista);

		// Sin selección no hay nada que mover.
		if (indiceActual < 0) {
			return;
		}

		int nuevaPosicion = indiceActual + desplazamiento;

		// No se puede salir de los límites de la lista.
		if (nuevaPosicion < 0 || nuevaPosicion >= vista.getModeloBestias().getSize()) {
			return;
		}

		// Se actualizan a la vez el ejército y el modelo visual.
		ejercitoBestias.cambiarOrden(indiceActual, nuevaPosicion);

		Bestia bestia = vista.getModeloBestias().remove(indiceActual);
		vista.getModeloBestias().add(nuevaPosicion, bestia);

		// Mantener seleccionada la bestia desplazada.
		vista.getListBestias().setSelectedIndex(nuevaPosicion);
	}

	// -------------------------------------------------------------------------
	// ESTADO DE LOS BOTONES
	// -------------------------------------------------------------------------

	private void actualizarBotonesHeroes(VistaApp vista) {
		actualizarBotones(vista.getListHeroes(), vista.getModeloHeroes(), vista.getBtnEliminarHeroe(),
				vista.getBtnSubirHeroe(), vista.getBtnBajarHeroe());
	}

	private void actualizarBotonesBestias(VistaApp vista) {
		actualizarBotones(vista.getListBestias(), vista.getModeloBestias(), vista.getBtnEliminarBestia(),
				vista.getBtnSubirBestia(), vista.getBtnBajarBestia());
	}

	/**
	 * Habilita o deshabilita los botones de una lista según su contenido y su
	 * selección. Es genérico para servir tanto a la lista de héroes como a la de
	 * bestias.
	 * <ul>
	 * <li>Eliminar: hace falta un elemento seleccionado.</li>
	 * <li>Subir y bajar: hacen falta al menos dos elementos (si no, no hay a dónde
	 * moverlo) y uno seleccionado.</li>
	 * </ul>
	 */
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