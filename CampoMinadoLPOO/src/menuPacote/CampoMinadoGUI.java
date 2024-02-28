package menuPacote;

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

public class CampoMinadoGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel painelTabuleiro;
	private JButton[][] botoes;
	private Campo campo;
	private int bombasFlag = 0;
	private int bombas;

	public CampoMinadoGUI(Campo campo) {
		
		this.campo = campo;
		setTitle("Campo Minado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.setSize(800, 800);
		this.setResizable(false);

		painelTabuleiro = new JPanel(new GridLayout(campo.getLinha(), campo.getColuna()));
		botoes = new JButton[campo.getLinha()][campo.getColuna()];

		inicializarTabuleiro();
		add(painelTabuleiro, BorderLayout.CENTER);
		pack();
		setVisible(true);

	}
	
	
	private void inicializarTabuleiro() {
		campo.iniciarJogo();
		for (int i = 0; i < campo.getLinha(); i++) {
			for (int j = 0; j < campo.getColuna(); j++) {
				JButton botao = new JButton();
				botao.setPreferredSize(new Dimension(50, 50));

				painelTabuleiro.add(botao);
				botoes[i][j] = botao;
				
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
						if ((campo.getMatriz()[linhaGuiClicada][colunaGuiClicada] instanceof CelulaBomba)
								&& (!campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].getFlag())) {
							campo.getMatriz()[linhaGuiClicada][colunaGuiClicada].revelar();
							botaoClicado.setLayout(new BorderLayout());
							// colocar imagem

							atualizarBotoes();

							// falta fazer o game over

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
						// fazer logica do score e troca de jogadores
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
									// colocar imagem da flag
									// setar configuracoes da imagem

									if ((bombasFlag == bombas) && flagsCorretas()) {
										// fazer metodo vencedor
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

					@Override
					public void mouseEntered(MouseEvent e) {
					    //hover on
					    if (!campo.getMatriz()[linhaGui][colunaGui].getRevelado()) {
					        botoes[linhaGui][colunaGui].setBackground(new Color(240,255,255));
					    } else {
					        botoes[linhaGui][colunaGui].setBackground(new Color(240,255,235));
					    }
					}

					@Override
					public void mouseExited(MouseEvent e) {
					    //hover off
					    if (!campo.getMatriz()[linhaGui][colunaGui].getRevelado()) {
					        botoes[linhaGui][colunaGui].setBackground(new Color(240,255,255));
					    } else {
					        botoes[linhaGui][colunaGui].setBackground(new Color(240,255,235));
					    }
					}
				});
			}
		}
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		/*JDialog infoDialog = new JDialog(this, "Informações do Jogador", false);
		infoDialog.setSize(482, 60);
		infoDialog.setResizable(false);
		infoDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		infoDialog.setLayout(new GridLayout(3, 1, 0, 0));
		infoDialog.getContentPane().setBackground(new Color(30, 30, 80)); 
		Border border = BorderFactory.createLineBorder(new Color(0, 150, 250), 2); 
		infoDialog.getRootPane().setBorder(border);

		// Label para exibir o nome e o score do primeiro player
		JLabel infoLabel1 = new JLabel("Jogador 1: " ); // Alterei o nome do jogador
		JLabel infoLabel1Score = new JLabel("Pontuação: " ); // Alterei o nome do método e da variável
		infoLabel1.setForeground(new Color(250, 250, 250));
		infoLabel1Score.setForeground(new Color(250, 250, 250));
		infoLabel1.setFont(new Font("Verdana", Font.BOLD, 15));
		infoLabel1Score.setFont(new Font("Verdana", Font.BOLD, 15));

		// Label para exibir o nome e o score do segundo player
		JLabel infoLabel2 = new JLabel("Jogador 2: " ); // Alterei o nome do jogador
		JLabel infoLabel2Score = new JLabel("Pontuação: " ); // Alterei o nome do método e da variável
		infoLabel2.setForeground(new Color(250, 250, 250));
		infoLabel2Score.setForeground(new Color(250, 250, 250));
		infoLabel2.setFont(new Font("Verdana", Font.BOLD, 15));
		infoLabel2Score.setFont(new Font("Verdana", Font.BOLD, 15));

		// Label para exibir a vez de quem é
		JLabel currentPlayerLabel = new JLabel("VEZ DE: " ); // Alterei o nome do jogador
		currentPlayerLabel.setForeground(new Color(250, 250, 250));
		currentPlayerLabel.setFont(new Font("Verdana", Font.BOLD, 10));

		infoDialog.add(infoLabel1);
		infoDialog.add(infoLabel1Score);
		infoDialog.add(infoLabel2);
		infoDialog.add(infoLabel2Score);
		infoDialog.add(currentPlayerLabel, BorderLayout.EAST);

		// Posicionando o JDialog abaixo da janela principal
		int mainFrameX = this.getLocation().x;
		int mainFrameY = this.getLocation().y;
		int mainFrameHeight = this.getHeight();

		int dialogX = mainFrameX;
		int dialogY = mainFrameY + mainFrameHeight;
		infoDialog.setLocation(dialogX + 9, dialogY - 6);
		

		infoDialog.setResizable(false);
		infoDialog.setUndecorated(true);
		infoDialog.setVisible(true);
		*/

	}
	

	private boolean flagsCorretas() {
		for (int i = 0; i < campo.getLinha(); i++) {
			for (int j = 0; j < campo.getColuna(); j++) {
				if (campo.getMatriz()[i][j] instanceof CelulaBomba) {
					if (campo.getMatriz()[i][j].getFlag()) {
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
		for (int i = 0; i < botoes.length; i++) {
			for (int j = 0; j < botoes[i].length; j++) {
				if (campo.getMatriz()[i][j].getRevelado()) {
					if (campo.getMatriz()[i][j] instanceof CelulaBomba && !campo.getMatriz()[i][j].getFlag()) {
						botoes[i][j].setText("X");
					} else if (campo.getMatriz()[i][j] instanceof CelulaBomba && !campo.getMatriz()[i][j].getFlag()) {
						botoes[i][j].setText("");
					} else if (campo.getMatriz()[i][j] instanceof CelulaVazia && !campo.getMatriz()[i][j].getFlag()) {
						int bombasAoRedor = campo.calcularBombas(i, j);
						botoes[i][j].setText(Integer.toString(bombasAoRedor));
					}
					botoes[i][j].setBackground(new Color(240,255,255));
				} else {
					botoes[i][j].setBackground(new Color(240,255,255));
				}
			}
		}

	}

	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            
	        
	            new CampoMinadoGUI(new CampoMedio(8,8,5));
	        }
	    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
