package br.univates.luan.projeto_dicionario.views;

import javax.swing.*;
import java.awt.event.*;

public class RegistrarView extends JDialog {
    private JPanel contentPane;
    private JButton saveBtn;
    private JButton cancelBtn;
    private JPanel btnsPanel;
    private JPanel infosPalavraPanel;
    private JPanel palavraPanel;
    private JPanel significadoPanel;
    private JPanel fontePanel;
    private JLabel palavraLabel;
    private JLabel fonteLabel;
    private JLabel significadoLabel;
    private JTextField palavraTextField;
    private JTextField fonteTextField;
    private JTextArea significadoTextArea;

    public RegistrarView() {
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(saveBtn);

        saveBtn.addActionListener(e -> onSalvar());

        cancelBtn.addActionListener(e -> onCancelar());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancelar();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSalvar() {
        dispose();
    }

    private void onCancelar() {
        dispose();
    }

    public static void main(String[] args) {
        RegistrarView dialog = new RegistrarView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
