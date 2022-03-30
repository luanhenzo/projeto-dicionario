package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Dicionario;
import br.univates.luan.projeto_dicionario.entities.Palavra;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    private AppView runningApp;

    public RegistrarView(AppView appView) {
        this.runningApp = appView;
        setContentPane(contentPane);
        setModal(true);
        pack();

        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

        this.setSize(450, 260);
        int dialogX = screenDimensions.width / 2 - getWidth() / 2;
        int dialogY = screenDimensions.height / 2 - getHeight() / 2;
        this.setLocation(dialogX, dialogY);

        saveBtn.addActionListener(e -> onSalvar());

        cancelBtn.addActionListener(e -> onCancelar());

        palavraTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }
        });
        significadoTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }
        });
        fonteTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boolean todosInputsValidos = validaInputs();
                saveBtn.setEnabled(todosInputsValidos);
            }
        });

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

    private boolean validaInputs() {
        boolean allInputsValid = false;
        String palavra = palavraTextField.getText();
        String significado = significadoTextArea.getText();
        String fonte = fonteTextField.getText();
        if (!palavra.isBlank() && !significado.isBlank() && !fonte.isBlank()) {
            boolean isPalavraString;
            boolean isSignificadoString;
            boolean isFonteString;

            try {
                Double.parseDouble(palavra);
                isPalavraString = false;
            } catch (NumberFormatException e) {
                isPalavraString = true;
            }
            try {
                Double.parseDouble(significado);
                isSignificadoString = false;
            } catch (NumberFormatException e) {
                isSignificadoString = true;
            }
            try {
                Double.parseDouble(fonte);
                isFonteString = false;
            } catch (NumberFormatException e) {
                isFonteString = true;
            }

            if (isPalavraString && isSignificadoString && isFonteString) {
                allInputsValid = true;
            }

        } else {
            allInputsValid = false;
        }
        return allInputsValid;
    }

    private void onSalvar() {
        String palavra = palavraTextField.getText();
        String significado = significadoTextArea.getText();
        String fonte = fonteTextField.getText();

        Dicionario dicionario = runningApp.getDicionario();

        Palavra novaPalavra = new Palavra(palavra, significado, fonte);
        dicionario.addPalavra(novaPalavra);

        JOptionPane.showMessageDialog(this, "Palavra \"" + novaPalavra.getPalavra() + "\" adicionada com sucesso.");
        runningApp.updateListModel();
        dispose();
    }

    private void onCancelar() {
        System.out.println("SAINDO!");
        dispose();
    }
}
