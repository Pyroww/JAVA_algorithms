package br.com.comercio.gui;

import br.com.comercio.modelo.Cliente;
import br.com.comercio.modelo.ClientePremium;
import br.com.comercio.servico.GerenciadorClientes;
import java.awt.*;
import javax.swing.*;

public class DialogoCliente extends JDialog {
    private JTextField txtNome;
    private JCheckBox chkPremium;
    private JButton btnSalvar;
    private JButton btnCancelar;

    private GerenciadorClientes gerenciadorClientes;
    private Cliente clienteExistente;

    public DialogoCliente(Frame parent, Cliente cliente, GerenciadorClientes gerenciadorClientes) {
        super(parent, "Gestão de Cliente", true);
        this.gerenciadorClientes = gerenciadorClientes;
        this.clienteExistente = cliente;
        
        setLayout(new BorderLayout(10,10));
        
        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCampos.add(new JLabel("Nome:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; 
        txtNome = new JTextField(20);
        painelCampos.add(txtNome, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Cliente Premium:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        chkPremium = new JCheckBox();
        painelCampos.add(chkPremium, gbc);

        add(painelCampos, BorderLayout.CENTER);
        
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        
        add(painelBotoes, BorderLayout.SOUTH);
        
        
        if (clienteExistente != null) {
            setTitle("Editar Cliente");
            txtNome.setText(clienteExistente.getNome());
            chkPremium.setSelected(clienteExistente instanceof ClientePremium);
        } else {
            setTitle("Adicionar Novo Cliente");
        }
        
        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(e -> salvar());
        
        pack();
        setLocationRelativeTo(parent);
    }

    private void salvar() {
        String nome = txtNome.getText();
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do cliente não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isPremium = chkPremium.isSelected();
        
        if (clienteExistente == null) { 
            gerenciadorClientes.cadastrarCliente(nome, isPremium);
        } else { 
            gerenciadorClientes.editarCliente(clienteExistente.getId(), nome, isPremium);
        }
        dispose();
    }
}