package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Palavra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.*;

public class PesquisarView extends JDialog {
    private JPanel contentPane;
    private JPanel infosPalavraPanel;
    private JPanel btnsPanel;
    private JPanel palavraPanel;
    private JPanel significadoPanel;
    private JPanel fontePanel;
    private JTextField palavraTextField;
    private JTextArea significadoTextArea;
    private JTextField fonteTextField;
    private JLabel palavraLabel;
    private JLabel significadoLabel;
    private JLabel fonteLabel;
    private JButton pesquisarBtn;
    private JButton voltarBtn;
    private JButton limparBtn;

    private AppView runningApp;

    public PesquisarView(AppView appView) {
        this.runningApp = appView;
        setContentPane(contentPane);
        setModal(true);
        pack();

        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

        int dialogX = screenDimensions.width / 2 - getWidth() / 2;
        int dialogY = screenDimensions.height / 2 - getHeight() / 2;
        this.setLocation(dialogX, dialogY);

        pesquisarBtn.addActionListener(e -> onPesquisar());

        limparBtn.addActionListener(e -> onLimpar());

        voltarBtn.addActionListener(e -> onVoltar());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onVoltar();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onVoltar();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onPesquisar() {
        ArrayList<Palavra> palavrasParaListar = new ArrayList<>();
        boolean isPalavraBlank = palavraTextField.getText().isBlank();
        boolean isSignificadoBlank = significadoTextArea.getText().isBlank();
        boolean isFonteBlank = fonteTextField.getText().isBlank();

        Pattern patternPalavra = Pattern.compile(palavraTextField.getText(), Pattern.CASE_INSENSITIVE);
        Pattern patternSignificado = Pattern.compile(significadoTextArea.getText(), Pattern.CASE_INSENSITIVE);
        Pattern patternFonte = Pattern.compile(fonteTextField.getText(), Pattern.CASE_INSENSITIVE);

        if (!isPalavraBlank || !isSignificadoBlank || !isFonteBlank) {

            for (Palavra p : runningApp.getDicionario().getPalavras()) {
                boolean confirmaPalavra;
                boolean confirmaSignificado;
                boolean confirmaFonte;

                if (!isPalavraBlank) {
                    Matcher verificacao = patternPalavra.matcher(p.getPalavra());
                    confirmaPalavra = verificacao.find();
                } else {
                    confirmaPalavra = true;
                }

                if (!isSignificadoBlank) {
                    Matcher verificacao = patternSignificado.matcher(p.getSignificado());
                    confirmaSignificado = verificacao.find();
                } else {
                    confirmaSignificado = true;
                }

                if (!isFonteBlank) {
                    Matcher verificacao = patternFonte.matcher(p.getFonte());
                    confirmaFonte = verificacao.find();
                } else {
                    confirmaFonte = true;
                }

                if (confirmaPalavra && confirmaSignificado && confirmaFonte) {
                    palavrasParaListar.add(p);
                }
            }
            if (palavrasParaListar.size() > 0) {
                runningApp.updateListModel(palavrasParaListar);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma palavra com essas informações foi encontrada.");
            }
        } else {
            runningApp.updateListModel();
            dispose();
        }
    }

    private void onLimpar() {
        palavraTextField.setText("");
        significadoTextArea.setText("");
        fonteTextField.setText("");
    }

    private void onVoltar() {
        dispose();
    }
}
