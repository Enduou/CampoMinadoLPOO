package menuPacote;

import javax.swing.*;
import java.awt.*;

public class CampoMinadoGUI extends JFrame {
    private JPanel painelTabuleiro;
    private JButton[][] botoes;
    private int linhas = 6; 
    private int colunas = 6; 

    public CampoMinadoGUI() {
        setTitle("Campo Minado");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        painelTabuleiro = new JPanel(new GridLayout(linhas, colunas));
        botoes = new JButton[linhas][colunas];

        inicializarTabuleiro();
        add(painelTabuleiro, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                JButton botao = new JButton();
                botao.setPreferredSize(new Dimension(50, 50)); 
                
                painelTabuleiro.add(botao);
                botoes[i][j] = botao;
            }
        }
    }

    /** Tirar comentário se for testar o tabuleiro
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CampoMinadoGUI();
            }
        });
    } */
}
