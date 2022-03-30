package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Dicionario;
import br.univates.luan.projeto_dicionario.entities.Palavra;
import br.univates.luan.projeto_dicionario.entities.Usuario;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PalavraView extends JDialog {
    private JPanel contentPane;
    private JButton voltarBtn;
    private JButton editarBtn;
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
    private JButton removerBtn;

    private AppView runningApp;
    private Palavra palavraSelecionada;

    public PalavraView(AppView appView) {
        this.runningApp = appView;
        this.palavraSelecionada = runningApp.getPalavraSelecionada();
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

        editarBtn.addActionListener(e -> onEditar());

        removerBtn.addActionListener(e -> onRemover());

        voltarBtn.addActionListener(e -> onSair());

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
        if (editarBtn.getText().equals("Editar")) {
            palavraTextField.setEnabled(true);
            significadoTextArea.setEnabled(true);
            fonteTextField.setEnabled(true);

            editarBtn.setText("Salvar");
            removerBtn.setEnabled(false);
            voltarBtn.setText("Cancelar");
        } else if (editarBtn.getText().equals("Salvar")) {
            JSONObject dicionarioEmJson = JSONUtils.readExternalJsonObject(palavraSelecionada.getDicionarioFonte().getEnderecoDicionario());
            JSONArray palavrasDoDicionario = (JSONArray) dicionarioEmJson.get("palavras");

            for (Object p : palavrasDoDicionario) {
                JSONObject pJo = (JSONObject) p;
                if (pJo.get("palavra").equals(palavraSelecionada.getPalavra())) {
                    pJo.put("palavra", palavraTextField.getText());
                    pJo.put("significado", significadoTextArea.getText());
                    pJo.put("fonte", fonteTextField.getText());

                    palavrasDoDicionario.remove(p);
                    palavrasDoDicionario.add(pJo);

                    dicionarioEmJson.put("palavras", palavrasDoDicionario);
                    break;
                }
            }
            try {
                Files.write(Paths.get(palavraSelecionada.getDicionarioFonte().getEnderecoDicionario()), dicionarioEmJson.toJSONString().getBytes());
                JOptionPane.showMessageDialog(this, "Palavra \"" + palavraSelecionada.getPalavra() + "\" alterada com sucesso.");
                runningApp.setDicionario(new Dicionario(palavraSelecionada.getDicionarioFonte().getEnderecoDicionario()));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Algum erro aconteceu.\nVeja o terminal para mais informações.");
            }
        }
    }

    private void onRemover() {
        int confirmcao = JOptionPane.showConfirmDialog(this, "Confirmar remoção desta palavra?", "Eliminar palavra?", JOptionPane.YES_NO_OPTION);
        if (confirmcao == 0) {
            runningApp.getDicionario().removePalavra(palavraSelecionada);
            runningApp.updateListModel();
            JOptionPane.showMessageDialog(this, "Palavra \"" + palavraSelecionada.getPalavra() + "\" removida com sucesso.");
            dispose();
        }
    }

    private void onSair() {
        if (voltarBtn.getText().equals("Voltar")) {
            dispose();
            runningApp.setPalavraSelecionada(null);
        } else if(voltarBtn.getText().equals("Cancelar")) {
            palavraTextField.setText(palavraSelecionada.getPalavra());
            palavraTextField.setEnabled(false);

            significadoTextArea.setText(palavraSelecionada.getSignificado());
            significadoTextArea.setEnabled(false);

            fonteTextField.setText(palavraSelecionada.getFonte());
            fonteTextField.setEnabled(false);

            editarBtn.setText("Editar");
            removerBtn.setEnabled(true);
            voltarBtn.setText("Voltar");
        }
    }

    public static void main(String args[]) {
        Dicionario dicionarioDeTestes = new Dicionario("C:\\Users\\areia\\Documents\\Coisas do Luan\\Desenvolvimento\\Estudos\\projeto-dicionario\\src\\br\\univates\\luan\\projeto_dicionario\\data\\dicionario_teste.json");
        AppView appView = new AppView(new Usuario("luan", "0000"));
        appView.setDicionario(dicionarioDeTestes);
        new PalavraView(appView).setVisible(true);
    }
}
