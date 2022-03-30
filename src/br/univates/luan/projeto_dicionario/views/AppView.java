package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Dicionario;
import br.univates.luan.projeto_dicionario.entities.Palavra;
import br.univates.luan.projeto_dicionario.entities.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
    private DefaultListModel<String> palavrasListModel;

    private Dicionario dicionario;
    private Palavra palavraSelecionada;

    public AppView(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.palavraSelecionada = null;
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

        palavrasListModel = new DefaultListModel();

        palavrasList.setModel(palavrasListModel);
        palavrasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        palavrasList.setLayoutOrientation(JList.VERTICAL);

        AppView instance = this;
        palavrasList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList palavrasList = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = palavrasList.locationToIndex(evt.getPoint());
                    palavraSelecionada = dicionario.getPalavra(index);
                    new PalavraView(instance).setVisible(true);
                }
            }
        });

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

    public Palavra getPalavraSelecionada() {
        return palavraSelecionada;
    }

    public void setPalavraSelecionada(Palavra palavraSelecionada) {
        this.palavraSelecionada = palavraSelecionada;
    }

    public Dicionario getDicionario() {
        return dicionario;
    }

    public void setDicionario(Dicionario dic) {
        this.dicionario = dic;
    }

    public void updateListModel() {
        palavrasListModel.removeAllElements();
        for (Palavra palavra : dicionario.getPalavras()) {
            palavrasListModel.addElement(palavra.getPalavra());
        }
    }

    private void onRegistrar() {
        new RegistrarView(this).setVisible(true);
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
            Dicionario dicio = new Dicionario(enderecoDicinarioTextField.getText());
            setDicionario(dicio);
            updateListModel();
            enderecoDicinarioTextField.setText("");
            registrarBtn.setEnabled(true);
            pesquisarBtn.setEnabled(true);
        } catch (NullPointerException e) {
            dicionario = null;
            palavrasListModel.removeAllElements();
            registrarBtn.setEnabled(false);
            pesquisarBtn.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Nenhum dicionário foi encontrado neste endereço!");
        }
    }

    public static void main(String args[]) {
        new AppView(new Usuario("luan", "0000")).setVisible(true);
    }
}
