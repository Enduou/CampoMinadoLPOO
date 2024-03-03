package guiPacote;

import javax.swing.*;
import javax.swing.border.Border;

import jogadorPacote.Jogador;
import pontuacaoPacote.GerenciadorDePontuacoes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


public class Menu extends JFrame {
 static final long serialVersionUID = 1L;
 private GerenciadorDePontuacoes gerenciador;

 private void mostrarPontuacoes() {
	    StringBuilder sb = new StringBuilder();
	    List<Jogador> jogadores = gerenciador.getJogadoresLista();
	    for (Jogador jogador : jogadores) {
	        sb.append(jogador.getNome()).append(": ").append(jogador.getPontos()).append("\n");
	    }
	    
	    JOptionPane.showMessageDialog(this, sb.toString().isEmpty() ? "Nenhuma pontuação registrada." : sb.toString(), "Pontuações", JOptionPane.INFORMATION_MESSAGE);
	}

 
 public Menu() {

		gerenciador = GerenciadorDePontuacoes.getInstance();
	    gerenciador.carregaRecorde("/CampoMinadoLPOO/src/pontos");
		
		setTitle("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new GridLayout(0, 1, 10, 10));
        setSize(500, 500);
        setResizable(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS)); 
        titlePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GREEN, 2), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); 

        JLabel titleLabel = new JLabel("Campo Minado - LPOO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GREEN)); 
        titlePanel.add(titleLabel);

        JLabel subTitleLabel = new JLabel("feito por Eduardo Castro e Paulo Carvalho");
        subTitleLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        subTitleLabel.setForeground(Color.LIGHT_GRAY);
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titlePanel.add(subTitleLabel);

        add(titlePanel);
        
        
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = Color.BLACK;
        Color textColor = Color.GREEN;
        Border buttonBorder = BorderFactory.createLineBorder(textColor, 2);

        JButton novoJogoButton = new JButton("Campo Minado");
        novoJogoButton.setFont(buttonFont);
        novoJogoButton.setForeground(textColor);
        novoJogoButton.setBackground(buttonColor);
        novoJogoButton.setBorder(buttonBorder);
        novoJogoButton.setFocusPainted(false);
        novoJogoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new DificuldadesCampo().setVisible(true);
                    }
                });
            }
        });
        add(novoJogoButton);
        
        JButton minadoMalucoButton = new JButton("Campo Minado Maluco");
        minadoMalucoButton.setFont(buttonFont);
        minadoMalucoButton.setForeground(textColor);
        minadoMalucoButton.setBackground(buttonColor);
        minadoMalucoButton.setBorder(buttonBorder);
        minadoMalucoButton.setFocusPainted(false);
        minadoMalucoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new DificuldadesMaluco();
                    }
                });
            }
        });
        add(minadoMalucoButton);

        JButton doisJogadoresButton2 = new JButton("Dois Jogadores");
        doisJogadoresButton2.setFont(buttonFont);
        doisJogadoresButton2.setForeground(textColor);
        doisJogadoresButton2.setBackground(buttonColor);
        doisJogadoresButton2.setBorder(buttonBorder);
        doisJogadoresButton2.setFocusPainted(false);
        doisJogadoresButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new DificuldadesCampoDuo();
                    }
                });
            }
        });
        add(doisJogadoresButton2);

      

        JButton pontuacaoButton = new JButton("Pontuação");
        pontuacaoButton.setFont(buttonFont);
        pontuacaoButton.setForeground(textColor);
        pontuacaoButton.setBackground(buttonColor);
        pontuacaoButton.setBorder(buttonBorder);
        pontuacaoButton.setFocusPainted(false);
        pontuacaoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JButton pontuacaoButton = new JButton("Pontuação");
            	pontuacaoButton.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
            			mostrarPontuacoes();
            	    }
            	});
            }
        });
        add(pontuacaoButton);

        JButton sairButton = new JButton("Sair");
        sairButton.setFont(buttonFont);
        sairButton.setForeground(textColor);
        sairButton.setBackground(buttonColor);
        sairButton.setBorder(buttonBorder);
        sairButton.setFocusPainted(false);
        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(sairButton);

        setSize(800, 620);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
}
