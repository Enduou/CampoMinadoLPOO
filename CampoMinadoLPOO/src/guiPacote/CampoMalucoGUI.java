package guiPacote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import celulaPacote.CampoMaluco;
import celulaPacote.Celula;
import celulaPacote.CelulaBomba;
import celulaPacote.CelulaVazia;
import celulaPacote.CelulaVizinha;

public class CampoMalucoGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private CampoMaluco campo;
	private JButton[][] botao;
	private int bombasFlag = 0;
	private int bombas;
	
	
	public ImageIcon redimensionarIcone(String caminhoImagem, int largura, int altura) {
	    ImageIcon iconeOriginal = new ImageIcon(caminhoImagem);
	    Image imagem = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
	    return new ImageIcon(imagem);
	}
	
	public CampoMalucoGUI(CampoMaluco campo) {
		this.campo = campo;
		
//		metodo que inicia o back
		campo.iniciarJogo();
		
		setTitle("Campo Minado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.setSize(800, 800);
		this.setResizable(false);

		this.setLayout(new GridLayout(campo.getLinha(), campo.getColuna()));
		
		botao = new JButton[campo.getLinha()][campo.getColuna()];
		
		bombas = campo.getBombas();
		
	
		
		for (int i = 0; i < campo.getLinha(); i++) {
			for (int j = 0; j < campo.getColuna(); j++) {
				botao[i][j] = new JButton();
				botao[i][j].setFocusable(false);
				botao[i][j].setFont(new Font("Arial", Font.BOLD, 20));
				botao[i][j].setBackground(new Color(0, 0, 0)); 
				botao[i][j].setForeground(new Color(0, 255, 0)); 
				botao[i][j].setBorder(BorderFactory.createLineBorder(new Color(0, 128, 0), 3));

				
				
				this.add(botao[i][j]);
				
				final int linhaGui = i;
				final int colunaGui = j;
				
				
				botao[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						JButton botaoClicado = (JButton) e.getSource();
						int linhaClicada = -1, colunaClicada = -1;
						
						for (int linhaGui = 0; linhaGui < campo.getLinha(); linhaGui++) {
							for (int colunaGui = 0; colunaGui < campo.getColuna(); colunaGui++) {
								if(botao[linhaGui][colunaGui] == botaoClicado) {
									
									linhaClicada = linhaGui;
									colunaClicada = colunaGui;
									break;
								}
								
							}
							if(linhaClicada != -1) {
								break;
							}
						}
						if ((campo.getMatriz()[linhaClicada][colunaClicada] instanceof CelulaBomba) && (!campo.getMatriz()[linhaClicada][colunaClicada].getFlag())) {
							campo.getMatriz()[linhaClicada][colunaClicada].revelar();
							
						
							
							botaoClicado.setLayout(new BorderLayout());
							
							
							atualizarBotoes();
							
							jogoPerdido();
							
						}
						else if((campo.getMatriz()[linhaClicada][colunaClicada] instanceof CelulaVazia) && (!campo.getMatriz()[linhaClicada][colunaClicada].getFlag())) {
							campo.explodir(linhaClicada, colunaClicada);
							atualizarBotoes();
						}
						else if((campo.getMatriz()[linhaClicada][colunaClicada] instanceof CelulaVizinha) && (!campo.getMatriz()[linhaClicada][colunaClicada].getFlag())) {
							
							int bombasAoRedor = campo.calcularBombas(linhaClicada,colunaClicada);
							botaoClicado.setText(Integer.toString(bombasAoRedor));
							campo.getMatriz()[linhaClicada][colunaClicada].revelar();
							atualizarBotoes();
							
						}
					
					}
					
					
				});
				
				
				botao[i][j].addMouseListener((MouseListener) new MouseAdapter() {
					 @Override
					 public void mouseClicked(MouseEvent e) {
	                        //verificando se  um clique com o botao direito
	                        if (e.getButton() == MouseEvent.BUTTON3) {
	                            if (!campo.getMatriz()[linhaGui][colunaGui].getRevelado()) {
	                                if (!campo.getMatriz()[linhaGui][colunaGui].getFlag()) {
	                                	//verificando se a c�lula � maluca para fazer a l�gica
	                                    if(campo.getMatriz()[linhaGui][colunaGui].getCelulaMaluca()) {
	                                        campo.getMatriz()[linhaGui][colunaGui].setFlag(true);
	                                        campo.getMatriz()[linhaGui][colunaGui].revelar();
	                                        botao[linhaGui][colunaGui].setText("FLAG");
	                                        
	                                        campo.identificadorCelulaMaluca(linhaGui, colunaGui);
	                                        campo.getMatriz()[linhaGui][colunaGui].revelar();
	                                        atualizarBotoes();

	                                        if (campo.getMatriz()[linhaGui][colunaGui] instanceof CelulaBomba) {
	                                            bombasFlag++;
	                                        }
	                                        
	                                        
	                                        if (flagsCorretas()) {
	                                        	jogoVencido();
	                                        }
	                                    }
	                                    else {
	                                        campo.getMatriz()[linhaGui][colunaGui].setFlag(true);
	                                        campo.getMatriz()[linhaGui][colunaGui].revelar();


	                                        botao[linhaGui][colunaGui].setText("FLAG");

	                                        atualizarBotoes();

	                                        if (campo.getMatriz()[linhaGui][colunaGui] instanceof CelulaBomba) {
	                                            bombasFlag++;
	                                        }

	                                        if (flagsCorretas()) {
	                                            jogoVencido();
	                                        }
	                                    }
	                                } 
	                                else {
	                                    campo.getMatriz()[linhaGui][colunaGui].setFlag(false);
	                                    campo.getMatriz()[linhaGui][colunaGui].setRevelado(false);

	                                    botao[linhaGui][colunaGui].setText("");
	                                    botao[linhaGui][colunaGui].setIcon(null);
	                                    botao[linhaGui][colunaGui].setLayout(new BorderLayout());

	                                    if (campo.getMatriz()[linhaGui][colunaGui] instanceof CelulaBomba) {
	                                        bombasFlag--;
	                                    }
	                                }
	                            } 
	                            else if (campo.getMatriz()[linhaGui][colunaGui].getRevelado() && campo.getMatriz()[linhaGui][colunaGui].getFlag()) {
	                                campo.getMatriz()[linhaGui][colunaGui].setFlag(false);
	                                campo.getMatriz()[linhaGui][colunaGui].setRevelado(false);

	                                botao[linhaGui][colunaGui].setText("");
	                                botao[linhaGui][colunaGui].setIcon(null);
	                                botao[linhaGui][colunaGui].setLayout(new BorderLayout());

	                                if (campo.getMatriz()[linhaGui][colunaGui] instanceof CelulaBomba) {
	                                    bombasFlag--;
	                                }
	                                if (flagsCorretas()) {
	                                	jogoVencido();
	                                }
	                            }
	                        }
	                        atualizarBotoes();
	                    }

				
	                });
				
			}
			
		}
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
	}
	
	

