package menuPacote;

import javax.swing.*;
import javax.swing.border.Border;

import celulaPacote.Campo;
import celulaPacote.CampoMaluco;
import celulaPacote.Jogador;
import celulaPacote.i;
import celulaPacote.iCampo;
import excessaoPacote.AtributoException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Menu extends JFrame {
    public Menu() {
        
    	setTitle("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK); 
        setLayout(new GridLayout(4, 1, 10, 10)); 
        this.setSize(500,500);
        this.setResizable(false);

        
       
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = Color.BLACK;
        Color textColor = Color.GREEN;
        Border buttonBorder = BorderFactory.createLineBorder(textColor, 2);

        
        JButton novoJogoButton = new JButton("Novo Jogo");
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
        				;
        			}
        		});
            	
            
            }   
        });
        add(novoJogoButton);
        
        JButton doisJogadoresButton = new JButton("Dois Jogadores");
        doisJogadoresButton.setFont(buttonFont);
        doisJogadoresButton.setForeground(textColor);
        doisJogadoresButton.setBackground(buttonColor);
        doisJogadoresButton.setBorder(buttonBorder);
        doisJogadoresButton.setFocusPainted(false);
        doisJogadoresButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	Scanner scanner = new Scanner(System.in);
                    System.out.println("Primeiro Jogador: ");
                    String nomeJogador1 = scanner.next();
                    System.out.println("Segundo Jogador: ");
                    String nomeJogador2 = scanner.next();
                    
                    // Criação de Jogadores
                    Jogador[] jogadores = new Jogador[2];
                    jogadores[0] = new Jogador(nomeJogador1, 0);
                    jogadores[1] = new Jogador(nomeJogador2, 0);
                    
                    iCampo c;
                    c = new Campo(i.linha, i.coluna, i.bombas);
                    
                    c.iniciarJogo(); // Inicia o jogo
                    
                    System.out.println("Campo Inicial:");
                    System.out.println(c);

                    int jogadorVez = 0;
                    boolean jogoEmAndamento = true;

                    while (jogoEmAndamento) {
                        System.out.println(jogadores[jogadorVez].getNome() + ", sua vez");
                        System.out.println();
                        System.out.println("Digite a linha:");
                        int linha = scanner.nextInt();
                        System.out.println("Digite a coluna:");
                        int coluna = scanner.nextInt();
                        System.out.println("Para Revelar a Casa, (DIGITE 0) Para adicionar bandeira, (DIGITE 1)");
                        int escolha = scanner.nextInt();
                        System.out.println();

                        try {
                            c.selecaoUsuario(linha, coluna, escolha);
                        } catch (AtributoException err) {
                            err.printStackTrace();
                        }
                        System.out.println(c);

                        jogadorVez = (jogadorVez + 1) % 2;
                    }

                    scanner.close();
                }
            	
        });    
        add(doisJogadoresButton);
       
        
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
        	            
        	        
        	            new CampoMalucoGUI(new CampoMaluco(8,8,5,6));
        	        }
        	    });
            	
            }
        });
        add(minadoMalucoButton);

        
        JButton pontuacaoButton = new JButton("Pontuação");
        pontuacaoButton.setFont(buttonFont);
        pontuacaoButton.setForeground(textColor);
        pontuacaoButton.setBackground(buttonColor);
        pontuacaoButton.setBorder(buttonBorder);
        pontuacaoButton.setFocusPainted(false);
        pontuacaoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("*Paulo: 3 \n *Eduardo: 2");
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

        setSize(300, 200); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
    

}
