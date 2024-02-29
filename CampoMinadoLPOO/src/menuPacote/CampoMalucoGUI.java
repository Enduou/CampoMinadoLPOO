package menuPacote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import celulaPacote.CampoMaluco;
import celulaPacote.CampoMedio;
import celulaPacote.CelulaBomba;
import celulaPacote.CelulaVazia;
import celulaPacote.CelulaVizinha;

public class CampoMalucoGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private CampoMaluco campo;
	private JButton[][] botao;
	private int bombasFlag = 0;
	private int bombas;
	
	public CampoMalucoGUI(CampoMaluco campo) {
		this.campo = campo;
		
		campo.iniciarJogo();
		
		setTitle("Campo Minado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.setSize(800, 800);
		this.setResizable(false);

		this.setLayout(new GridLayout(campo.getLinha(), campo.getColuna()));
		botao = new JButton[campo.getLinha()][campo.getColuna()];
		
		for (int i = 0; i < campo.getLinha(); i++) {
			for (int j = 0; j < campo.getColuna(); j++) {
				botao[i][j] = new JButton();
				botao[i][j].setFocusable(false);
				botao[i][j].setFont(new Font("Arial", Font.BOLD, 18));
				botao[i][j].setBackground(new Color(0, 0, 3));
				botao[i][j].setForeground(new Color(0, 0, 128));
				botao[i][j].setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 3));
				botao[i][j].setLayout(new BorderLayout());
				
				
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
							
							//colocar imagem da bomba
							
							botaoClicado.setLayout(new BorderLayout());
							//botaoClicado.add(new JLabel(bombIcon),BorderLayout.CENTER); (*preciso fazer funcionar*)
							
							atualizarBotoes();
							
							//faltando metodo do game over
							
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
	                        //verificando se é um clique com o botão direito
	                        if (e.getButton() == MouseEvent.BUTTON3) {
	                            if (!campo.getMatriz()[linhaGui][colunaGui].getRevelado()) {
	                                if (!campo.getMatriz()[linhaGui][colunaGui].getFlag()) {
	                                	//verificando se a célula é maluca para fazer a lógica
	                                    if(campo.getMatriz()[linhaGui][colunaGui].getCelulaMaluca()) {
	                                        campo.getMatriz()[linhaGui][colunaGui].setFlag(true);
	                                        campo.getMatriz()[linhaGui][colunaGui].revelar();
//
//	                                        ImageIcon flagIcon = new ImageIcon(flagImage.getImage());
//	                                        Image scaledImage = flagIcon.getImage().getScaledInstance(botao[linhaGui][colunaGui].getWidth(), botao[linhaGui][colunaGui].getHeight(), Image.SCALE_SMOOTH);
//	                                        flagIcon = new ImageIcon(scaledImage);
//	                                        botao[linhaGui][colunaGui].setIcon(flagIcon);

	                                        campo.identificadorCelulaMaluca(linhaGui, colunaGui);
	                                        campo.getMatriz()[linhaGui][colunaGui].revelar();
	                                        atualizarBotoes();

	                                        if (campo.getMatriz()[linhaGui][colunaGui] instanceof CelulaBomba) {
	                                            bombasFlag--;
	                                        }
	                                        
	                                        //exibindo a vitória em caso de retornar true
	                                        if (flagsCorretas()) {
//	                                            gameWin(currentPlayer);
	                                        }
	                                    }
	                                    else {
	                                        campo.getMatriz()[linhaGui][colunaGui].setFlag(true);
	                                        campo.getMatriz()[linhaGui][colunaGui].revelar();

//	                                        ImageIcon flagIcon = new ImageIcon(flagImage.getImage());
//	                                        Image scaledImage = flagIcon.getImage().getScaledInstance(botao[linhaGui][colunaGui].getWidth(), botao[linhaGui][colunaGui].getHeight(), Image.SCALE_SMOOTH);
//	                                        flagIcon = new ImageIcon(scaledImage);
//	                                        botao[linhaGui][colunaGui].setIcon(flagIcon);

	                                        atualizarBotoes();

	                                        if (campo.getMatriz()[linhaGui][colunaGui] instanceof CelulaBomba) {
	                                            bombasFlag++;
	                                        }

	                                        if (flagsCorretas()) {
//	                                            gameWin(currentPlayer);
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
//	                                    gameWin(currentPlayer);
	                                }
	                            }
	                        }
	                        atualizarBotoes();
	                    }

	                    @Override
	                    public void mouseEntered(MouseEvent e) {
	                        //hover on
	                        if (!campo.getMatriz()[linhaGui][colunaGui].getRevelado()) {
	                            botao[linhaGui][colunaGui].setBackground(new Color(0, 0, 90));
	                        } 
	                        else {
	                            botao[linhaGui][colunaGui].setBackground(new Color(0, 0, 3));
	                        }
	                    }

	                    @Override
	                    public void mouseExited(MouseEvent e) {
	                        //hover off
	                        if (!campo.getMatriz()[linhaGui][colunaGui].getRevelado()) {
	                            botao[linhaGui][colunaGui].setBackground(new Color(0, 0, 40));
	                        } 
	                        else {
	                            botao[linhaGui][colunaGui].setBackground(new Color(0, 0, 3));
	                        }
	                    }
	                });
				
			}
			
		}
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
		
	}

	public void atualizarBotoes() {
		for (int i = 0; i < botao.length; i++) {
			for (int j = 0; j < botao[i].length; j++) {
				if (campo.getMatriz()[i][j].getRevelado()) {
					if (campo.getMatriz()[i][j] instanceof CelulaBomba && !campo.getMatriz()[i][j].getFlag()) {
						botao[i][j].setText("X");
					} else if (campo.getMatriz()[i][j] instanceof CelulaVazia && !campo.getMatriz()[i][j].getFlag()) {
						botao[i][j].setText("");
					} else if (campo.getMatriz()[i][j] instanceof CelulaVizinha && !campo.getMatriz()[i][j].getFlag()) {
						int bombasAoRedor = campo.calcularBombas(i, j);
						botao[i][j].setText(Integer.toString(bombasAoRedor));
					}
					// escurecendo botão se tiver selecionado
					botao[i][j].setBackground(new Color(0, 0, 3));
				} else {
					// clareando botão se não tiver selecionado
					botao[i][j].setBackground(new Color(0, 0, 40));
				}
			}

		}

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

	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            
	        
	            new CampoMalucoGUI(new CampoMaluco(8,8,5,6));
	        }
	    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
