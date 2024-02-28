package menuPacote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import celulaPacote.Campo;
import celulaPacote.CampoMedio;

public class DificuldadesCampo extends JFrame implements ActionListener {

	private JButton hardButton;
	private JButton mediumButton;
	private JButton easyButton;
	private JButton backButton;

	private static final long serialVersionUID = 1L;

	public DificuldadesCampo() {
		this.setTitle("Campo Minado");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(500, 500);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0, 0, 50));

		MatteBorder labelBorder = BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(100, 200, 200));
		JLabel label = new JLabel("Campo Minado");
		label.setForeground(new Color(150, 150, 150));
		label.setFont(new Font("Arialxz", Font.BOLD, 33));
		label.setHorizontalAlignment(JLabel.CENTER);
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textPanel.setBackground(new Color(220, 220, 220));
		textPanel.setBorder(labelBorder);
		textPanel.add(label);

		this.add(textPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 1, 10, 10));
		centerPanel.setBackground(new Color(240, 240, 240));

		easyButton = createStyledButton("Modo Facil", new Color(173, 216, 230), new Color(70, 130, 180));
		mediumButton = createStyledButton("Modo Medio", new Color(255, 228, 196), new Color(210, 105, 30));
		hardButton = createStyledButton("Modo Dificil", new Color(240, 128, 128), new Color(178, 34, 34));
		backButton = createStyledButton("Back", new Color(240, 248, 255), new Color(0, 0, 128));

		easyButton.addActionListener(this);
		mediumButton.addActionListener(this); 
		hardButton.addActionListener(this);
		backButton.addActionListener(this);

		centerPanel.add(easyButton);
		centerPanel.add(mediumButton);
		centerPanel.add(hardButton);
		centerPanel.add(backButton);

		this.add(centerPanel, BorderLayout.CENTER);

		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private JButton createStyledButton(String text, Color backgroundColor, Color foregroundColor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Verdana", Font.BOLD, 20));
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.setFocusPainted(false);
		return button;
	}

	private static Campo escolherDificuldade(int escolha) {
		switch (escolha) {
		case 0:
			return new CampoMedio(5, 5, 5);

		case 1:
			return new CampoMedio(10, 10, 13);

		case 2:
			return new CampoMedio(17, 17, 17);

		default:
			System.exit(0);
			return null;
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				new DificuldadesCampo().setVisible(true);
				;
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == easyButton) {
			dispose();
			new CampoMinadoGUI(escolherDificuldade(0));
		}

		if (e.getSource() == mediumButton) {
			dispose();
			new CampoMinadoGUI(escolherDificuldade(1));
		}

		if (e.getSource() == hardButton) {
			dispose();
			new CampoMinadoGUI(escolherDificuldade(2));
		}
		if (e.getSource() == backButton) {
			dispose();
			new Menu();
		}
	}

}
