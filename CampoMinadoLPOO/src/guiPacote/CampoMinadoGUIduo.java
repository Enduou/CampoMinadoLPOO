package guiPacote;

import javax.swing.*;
import javax.swing.border.Border;

import celulaPacote.Campo;
import celulaPacote.CampoMedio;
import celulaPacote.CelulaBomba;
import celulaPacote.CelulaVazia;
import celulaPacote.CelulaVizinha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

public class CampoMinadoGUIduo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel painelTabuleiro;
	private JButton[][] botoes;
	private Campo campo;
	private int bombasFlag = 0;
	private int bombas;
	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorAtual;
	private JLabel jogadorAtualLabel;
	private int pontuacaoAtual1 = 0;
	private int pontuacaoAtual2 = 0;
	private JLabel pontuacaoLabel1;


	public ImageIcon redimensionarIcone(String caminhoImagem, int largura, int altura) {
	    ImageIcon iconeOriginal = new ImageIcon(caminhoImagem);
	    Image imagem = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
	    return new ImageIcon(imagem);
	}
	
	public CampoMinadoGUIduo(Campo campo) {
		
		this.campo = campo;
		
		String nomeJogador1 = JOptionPane.showInputDialog(this, "Nome do jogador 1:", "Registro dos Jogadores", JOptionPane.PLAIN_MESSAGE);
	    String nomeJogador2 = JOptionPane.showInputDialog(this, "Nome do jogador 2:", "Registro dos Jogadores", JOptionPane.PLAIN_MESSAGE);
	    
	    jogador1 = new Jogador(nomeJogador1, 0); 
	    jogador2 = new Jogador(nomeJogador2, 0);
	    jogadorAtual = jogador1;
	    
	    jogadorAtualLabel = new JLabel();
	    jogadorAtualLabel.setHorizontalAlignment(JLabel.CENTER);
	    jogadorAtualLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
	    atualizarLabelJogador(); 
	    
	    
	    add(jogadorAtualLabel, BorderLayout.NORTH);
	    
		setTitle("Campo Minado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout() );
		this.setSize(800, 800);
		this.setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		pontuacaoLabel1 = new JLabel(nomeJogador1 + " - Pontuação: 0  " + nomeJogador2 + " - Pontuação: 0");
	    pontuacaoLabel1.setHorizontalAlignment(JLabel.CENTER);
	    pontuacaoLabel1.setFont(new Font("SansSerif", Font.BOLD, 18));
	    add(pontuacaoLabel1, BorderLayout.NORTH);
	    
	    

		painelTabuleiro = new JPanel(new GridLayout(campo.getLinha(), campo.getColuna()));
	    botoes = new JButton[campo.getLinha()][campo.getColuna()];

	    inicializarTabuleiro();
	    add(painelTabuleiro, BorderLayout.CENTER);
		pack();
		setVisible(true);

	}
	
	
	
	private void inicializarTabuleiro() {
		
		ImageIcon iconeFlag = redimensionarIcone("images/flag.png", 20, 20);
		campo.iniciarJogo();
		for (int i = 0; i < campo.getLinha(); i++) {
			for (int j = 0; j < campo.getColuna(); j++) {
				JButton botao = new JButton();
				botao.setPreferredSize(new Dimension(50, 50));

				painelTabuleiro.add(botao);
				botoes[i][j] = botao;
				
				bombas = campo.getBombas();
				
				botao.setBackground(new Color(245,255,250));


				final int linhaGui = i;
				final int colunaGui = j;

				botao.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton botaoClicado = (JButton) e.getSource();
						int linhaGuiClicada = -1, colunaGuiClicada = -1;

						for (int linha = 0; linha < campo.getLinha(); linha++) {
							for (int coluna = 0; coluna < campo.getColuna(); coluna++) {
								if (botoes[linha][coluna] == botaoClicado) {
									linhaGuiClicada = linha;
									colunaGuiClicada = coluna;
									break;
								}
							}
							if (linhaGuiClicada != -1) {
								break;
							}
						}

						if (campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].getRevelado()) {
							return;

						}
						 if (!campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].getRevelado()) {
						        if (jogadorAtual == jogador1) pontuacaoAtual1++;
						        else pontuacaoAtual2++;
						        atualizarPontuacoes();
						    }
						
						if ((campo.getMatriz()[linhaGuiClicada][colunaGuiClicada] instanceof CelulaBomba)
								&& (!campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].getFlag())) {
							campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].revelar();
							botaoClicado.setLayout(new BorderLayout());
							

							atualizarBotoes();
							jogoPerdido();

						} else if ((campo.getMatriz()[linhaGuiClicada][colunaGuiClicada] instanceof CelulaVazia)
								&& (!campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].getFlag())) {
							campo.explodir(linhaGuiClicada, colunaGuiClicada);
							atualizarBotoes();
						} else if ((campo.getMatriz()[linhaGuiClicada][colunaGuiClicada] instanceof CelulaVizinha)
								&& (!campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].getFlag())) {
							int bombasAoRedor = campo.calcularBombas(linhaGuiClicada, colunaGuiClicada);
							botaoClicado.setText(Integer.toString(bombasAoRedor));
							campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].revelar();
							atualizarBotoes();
						}
						alternarJogador();
					}
				});

				botoes[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						if (e.getButton() == MouseEvent.BUTTON3) {
							if (!campo.getMatriz()[linhaGui][colunaGui].getRevelado()) {
								if (!campo.getMatriz()[linhaGui][colunaGui].getFlag()) {
									campo.getMatriz()[linhaGui][colunaGui].setFlag(true);
									campo.getMatriz()[linhaGui][colunaGui].revelar();
									if ((campo.getMatriz()[linhaGui][colunaGui] instanceof CelulaBomba)
											&& (campo.getMatriz()[linhaGui][colunaGui].getFlag())) {
										bombasFlag++;
									}
									
									botoes[linhaGui][colunaGui].setIcon(iconeFlag);
									if ((bombasFlag == bombas) && flagsCorretas()) {
										jogoVencido();
									}
								}
								else {
									campo.getMatriz()[linhaGui][colunaGui].setFlag(false);
									campo.getMatriz()[linhaGui][colunaGui].setRevelado(false);
									
									botoes[linhaGui][colunaGui].setText("");
									botoes[linhaGui][colunaGui].setIcon(null);
									botoes[linhaGui][colunaGui].setLayout(new BorderLayout());
								}
							}
							else if(campo.getMatriz()[linhaGui][colunaGui].getRevelado() && campo.getMatriz()[linhaGui][colunaGui].getFlag()) {
								campo.getMatriz()[linhaGui][colunaGui].setFlag(false);
								campo.getMatriz()[linhaGui][colunaGui].setRevelado(false);
								
								botoes[linhaGui][colunaGui].setText("");
								botoes[linhaGui][colunaGui].setIcon(null);
								botoes[linhaGui][colunaGui].setLayout(new BorderLayout());
							}
						}
					}
					
				});
			}
		}
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		System.out.println(campo.getBombas());
		System.out.println(bombasFlag);
		System.out.println(flagsCorretas());
		
		
	}
	

	private boolean flagsCorretas() {
		for (int i = 0; i < campo.getLinha(); i++) {
			for (int j = 0; j < campo.getColuna(); j++) {
				if (campo.getMatriz()[i][j] instanceof CelulaBomba) {
					if (!campo.getMatriz()[i][j].getFlag()) {
						return false;
					}
				} else {
					if (campo.getMatriz()[i][j].getFlag()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void atualizarBotoes() {
		
		ImageIcon iconeBomba = redimensionarIcone("images/bomba.png", 20, 20);
		for (int i = 0; i < botoes.length; i++) {
			for (int j = 0; j < botoes[i].length; j++) {
				if (campo.getMatriz()[i][j].getRevelado()) {
					if (campo.getMatriz()[i][j] instanceof CelulaBomba && !campo.getMatriz()[i][j].getFlag()) {
						botoes[i][j].setIcon(iconeBomba);
					} else if (campo.getMatriz()[i][j] instanceof CelulaVazia && !campo.getMatriz()[i][j].getFlag()) {
						botoes[i][j].setText("");
					} else if (campo.getMatriz()[i][j] instanceof CelulaVizinha && !campo.getMatriz()[i][j].getFlag()) {
						int bombasAoRedor = campo.calcularBombas(i, j);
						botoes[i][j].setText(Integer.toString(bombasAoRedor));
					}
					botoes[i][j].setBackground(new Color(240,255,233));
				} else {
					botoes[i][j].setBackground(new Color(240,255,255));
				}
			}
		}

	}
	
	private void alternarJogador() {
	    jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
	    JOptionPane.showMessageDialog(this, "Agora é a vez do jogador: " + jogadorAtual.getNomeJogador(), "Vez do Jogador", JOptionPane.INFORMATION_MESSAGE);
	    atualizarLabelJogador();
	}

	
	private void atualizarLabelJogador() {
	    jogadorAtualLabel.setText("Vez do jogador: " + jogadorAtual.getNomeJogador());
	    
	}
	
	private void atualizarPontuacoes() {
	    pontuacaoLabel1.setText(jogador1.getNomeJogador() + " - Pontuação: " + pontuacaoAtual1 + " | " + jogador2.getNomeJogador() + " - Pontuação: " + pontuacaoAtual2);
	}


	private void jogoVencido() {
	    JDialog gameWinDialog = new JDialog(this, "Parabens, Voce Venceu!", true);
	    gameWinDialog.setSize(300, 150);
	    gameWinDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    gameWinDialog.setLayout(new BorderLayout());

	    JLabel parabensLabel = new JLabel("Parabens! Voce venceu o jogo!", SwingConstants.CENTER);
	    gameWinDialog.add(parabensLabel, BorderLayout.CENTER);

	    Timer timer = new Timer(4000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            dispose();
	          
	            new Menu(); 
	            
	        }
	    });

	    timer.setRepeats(false); 
	    timer.start();           
	    

	    gameWinDialog.setLocationRelativeTo(this);
	    gameWinDialog.setVisible(true);
	}

	
	private void jogoPerdido() {
	    JDialog gameLostDialog = new JDialog(this, "Game Over", true);
	    gameLostDialog.setSize(300, 150);
	    gameLostDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    gameLostDialog.setLayout(new BorderLayout());

	    JLabel gameOverLabel = new JLabel("Game Over! Você perdeu o jogo.", SwingConstants.CENTER);
	    gameLostDialog.add(gameOverLabel, BorderLayout.CENTER);

	    Timer timer = new Timer(4000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            dispose(); 
	            new Menu();
	        }
	    });

	    timer.setRepeats(false);
	    timer.start();           

	    gameLostDialog.setLocationRelativeTo(this);
	    gameLostDialog.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		

	}
}
