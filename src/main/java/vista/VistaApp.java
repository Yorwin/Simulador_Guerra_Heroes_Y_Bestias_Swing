package vista;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class VistaApp extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField textFieldNombreHeroe;
	private JTextField textFieldVidaHeroe;
	private JTextField textFieldArmaduraHeroe;

	private JTextField textFieldBestiaNombre;
	private JTextField textFieldVidaBestia;
	private JTextField textFieldArmaduraBestia;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VistaApp frame = new VistaApp();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public VistaApp() {

		setTitle("Batalla por la Tierra Media");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();

		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };

		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };

		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 1.0 };

		contentPane.setLayout(gbl_contentPane);

		JPanel heroesPane = new JPanel();

		heroesPane.setBorder(new TitledBorder(null, "Heroes", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));

		GridBagConstraints gbc_heroesPane = new GridBagConstraints();

		gbc_heroesPane.gridx = 0;
		gbc_heroesPane.gridy = 0;
		gbc_heroesPane.weightx = 1.0;
		gbc_heroesPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_heroesPane.insets = new Insets(5, 5, 5, 5);

		contentPane.add(heroesPane, gbc_heroesPane);

		GridBagLayout gbl_heroesPane = new GridBagLayout();

		gbl_heroesPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_heroesPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };

		gbl_heroesPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };

		gbl_heroesPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

		heroesPane.setLayout(gbl_heroesPane);

		JLabel lblNombreHeroes = new JLabel("Nombre:");

		GridBagConstraints gbc_lblNombreHeroes = new GridBagConstraints();

		gbc_lblNombreHeroes.gridx = 0;
		gbc_lblNombreHeroes.gridy = 0;
		gbc_lblNombreHeroes.anchor = GridBagConstraints.LINE_START;
		gbc_lblNombreHeroes.insets = new Insets(3, 5, 3, 5);

		heroesPane.add(lblNombreHeroes, gbc_lblNombreHeroes);

		textFieldNombreHeroe = new JTextField(10);
		lblNombreHeroes.setLabelFor(textFieldNombreHeroe);

		GridBagConstraints gbc_textFieldNombreHeroe = new GridBagConstraints();

		gbc_textFieldNombreHeroe.gridx = 1;
		gbc_textFieldNombreHeroe.gridy = 0;
		gbc_textFieldNombreHeroe.weightx = 1.0;
		gbc_textFieldNombreHeroe.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreHeroe.insets = new Insets(3, 0, 3, 5);

		heroesPane.add(textFieldNombreHeroe, gbc_textFieldNombreHeroe);

		JLabel lblTipoHeroes = new JLabel("Tipo:");

		GridBagConstraints gbc_lblTipoHeroes = new GridBagConstraints();

		gbc_lblTipoHeroes.gridx = 0;
		gbc_lblTipoHeroes.gridy = 1;
		gbc_lblTipoHeroes.anchor = GridBagConstraints.LINE_START;
		gbc_lblTipoHeroes.insets = new Insets(3, 5, 3, 5);

		heroesPane.add(lblTipoHeroes, gbc_lblTipoHeroes);

		JComboBox<String> comboBoxTipoHeroe = new JComboBox<>();
		lblTipoHeroes.setLabelFor(comboBoxTipoHeroe);

		comboBoxTipoHeroe.setModel(new DefaultComboBoxModel<>(new String[] { "Humano", "Elfo", "Hobbit" }));

		comboBoxTipoHeroe.setToolTipText("Selecciona el tipo del héroe que quieres añadir.");

		GridBagConstraints gbc_comboBoxTipoHeroe = new GridBagConstraints();

		gbc_comboBoxTipoHeroe.gridx = 1;
		gbc_comboBoxTipoHeroe.gridy = 1;
		gbc_comboBoxTipoHeroe.weightx = 1.0;
		gbc_comboBoxTipoHeroe.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipoHeroe.insets = new Insets(3, 0, 3, 5);

		heroesPane.add(comboBoxTipoHeroe, gbc_comboBoxTipoHeroe);

		JLabel lblVidaHeroes = new JLabel("Vida:");

		GridBagConstraints gbc_lblVidaHeroes = new GridBagConstraints();

		gbc_lblVidaHeroes.gridx = 0;
		gbc_lblVidaHeroes.gridy = 2;
		gbc_lblVidaHeroes.anchor = GridBagConstraints.LINE_START;
		gbc_lblVidaHeroes.insets = new Insets(3, 5, 3, 5);

		heroesPane.add(lblVidaHeroes, gbc_lblVidaHeroes);

		textFieldVidaHeroe = new JTextField(10);
		lblVidaHeroes.setLabelFor(textFieldVidaHeroe);

		GridBagConstraints gbc_textFieldVidaHeroe = new GridBagConstraints();

		gbc_textFieldVidaHeroe.gridx = 1;
		gbc_textFieldVidaHeroe.gridy = 2;
		gbc_textFieldVidaHeroe.weightx = 1.0;
		gbc_textFieldVidaHeroe.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVidaHeroe.insets = new Insets(3, 0, 3, 5);

		heroesPane.add(textFieldVidaHeroe, gbc_textFieldVidaHeroe);

		JLabel lblArmaduraHeroes = new JLabel("Armadura:");

		GridBagConstraints gbc_lblArmaduraHeroes = new GridBagConstraints();

		gbc_lblArmaduraHeroes.gridx = 0;
		gbc_lblArmaduraHeroes.gridy = 3;
		gbc_lblArmaduraHeroes.anchor = GridBagConstraints.LINE_START;
		gbc_lblArmaduraHeroes.insets = new Insets(3, 5, 3, 5);

		heroesPane.add(lblArmaduraHeroes, gbc_lblArmaduraHeroes);

		textFieldArmaduraHeroe = new JTextField(10);
		lblArmaduraHeroes.setLabelFor(textFieldArmaduraHeroe);

		GridBagConstraints gbc_textFieldArmaduraHeroe = new GridBagConstraints();

		gbc_textFieldArmaduraHeroe.gridx = 1;
		gbc_textFieldArmaduraHeroe.gridy = 3;
		gbc_textFieldArmaduraHeroe.weightx = 1.0;
		gbc_textFieldArmaduraHeroe.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldArmaduraHeroe.insets = new Insets(3, 0, 3, 5);

		heroesPane.add(textFieldArmaduraHeroe, gbc_textFieldArmaduraHeroe);

		JButton btnAnadirHeroe = new JButton("Añadir");

		GridBagConstraints gbc_btnAnadirHeroe = new GridBagConstraints();
		gbc_btnAnadirHeroe.insets = new Insets(5, 40, 5, 40);

		gbc_btnAnadirHeroe.gridx = 0;
		gbc_btnAnadirHeroe.gridy = 4;
		gbc_btnAnadirHeroe.gridwidth = 2;
		gbc_btnAnadirHeroe.fill = GridBagConstraints.HORIZONTAL;

		heroesPane.add(btnAnadirHeroe, gbc_btnAnadirHeroe);

		JPanel bestiasPane = new JPanel();

		bestiasPane.setBorder(new TitledBorder(null, "Bestias", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));

		GridBagConstraints gbc_bestiasPane = new GridBagConstraints();

		gbc_bestiasPane.gridx = 1;
		gbc_bestiasPane.gridy = 0;
		gbc_bestiasPane.weightx = 1.0;
		gbc_bestiasPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_bestiasPane.insets = new Insets(5, 5, 5, 0);

		contentPane.add(bestiasPane, gbc_bestiasPane);

		GridBagLayout gbl_bestiasPane = new GridBagLayout();

		gbl_bestiasPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_bestiasPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };

		gbl_bestiasPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };

		gbl_bestiasPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };

		bestiasPane.setLayout(gbl_bestiasPane);

		JLabel lblNombreBestias = new JLabel("Nombre:");

		GridBagConstraints gbc_lblNombreBestias = new GridBagConstraints();

		gbc_lblNombreBestias.gridx = 0;
		gbc_lblNombreBestias.gridy = 0;
		gbc_lblNombreBestias.anchor = GridBagConstraints.LINE_START;
		gbc_lblNombreBestias.insets = new Insets(3, 5, 3, 5);

		bestiasPane.add(lblNombreBestias, gbc_lblNombreBestias);

		textFieldBestiaNombre = new JTextField(10);
		lblNombreBestias.setLabelFor(textFieldBestiaNombre);

		GridBagConstraints gbc_textFieldBestiaNombre = new GridBagConstraints();

		gbc_textFieldBestiaNombre.gridx = 1;
		gbc_textFieldBestiaNombre.gridy = 0;
		gbc_textFieldBestiaNombre.weightx = 1.0;
		gbc_textFieldBestiaNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBestiaNombre.insets = new Insets(3, 0, 3, 5);

		bestiasPane.add(textFieldBestiaNombre, gbc_textFieldBestiaNombre);

		JLabel lblTipoBestias = new JLabel("Tipo:");

		GridBagConstraints gbc_lblTipoBestias = new GridBagConstraints();

		gbc_lblTipoBestias.gridx = 0;
		gbc_lblTipoBestias.gridy = 1;
		gbc_lblTipoBestias.anchor = GridBagConstraints.LINE_START;
		gbc_lblTipoBestias.insets = new Insets(3, 5, 3, 5);

		bestiasPane.add(lblTipoBestias, gbc_lblTipoBestias);

		JComboBox<String> comboBoxTipoBestia = new JComboBox<>();
		lblTipoBestias.setLabelFor(comboBoxTipoBestia);

		comboBoxTipoBestia.setModel(new DefaultComboBoxModel<>(new String[] { "Orco", "Trasgo" }));

		comboBoxTipoBestia.setToolTipText("Selecciona el tipo de bestia que quieres añadir.");

		GridBagConstraints gbc_comboBoxTipoBestia = new GridBagConstraints();

		gbc_comboBoxTipoBestia.gridx = 1;
		gbc_comboBoxTipoBestia.gridy = 1;
		gbc_comboBoxTipoBestia.weightx = 1.0;
		gbc_comboBoxTipoBestia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipoBestia.insets = new Insets(3, 0, 3, 5);

		bestiasPane.add(comboBoxTipoBestia, gbc_comboBoxTipoBestia);

		JLabel lblVidaBestias = new JLabel("Vida:");

		GridBagConstraints gbc_lblVidaBestias = new GridBagConstraints();

		gbc_lblVidaBestias.gridx = 0;
		gbc_lblVidaBestias.gridy = 2;
		gbc_lblVidaBestias.anchor = GridBagConstraints.LINE_START;
		gbc_lblVidaBestias.insets = new Insets(3, 5, 3, 5);

		bestiasPane.add(lblVidaBestias, gbc_lblVidaBestias);

		textFieldVidaBestia = new JTextField(10);
		lblVidaBestias.setLabelFor(textFieldVidaBestia);

		GridBagConstraints gbc_textFieldVidaBestia = new GridBagConstraints();

		gbc_textFieldVidaBestia.gridx = 1;
		gbc_textFieldVidaBestia.gridy = 2;
		gbc_textFieldVidaBestia.weightx = 1.0;
		gbc_textFieldVidaBestia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVidaBestia.insets = new Insets(3, 0, 3, 5);

		bestiasPane.add(textFieldVidaBestia, gbc_textFieldVidaBestia);

		JLabel lblArmaduraBestias = new JLabel("Armadura:");

		GridBagConstraints gbc_lblArmaduraBestias = new GridBagConstraints();

		gbc_lblArmaduraBestias.gridx = 0;
		gbc_lblArmaduraBestias.gridy = 3;
		gbc_lblArmaduraBestias.anchor = GridBagConstraints.LINE_START;
		gbc_lblArmaduraBestias.insets = new Insets(3, 5, 3, 5);

		bestiasPane.add(lblArmaduraBestias, gbc_lblArmaduraBestias);

		textFieldArmaduraBestia = new JTextField(10);
		lblArmaduraBestias.setLabelFor(textFieldArmaduraBestia);

		GridBagConstraints gbc_textFieldArmaduraBestia = new GridBagConstraints();

		gbc_textFieldArmaduraBestia.gridx = 1;
		gbc_textFieldArmaduraBestia.gridy = 3;
		gbc_textFieldArmaduraBestia.weightx = 1.0;
		gbc_textFieldArmaduraBestia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldArmaduraBestia.insets = new Insets(3, 0, 3, 5);

		bestiasPane.add(textFieldArmaduraBestia, gbc_textFieldArmaduraBestia);

		JButton btnAnadirBestia = new JButton("Añadir");

		GridBagConstraints gbc_btnAnadirBestia = new GridBagConstraints();

		gbc_btnAnadirBestia.gridx = 0;
		gbc_btnAnadirBestia.gridy = 4;
		gbc_btnAnadirBestia.gridwidth = 2;
		gbc_btnAnadirBestia.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAnadirBestia.insets = new Insets(5, 40, 5, 40);

		bestiasPane.add(btnAnadirBestia, gbc_btnAnadirBestia);

		JPanel luchaPane = new JPanel();

		luchaPane.setBorder(new TitledBorder(null, "Lucha", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));

		GridBagConstraints gbc_luchaPane = new GridBagConstraints();
		gbc_luchaPane.insets = new Insets(5, 5, 5, 0);

		gbc_luchaPane.gridx = 0;
		gbc_luchaPane.gridy = 1;
		gbc_luchaPane.gridwidth = 2;
		gbc_luchaPane.weightx = 1.0;
		gbc_luchaPane.weighty = 1.0;
		gbc_luchaPane.fill = GridBagConstraints.BOTH;

		contentPane.add(luchaPane, gbc_luchaPane);
		luchaPane.setLayout(new BoxLayout(luchaPane, BoxLayout.Y_AXIS));

		JPanel panelEjercitos = new JPanel();
		luchaPane.add(panelEjercitos);
		GridBagLayout gbl_panelEjercitos = new GridBagLayout();
		gbl_panelEjercitos.columnWidths = new int[] { 0, 0 };
		gbl_panelEjercitos.rowHeights = new int[] { 0 };
		gbl_panelEjercitos.columnWeights = new double[] { 1.0, 0.0 };
		gbl_panelEjercitos.rowWeights = new double[] { 1.0 };
		panelEjercitos.setLayout(gbl_panelEjercitos);

		JPanel panelHeroes = new JPanel();
		GridBagConstraints gbc_panelHeroes = new GridBagConstraints();
		gbc_panelHeroes.weightx = 1.0;
		gbc_panelHeroes.insets = new Insets(15, 15, 15, 15);
		gbc_panelHeroes.fill = GridBagConstraints.BOTH;
		gbc_panelHeroes.gridx = 0;
		gbc_panelHeroes.gridy = 0;
		panelEjercitos.add(panelHeroes, gbc_panelHeroes);
		GridBagLayout gbl_panelHeroes = new GridBagLayout();
		gbl_panelHeroes.columnWidths = new int[] { 0, 0 };
		gbl_panelHeroes.rowHeights = new int[] { 0, 0 };
		gbl_panelHeroes.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelHeroes.rowWeights = new double[] { 0.0, 0.0 };
		panelHeroes.setLayout(gbl_panelHeroes);

		JPanel panelListaHeroes = new JPanel();
		panelListaHeroes.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Heroes",
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelListaHeroes = new GridBagConstraints();
		gbc_panelListaHeroes.weighty = 1.0;
		gbc_panelListaHeroes.weightx = 1.0;
		gbc_panelListaHeroes.fill = GridBagConstraints.BOTH;
		gbc_panelListaHeroes.gridx = 0;
		gbc_panelListaHeroes.gridy = 0;
		panelHeroes.add(panelListaHeroes, gbc_panelListaHeroes);

		String[] opciones = { "Ejemplo 1", "Ejemplo 2", "Ejemplo 3", "Ejemplo 4", "Ejemplo 5", "Ejemplo 6" };
		panelListaHeroes.setLayout(new GridLayout(0, 1, 0, 0));
		JList<String> listHeroes = new JList<>(opciones);
		JScrollPane scrollPaneListaHeroes = new JScrollPane(listHeroes);
		scrollPaneListaHeroes.setPreferredSize(new Dimension(200, 100));
		listHeroes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelListaHeroes.add(scrollPaneListaHeroes);

		JPanel panelOpcionesListaHeroes = new JPanel();
		GridBagConstraints gbc_panelOpcionesListaHeroes = new GridBagConstraints();
		gbc_panelOpcionesListaHeroes.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelOpcionesListaHeroes.weightx = 1.0;
		gbc_panelOpcionesListaHeroes.gridx = 0;
		gbc_panelOpcionesListaHeroes.gridy = 1;
		panelHeroes.add(panelOpcionesListaHeroes, gbc_panelOpcionesListaHeroes);
		panelOpcionesListaHeroes.setLayout(new GridLayout(1, 3, 5, 5));

		JButton btnSubirHeroe = new JButton("Subir");
		panelOpcionesListaHeroes.add(btnSubirHeroe);

		JButton btnBajarHeroe = new JButton("Bajar");
		panelOpcionesListaHeroes.add(btnBajarHeroe);

		JButton btnEliminarHeroe = new JButton("Eliminar");
		btnEliminarHeroe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelOpcionesListaHeroes.add(btnEliminarHeroe);

		JPanel panelBestias = new JPanel();
		GridBagConstraints gbc_panelBestias = new GridBagConstraints();
		gbc_panelBestias.weightx = 1.0;
		gbc_panelBestias.insets = new Insets(15, 15, 15, 15);
		gbc_panelBestias.fill = GridBagConstraints.BOTH;
		gbc_panelBestias.gridx = 1;
		gbc_panelBestias.gridy = 0;
		panelEjercitos.add(panelBestias, gbc_panelBestias);
		GridBagLayout gbl_panelBestias = new GridBagLayout();
		gbl_panelBestias.columnWidths = new int[] { 0, 0 };
		gbl_panelBestias.rowHeights = new int[] { 0, 0 };
		gbl_panelBestias.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelBestias.rowWeights = new double[] { 0.0, 0.0 };
		panelBestias.setLayout(gbl_panelBestias);

		JPanel panelListaBestias = new JPanel();
		panelListaBestias
				.setBorder(new TitledBorder(null, "Bestias", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_panelListaBestias = new GridBagConstraints();
		gbc_panelListaBestias.weighty = 1.0;
		gbc_panelListaBestias.fill = GridBagConstraints.BOTH;
		gbc_panelListaBestias.insets = new Insets(0, 0, 5, 0);
		gbc_panelListaBestias.gridx = 0;
		gbc_panelListaBestias.gridy = 0;
		panelBestias.add(panelListaBestias, gbc_panelListaBestias);

		String[] opcionesBestias = { "Ejemplo 2", "Ejemplo 1", "Ejemplo 6", "Ejemplo 7", "Ejemplo 4", "Ejemplo 3" };
		panelListaBestias.setLayout(new GridLayout(0, 1, 0, 0));
		JList<String> listBestias = new JList<>(opcionesBestias);
		JScrollPane scrollPaneListaBestias = new JScrollPane(listBestias);
		scrollPaneListaBestias.setPreferredSize(new Dimension(200, 100));
		listBestias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelListaBestias.add(scrollPaneListaBestias);

		JPanel panelOpcionesListaBestias = new JPanel();
		GridBagConstraints gbc_panelOpcionesListaBestias = new GridBagConstraints();
		gbc_panelOpcionesListaBestias.weightx = 1.0;
		gbc_panelOpcionesListaBestias.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelOpcionesListaBestias.gridx = 0;
		gbc_panelOpcionesListaBestias.gridy = 1;
		panelBestias.add(panelOpcionesListaBestias, gbc_panelOpcionesListaBestias);
		panelOpcionesListaBestias.setLayout(new GridLayout(1, 3, 5, 5));

		JButton btnSubirBestia = new JButton("Subir");
		panelOpcionesListaBestias.add(btnSubirBestia);

		JButton btnBajarBestia = new JButton("Bajar");
		panelOpcionesListaBestias.add(btnBajarBestia);

		JButton btnEliminarBestia = new JButton("Eliminar");
		panelOpcionesListaBestias.add(btnEliminarBestia);

		JPanel panelBtnLucha = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBtnLucha.getLayout();
		flowLayout.setVgap(6);
		luchaPane.add(panelBtnLucha);

		JButton btnlucha = new JButton("¡LUCHA!");
		btnlucha.setMargin(new Insets(2, 45, 2, 45));
		btnlucha.setAlignmentX(0.5f);
		panelBtnLucha.add(btnlucha);

		JPanel panelResultado = new JPanel();
		GridBagConstraints gbc_panelResultado = new GridBagConstraints();
		gbc_panelResultado.gridwidth = 2;
		gbc_panelResultado.insets = new Insets(0, 5, 5, 5);
		gbc_panelResultado.fill = GridBagConstraints.BOTH;
		gbc_panelResultado.gridx = 0;
		gbc_panelResultado.gridy = 2;
		contentPane.add(panelResultado, gbc_panelResultado);
		panelResultado.setLayout(new GridLayout(0, 1, 0, 0));

		JTextArea textAreaResultadoGuerra = new JTextArea();
		panelResultado.add(textAreaResultadoGuerra);
	}
}