// atualiza o campo apos a abertura de celulas
	public void atualizarBotoes() {
	    ImageIcon iconeBomba = redimensionarIcone("images/bomba.png", 20, 20);
	    ImageIcon iconeFlag = redimensionarIcone("images/flag.png", 20, 20);
	    
	    for (int i = 0; i < botao.length; i++) {
	        for (int j = 0; j < botao[i].length; j++) {
	            // Redimensione de acordo com o tamanho do seu botao
	            botao[i][j].setIcon(null); // Limpa o icone anterior
	            if (campo.getMatriz()[i][j].getRevelado()) {
	                if (campo.getMatriz()[i][j] instanceof CelulaBomba && !campo.getMatriz()[i][j].getFlag()) {
	                    botao[i][j].setIcon(iconeBomba);
	                } else if (campo.getMatriz()[i][j] instanceof CelulaVazia && !campo.getMatriz()[i][j].getFlag()) {
	                    botao[i][j].setText("_"); // Celulas vazias nao mostram texto ou iccone
	                } else if (campo.getMatriz()[i][j] instanceof CelulaVizinha && !campo.getMatriz()[i][j].getFlag()) {
	                    int bombasAoRedor = campo.calcularBombas(i, j);
	                    if(bombasAoRedor == 0) {
	                    	botao[i][j].setText("_");
	                    }else {
	                    	botao[i][j].setText(Integer.toString(bombasAoRedor)); // Mostra o numero de bombas ao redor
	                    }
	                 
	                }
	            } else {
	                if (campo.getMatriz()[i][j].getFlag()) {
	                    botao[i][j].setIcon(iconeFlag); // Aplica o ide flag
	                } else {
	                    botao[i][j].setText("");
	                }
	            } 	
	        }
	    }
	}

