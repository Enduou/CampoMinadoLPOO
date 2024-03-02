package menuPacote;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
 static final long serialVersionUID = 1L;

	public Menu() {

        setTitle("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new GridLayout(0, 1, 10, 10));
        setSize(500, 500);
        setResizable(false);

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

        JButton doisJogadoresButton = new JButton("Dois Jogadores -> Maluco");
        doisJogadoresButton.setFont(buttonFont);
        doisJogadoresButton.setForeground(textColor);
        doisJogadoresButton.setBackground(buttonColor);
        doisJogadoresButton.setBorder(buttonBorder);
        doisJogadoresButton.setFocusPainted(false);
        doisJogadoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new DificuldadesMalucoDuo();
                    }
                });
            }
        });
        add(doisJogadoresButton);

        JButton doisJogadoresButton2 = new JButton("Dois Jogadores -> Normal");
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

        setSize(800, 620);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
}
