package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Palavra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PalavraView extends JDialog {
    private JPanel contentPane;
    private JButton voltarButton;
    private JButton editarButton;
    private JTextField palavraTextField;
    private JTextField fonteTextField;
    private JTextArea significadoTextArea;
    private JPanel btnsPanel;
    private JPanel infosPalavraPanel;
    private JPanel palavraPanel;
    private JPanel significadoPanel;
    private JPanel fontePanel;
    private JLabel fonteLabel;
    private JLabel significadoLabel;
    private JLabel palavraLabel;
    private Palavra palavraSelecionada;

    private String breakLineString(String strToBreakLine, int maxCharPerLine) {
        String breakedStr = "";
        char[] charArray = strToBreakLine.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if ((i + 1) % maxCharPerLine == 0) {
                breakedStr += "\n";
            }
            breakedStr += charArray[i];
        }
        return breakedStr;
    }

    public PalavraView(Palavra palavra) {
        this.palavraSelecionada = palavra;
        setContentPane(contentPane);
        setModal(true);
        pack();

        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

        this.setSize(450, 260);
        int dialogX = screenDimensions.width / 2 - getWidth() / 2;
        int dialogY = screenDimensions.height / 2 - getHeight() / 2;
        this.setLocation(dialogX, dialogY);

        palavraTextField.setText(palavraSelecionada.getPalavra());
        significadoTextArea.setText(palavraSelecionada.getSignificado());
        fonteTextField.setText(palavraSelecionada.getFonte());

        editarButton.addActionListener(e -> onEditar());

        voltarButton.addActionListener(e -> onSair());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onSair();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onSair(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onEditar() {
        if (editarButton.getText().equals("Editar")) {
            palavraTextField.setEnabled(true);
            significadoTextArea.setEnabled(true);
            fonteTextField.setEnabled(true);

            editarButton.setText("Salvar");
            voltarButton.setText("Cancelar");
        } else if (editarButton.getText().equals("Salvar")) {

        }
    }

    private void onSair() {
        if (voltarButton.getText().equals("Voltar")) {
            dispose();
        } else if(voltarButton.getText().equals("Cancelar")) {
            palavraTextField.setText(palavraSelecionada.getPalavra());
            palavraTextField.setEnabled(false);

            significadoTextArea.setText(palavraSelecionada.getSignificado());
            significadoTextArea.setEnabled(false);

            fonteTextField.setText(palavraSelecionada.getFonte());
            fonteTextField.setEnabled(false);

            editarButton.setText("Editar");
            voltarButton.setText("Voltar");
        }
    }
}
