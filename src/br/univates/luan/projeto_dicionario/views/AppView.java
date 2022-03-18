package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Dicionario;
import br.univates.luan.projeto_dicionario.entities.Palavra;
import br.univates.luan.projeto_dicionario.entities.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppView extends JDialog {
    private Usuario usuarioLogado;

    private JPanel contentPane;
    private JButton pesquisarBtn;
    private JButton sairBtn;
    private JPanel btnsPanel;
    private JButton carregarBtn;
    private JTextField enderecoDicinarioTextField;
    private JPanel leituraDicionarioPanel;
    private JButton registrarBtn;
    private JLabel enderecoDicionarioLabel;
    private JPanel enderecoDicionarioPanel;
    private JScrollPane dicionarioScrollPanel;
    private JList palavrasList;
    private DefaultListModel<Palavra> listaDePalavras;

    private Dicionario dicionario;

    public AppView(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(sairBtn);

        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

        int dialogX = screenDimensions.width / 2 - getWidth() / 2;
        int dialogY = screenDimensions.height / 2 - getHeight() / 2;
        this.setLocation(dialogX, dialogY);

        carregarBtn.addActionListener(e -> onCarregarDicionario());

        registrarBtn.addActionListener(e -> onRegistrar());

        pesquisarBtn.addActionListener(e -> onPesquisar());

        sairBtn.addActionListener(e -> onSair());

        listaDePalavras = new DefaultListModel();

        palavrasList.setModel(listaDePalavras);
        palavrasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        palavrasList.setLayoutOrientation(JList.VERTICAL);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onSair();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSair();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onRegistrar() {
        System.out.println("REGISTRAR!");
    }

    private void onPesquisar() {
        System.out.println("PESQUISAR!");
    }

    private void onSair() {
        dispose();
        new LoginView().setVisible(true);
    }

    private void onCarregarDicionario() {
        try {
            dicionario = new Dicionario(enderecoDicinarioTextField.getText());
            for (Palavra palavra : dicionario.getPalavras()) {
                listaDePalavras.addElement(palavra);
            }
            enderecoDicinarioTextField.setText("");
        } catch (NullPointerException e) {
            dicionario = null;
            listaDePalavras.removeAllElements();
            JOptionPane.showMessageDialog(this, "Nenhum dicionário foi encontrado neste endereço!");
        }
    }

    public static void main(String args[]) {
        new AppView(new Usuario("luan", "0000")).setVisible(true);
    }
}
