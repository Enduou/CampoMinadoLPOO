package guiPacote;

import javax.swing.*;
import javax.swing.border.Border;
import pontuacaoPacote.GerenciadorDePontuacoes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Menu extends JFrame {
 static final long serialVersionUID = 1L;

 
 public Menu() {

		
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
            			    
            				exibirPontuacoes();
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

 public void exibirPontuacoes() {
	    GerenciadorDePontuacoes gerenciador = new GerenciadorDePontuacoes();
	    List<String> pontuacoes = gerenciador.getPontuacoes();
	    
	    
	    List<Jogador> pontuacoesSeparadas = new ArrayList<>();
	    
	    
	    for (String pontuacao : pontuacoes) {
	        String[] partes = pontuacao.split(" - ");
	        if (partes.length == 2) {
	            String nomeJogador = partes[0];
	            int pontos;
	            try {
	                pontos = Integer.parseInt(partes[1]);
	                pontuacoesSeparadas.add(new Jogador(nomeJogador, pontos));
	            } catch (NumberFormatException e) {
	                System.err.println("Erro ao converter a pontuação para inteiro: " + partes[1]);
	            }
	        }
	    }
	    
	    
	    pontuacoesSeparadas.sort((p1, p2) -> Integer.compare(p2.getPontos(), p1.getPontos()));
	    
	    
	    StringBuilder pontuacoesStr = new StringBuilder();
	    for (Jogador pontuacaoJogador : pontuacoesSeparadas) {
	        System.out.println(pontuacaoJogador.getNomeJogador());
	        System.out.println(pontuacaoJogador.getPontos());
	    	pontuacoesStr.append(pontuacaoJogador.getNomeJogador()).append(" - ").append(pontuacaoJogador.getPontos()).append("\n");
	    }
	    
	    JOptionPane.showMessageDialog(null, pontuacoesStr.toString(), "Pontuações - 10 Melhores", JOptionPane.INFORMATION_MESSAGE);
	}
 
}
