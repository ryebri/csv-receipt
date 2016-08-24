package merchMadeEasier;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		setTitle("Table To Page Print");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		createMainPanel();
		
	}
	
	public void createMainPanel()
	{
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 5, 0, 0));
		
		JPanel spacer1 = new JPanel();
		panel_1.add(spacer1);
		
		JButton cancelButton = new JButton("Cancel");
		panel_1.add(cancelButton);
		
		JPanel spacer2 = new JPanel();
		panel_1.add(spacer2);
		
		JButton nextButtonPg1 = new JButton("Next");
		panel_1.add(nextButtonPg1);
		
		JPanel spacer3 = new JPanel();
		panel_1.add(spacer3);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPaneInfoContainer = new JSplitPane();
		panel_2.add(splitPaneInfoContainer, BorderLayout.CENTER);
		
		JPanel leftSplitPane = new JPanel();
		splitPaneInfoContainer.setLeftComponent(leftSplitPane);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		leftSplitPane.add(chckbxNewCheckBox);
		
		JPanel rightSplitPlane = new JPanel();
		splitPaneInfoContainer.setRightComponent(rightSplitPlane);
		
		JPanel pg1PanelNorth = new JPanel();
		FlowLayout fl_pg1PanelNorth = (FlowLayout) pg1PanelNorth.getLayout();
		fl_pg1PanelNorth.setAlignment(FlowLayout.LEFT);
		panel_2.add(pg1PanelNorth, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("File:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pg1PanelNorth.add(lblNewLabel);
		
		fileName = new JTextField();
		pg1PanelNorth.add(fileName);
		fileName.setColumns(30);
	}

}
