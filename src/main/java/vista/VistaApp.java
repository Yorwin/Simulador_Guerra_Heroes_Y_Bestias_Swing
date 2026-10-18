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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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

		setTitle("Simulador de guerra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();

		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };

		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };

		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };

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
		gbc_bestiasPane.insets = new Insets(5, 5, 5, 5);

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
		
		gbc_luchaPane.gridx = 0;
		gbc_luchaPane.gridy = 1;
		gbc_luchaPane.gridwidth = 2;
		gbc_luchaPane.weightx = 1.0;
		gbc_luchaPane.weighty = 1.0;
		gbc_luchaPane.fill = GridBagConstraints.BOTH;
		gbc_luchaPane.insets = new Insets(5, 5, 5, 5);

		contentPane.add(luchaPane, gbc_luchaPane);
	}
}