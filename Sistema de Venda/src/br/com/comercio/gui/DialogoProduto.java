package br.com.comercio.gui;

import br.com.comercio.modelo.Produto;
import br.com.comercio.servico.GerenciadorEstoque;
import java.awt.*;
import javax.swing.*;

public class DialogoProduto extends JDialog {
    private JTextField txtNome;
    private JTextField txtPreco;
    private JSpinner spinnerQuantidade;
    private JButton btnSalvar;
    private JButton btnCancelar;

    private GerenciadorEstoque gerenciadorEstoque;
    private Produto produtoExistente;

    public DialogoProduto(Frame parent, Produto produto, GerenciadorEstoque gerenciadorEstoque) {
        super(parent, true); // true para ser modal (bloqueia a janela principal)
        this.gerenciadorEstoque = gerenciadorEstoque;
        this.produtoExistente = produto;
        
        setTitle(produto == null ? "Adicionar Produto" : "Editar Produto");
        
        setLayout(new BorderLayout(10,10));
        
        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtNome = new JTextField();
        txtPreco = new JTextField();
        spinnerQuantidade = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));

        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Preço:"));
        painelCampos.add(txtPreco);
        painelCampos.add(new JLabel("Quantidade:"));
        painelCampos.add(spinnerQuantidade);
        
        add(painelCampos, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        
        add(painelBotoes, BorderLayout.SOUTH);
        
        
        if (produtoExistente != null) {
            txtNome.setText(produtoExistente.getNome());
            txtPreco.setText(String.valueOf(produtoExistente.getPreco()));
            spinnerQuantidade.setValue(produtoExistente.getQuantidadeEstoque());
        }
        
        btnCancelar.addActionListener(e -> dispose());
        
        btnSalvar.addActionListener(e -> salvar());
        
        pack(); 
        setLocationRelativeTo(parent);
    }

    private void salvar() {
        try {
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));
            int quantidade = (int) spinnerQuantidade.getValue();
            
            if (produtoExistente == null) { 
                gerenciadorEstoque.adicionarProduto(nome, preco, quantidade);
            } else { 
                gerenciadorEstoque.editarProduto(produtoExistente.getId(), nome, preco, quantidade);
            }
            dispose(); 
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço inválido. Use o formato XX.XX", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}