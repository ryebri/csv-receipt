package merchMadeEasier;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txttxt;
	private JTextField textField;
	private JComboBox<Integer> comboBox_1;
	private JComboBox<Integer> comboBox;
	private JRadioButton rdbtnTabDelimited;
	private JRadioButton rdbtnCommaSeparated;
	private JProgressBar progressBar;
	private JButton btnNewButton_3;
	private JTextField textField_1;
	private NewMainStuff stuff;
	
	//used to determine what size the window should be
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		determineOS();
		setTitle("Customer Form GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//This is the first card, where the user inputs information first
		JPanel first_card = new JPanel();
		contentPane.add(first_card, "start_here");
		first_card.setLayout(new BorderLayout(0, 0));
		
		//This creates the north panel on the first card
		JPanel panel_north = new JPanel();
		first_card.add(panel_north, BorderLayout.NORTH);
		GridBagLayout gbl_panel_north = new GridBagLayout();
		gbl_panel_north.columnWidths = new int[]{50, 166, 0};
		gbl_panel_north.rowHeights = new int[] {20, 0, 10};
		gbl_panel_north.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_north.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_north.setLayout(gbl_panel_north);
		
		JLabel lblNewLabel = new JLabel("File Location with Name:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_north.add(lblNewLabel, gbc_lblNewLabel);
		
		
		
		//Where/what the file name is
		txttxt = new JTextField();
		txttxt.setText(".txt");
		GridBagConstraints gbc_txttxt = new GridBagConstraints();
		gbc_txttxt.insets = new Insets(0, 0, 5, 0);
		gbc_txttxt.anchor = GridBagConstraints.NORTHWEST;
		gbc_txttxt.gridx = 1;
		gbc_txttxt.gridy = 0;
		panel_north.add(txttxt, gbc_txttxt);
		txttxt.setColumns(20);
		
		JButton file_gui = new JButton("Open File");
		file_gui.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(null);
				if(status == JFileChooser.APPROVE_OPTION){
					txttxt.setText(chooser.getSelectedFile().getAbsolutePath());
				}
			
			}
		});
		GridBagConstraints gbc_butNewButton = new GridBagConstraints();
		gbc_butNewButton.anchor = GridBagConstraints.EAST;
		gbc_butNewButton.gridx = 2;
		gbc_butNewButton.gridy = 0;
		panel_north.add(file_gui, gbc_butNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Destination Folder:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_north.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		//What folder the printed things will go in.
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_north.add(textField, gbc_textField);
		textField.setColumns(20);
		
		//creates the south panel
		JPanel panel_south = new JPanel();
		first_card.add(panel_south, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_south = new GridBagLayout();
		gbl_panel_south.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5};
		gbl_panel_south.rowHeights = new int[]{0, 0, 0};
		gbl_panel_south.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_south.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_south.setLayout(gbl_panel_south);
		
		//adds the cancel button which will end the program if hit
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				System.exit(EXIT_ON_CLOSE);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panel_south.add(btnNewButton_1, gbc_btnNewButton_1);
		
		//Button to begin all the code and start formatting the files
		JButton btnNewButton = new JButton("Create Forms");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = txttxt.getText();
				String outputLocation = textField.getText();
				if(filename.equals(".txt"))
				{
					JOptionPane.showMessageDialog(contentPane,  "You did not enter a valid File Name", "Input Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if(outputLocation.equals("")){
					JOptionPane.showMessageDialog(contentPane,  "You did not enter a valid Destination", "Input Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int numPerPage = (Integer)comboBox.getSelectedItem();
				int numOfPermCols = (Integer)comboBox_1.getSelectedItem();
				
				File in = new File(filename);
				
				String delimiter = "";
				if(rdbtnTabDelimited.isSelected())
				{
					delimiter = "\\t";
				} else {
					delimiter = ",";
				}
				
				//calls the program to do work
				stuff = new NewMainStuff(in, outputLocation, numPerPage, numOfPermCols, delimiter, progressBar);
				
				//Goes to the second card (the one with the progress bar
				CardLayout cards = (CardLayout)(contentPane.getLayout());
				cards.show(contentPane, "finish_here");
			}
		});
		
		//creates the panel for choosing how many forms per page, and how many columns will be auto printed
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 1;
		panel_south.add(panel_1, gbc_panel_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 1;
		panel_south.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_west = new JPanel();
		first_card.add(panel_west, BorderLayout.WEST);
		GridBagLayout gbl_panel_west = new GridBagLayout();
		gbl_panel_west.columnWidths = new int[] {311, 10, 31, 0};
		gbl_panel_west.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_panel_west.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_west.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_west.setLayout(gbl_panel_west);
		
		JLabel lblNewLabel_1 = new JLabel("Select the number of customer forms you wish to print on 1 page");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_west.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
		panel_west.add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Select the number of lines you wish to have included on all forms");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panel_west.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5, 6}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.anchor = GridBagConstraints.WEST;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 1;
		panel_west.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblSelectWhetherDocument = new JLabel("Select whether document is tab or comma separated");
		lblSelectWhetherDocument.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblSelectWhetherDocument = new GridBagConstraints();
		gbc_lblSelectWhetherDocument.anchor = GridBagConstraints.WEST;
		gbc_lblSelectWhetherDocument.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectWhetherDocument.gridx = 0;
		gbc_lblSelectWhetherDocument.gridy = 2;
		panel_west.add(lblSelectWhetherDocument, gbc_lblSelectWhetherDocument);
		
		//creates the buttons for selecting whether the document is tab delimited or comma delimited
		rdbtnTabDelimited = new JRadioButton("Tab Delimited");
		rdbtnTabDelimited.setSelected(true);
		GridBagConstraints gbc_rdbtnTabDelimited = new GridBagConstraints();
		gbc_rdbtnTabDelimited.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTabDelimited.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTabDelimited.gridx = 0;
		gbc_rdbtnTabDelimited.gridy = 3;
		panel_west.add(rdbtnTabDelimited, gbc_rdbtnTabDelimited);
		
		rdbtnCommaSeparated = new JRadioButton("Comma Separated");
		GridBagConstraints gbc_rdbtnCommaSeparated = new GridBagConstraints();
		gbc_rdbtnCommaSeparated.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCommaSeparated.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnCommaSeparated.gridx = 0;
		gbc_rdbtnCommaSeparated.gridy = 4;
		panel_west.add(rdbtnCommaSeparated, gbc_rdbtnCommaSeparated);
		
		//makes it so only one button can be selected at a time
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnTabDelimited);
		group.add(rdbtnCommaSeparated);
		
		//creates the final card (one with the progress bar)
		JPanel second_card = new JPanel();
		contentPane.add(second_card, "finish_here");
		
		//sets the values of the progress bar
		progressBar = new JProgressBar(0,100);
		//they did enter some values correctly, so they are being given some credit
		progressBar.setValue(10);

		progressBar.setStringPainted(true);
		progressBar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_1.setText("Working on creating your forms");
				if(progressBar.getValue()==progressBar.getMaximum())
				{
					textField_1.setText("Work complete!");
					progressBar.repaint();
					
					JOptionPane.showInternalMessageDialog(contentPane, "Process Complete\n\nTotal Orders: " 
					+ stuff.getTotalCount()
					+ "\nNormal Order Forms: " + stuff.getNormalPageTotal()
					+ "\nSpecial Order Forms: " + stuff.getSpecialOrderCount(), "Success", JOptionPane.PLAIN_MESSAGE);
					dispose();
				}
				if(progressBar.getValue() == 60){
					textField_1.setText("Creating PDF Document For Printing");
					progressBar.repaint();
				}
				if(progressBar.getValue() == 10 ||progressBar.getValue() ==90)
				{
					btnNewButton_3.setEnabled(true);
				} else {
					btnNewButton_3.setEnabled(false);
				}
			}
		});
		
		second_card.setLayout(new BorderLayout(0, 0));
		second_card.add(progressBar, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		second_card.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//allows the user to go back to the previous page 
		//if an error appears they can go back and fix their error instead of exiting the program
		btnNewButton_3 = new JButton("Go Back");
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(contentPane.getLayout());
				c.show(contentPane, "start_here");
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 1;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.WEST;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 4;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		
		//cancels everything entirely
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				stuff.stop();
				
			
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 5;
		gbc_btnNewButton_2.gridy = 1;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		second_card.add(textField_1, BorderLayout.CENTER);
		textField_1.setColumns(10);
	}
	
	private void determineOS(){
		String opS = System.getProperty("os.name").toLowerCase();
		if(opS.contains("mac")){
			x = 200;
			y = 100;
			width = 530;
			height = 300;
			
		} else {

			x = 100;
			y = 100;
			width = 430; //494
			height = 300;
		}

	}

}