// contabiliza as flags que o usuario marcou no local correto
	
	private boolean flagsCorretas() {
	    int contadorBombasMarcadas = 0;
	    int contadorFlagsIncorretas = 0;
	    
	    for (int i = 0; i < campo.getLinha(); i++) {
	        for (int j = 0; j < campo.getColuna(); j++) {
	            Celula celulaAtual = campo.getMatriz()[i][j];
	            if (celulaAtual instanceof CelulaBomba && celulaAtual.getFlag()) {
	                contadorBombasMarcadas++;
	            } else if (!(celulaAtual instanceof CelulaBomba) && celulaAtual.getFlag()) {
	                
	                contadorFlagsIncorretas++;
	            }
	        }
	    }
	    
	    
	    return contadorBombasMarcadas == bombas && contadorFlagsIncorretas == 0;
	}
//	metodo para informar que o usuario venceu
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

//	metodo para informar que o usuario perdeu
	private void jogoPerdido() {
		

	    for (int i = 0; i < campo.getLinha(); i++) {
	        for (int j = 0; j < campo.getColuna(); j++) {
	            if (campo.getMatriz()[i][j] instanceof CelulaBomba) {
	               
	                botao[i][j].setIcon(redimensionarIcone("images/bomba.png", 20, 20));
	                campo.getMatriz()[i][j].revelar(); 
	            }
	        }
	    }
	    JDialog gameLostDialog = new JDialog(this, "Game Over", true);
	    gameLostDialog.setSize(300, 150);
	    gameLostDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    gameLostDialog.setLayout(new BorderLayout());

	    JLabel gameOverLabel = new JLabel("Game Over! Voc� perdeu o jogo.", SwingConstants.CENTER);
	    gameLostDialog.add(gameOverLabel, BorderLayout.CENTER);

	    Timer timer = new Timer(2000, new ActionListener() {
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

//	metodo para observar o funcionamento do tabuleiro no console
		public void printcampoState() {
		    System.out.println("Current campo:");
		    for (int i = 0; i < campo.getLinha(); i++) {
		        for (int j = 0; j < campo.getColuna(); j++) {
		            if (campo.getMatriz()[i][j] instanceof CelulaBomba) {
		                if (campo.getMatriz()[i][j].getCelulaMaluca()) {
		                    System.out.print("f ");
		                } else {
		                    System.out.print("X ");
		                }
		            } else if (campo.getMatriz()[i][j] instanceof CelulaVazia) {
		                System.out.print("- ");
		            } else if (campo.getMatriz()[i][j] instanceof CelulaVizinha) {
		                System.out.print("- ");
		            }
		        }
		        System.out.println();
		    }
		    System.out.println(bombasFlag);
		}
	
		public int contadorBombas() {
		    int n = 0;
		    System.out.println("");
		    for (int i = 0; i < campo.getLinha(); i++) {
		        for (int j = 0; j < campo.getColuna(); j++) {
		            if (campo.getMatriz()[i][j].getCelulaMaluca()) {
		                n++  ; 
		               
		            }
		        }
		    }
		    return n;
		}
		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	 

}