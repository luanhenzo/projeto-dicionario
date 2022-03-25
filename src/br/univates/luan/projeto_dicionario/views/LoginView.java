package br.univates.luan.projeto_dicionario.views;

import br.univates.luan.projeto_dicionario.entities.Usuario;
import br.univates.luan.projeto_dicionario.utilities.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JDialog {
    private JPanel contentPane;
    private JButton sairBtn;
    private JButton loginBtn;
    private JTextField userTextField;
    private JPasswordField passwordTextField;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JPanel inputsPanel;
    private JPanel btnsPanel;

    // WINDOWS
    private static final JSONArray LISTA_DE_USUARIOS = JSONUtils.readExternalJsonArray("C:\\Users\\areia\\Documents\\Coisas do Luan\\Desenvolvimento\\Estudos\\projeto-dicionario\\src\\br\\univates\\luan\\projeto_dicionario\\data\\usuarios.json");

    // LINUX
    //private static final JSONArray LISTA_DE_USUARIOS = JSONUtils.readExternalJson("/home/luansinh0/IdeaProjects/projeto-dicionario/src/br/univates/luan/projeto_dicionario/data/usuarios.json");

    public LoginView() {
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(loginBtn);

        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

        int dialogX = screenDimensions.width / 2 - getWidth() / 2;
        int dialogY = screenDimensions.height / 2 - getHeight() / 2;
        this.setLocation(dialogX, dialogY);

        loginBtn.addActionListener(e -> onLoginValidaExistenciaUsuario(LISTA_DE_USUARIOS));

        sairBtn.addActionListener(e -> onSair());

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

    private void onLoginValidaExistenciaUsuario(JSONArray listaDeUsuarios) {
        boolean usuarioEncontrado = false;
        String senha = new String(passwordTextField.getPassword()).trim();
        Usuario usuarioTentandoEntrar = new Usuario(userTextField.getText(), senha);
        System.out.println(usuarioTentandoEntrar.getNomeDeUsuario());
        System.out.println(usuarioTentandoEntrar.getSenha());
        if (usuarioTentandoEntrar != null && listaDeUsuarios != null) {
            for(int i = 0; i < listaDeUsuarios.size(); i++) {
                JSONObject usuarioNoJson = (JSONObject) listaDeUsuarios.get(i);
                if (usuarioTentandoEntrar.getNomeDeUsuario().equals(usuarioNoJson.get("nome")) &&
                    usuarioTentandoEntrar.getSenha().equals(usuarioNoJson.get("senha"))) {
                        usuarioEncontrado = true;
                        break;
                }
            }
        }
        if (usuarioEncontrado) {
            dispose();
            new AppView(usuarioTentandoEntrar).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!");
        }
    }

    private void onSair() {
        dispose();
    }

}
