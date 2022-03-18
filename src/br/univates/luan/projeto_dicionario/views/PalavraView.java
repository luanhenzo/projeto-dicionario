package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Palavra;

import javax.swing.*;

public class PalavraView extends JDialog {
    private JPanel contentPane;
    private JButton voltarButton;
    private JButton editarButton;
    private JTextField palavraTextField;
    private JTextField textField2;
    private JTextArea significadoTextArea;
    private JPanel btnsPanel;
    private JPanel infosPalavraPanel;
    private JPanel palavraPanel;
    private JPanel significadoPanel;
    private JPanel fontePanel;
    private JLabel fonteLabel;
    private JLabel significadoLabel;
    private JLabel palavraLabel;

    public PalavraView(Palavra palavra) {
        setContentPane(contentPane);
        setModal(true);
    }
}
