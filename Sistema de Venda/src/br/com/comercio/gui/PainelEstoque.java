package br.com.comercio.gui;

import br.com.comercio.modelo.Produto;
import br.com.comercio.servico.GerenciadorEstoque;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PainelEstoque extends JPanel {
    
    private MainFrame mainFrame;
    private GerenciadorEstoque gerenciadorEstoque;
    private JTable tabela;
    private DefaultTableModel modeloTabela;


    public PainelEstoque(MainFrame mainFrame, GerenciadorEstoque gerenciadorEstoque) {
        this.mainFrame = mainFrame;
        this.gerenciadorEstoque = gerenciadorEstoque;
        setLayout(new BorderLayout(10, 10));

        
        String[] colunas = {"ID", "Nome", "Preço", "Estoque"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> {
            DialogoProduto dialogo = new DialogoProduto(mainFrame, null, gerenciadorEstoque);
            dialogo.setVisible(true);
            atualizarTabela(); 
        });
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um produto para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idProduto = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            Produto produto = gerenciadorEstoque.buscarPorId(idProduto).orElse(null);
            
            if (produto != null) {
                DialogoProduto dialogo = new DialogoProduto(mainFrame, produto, gerenciadorEstoque);
                dialogo.setVisible(true);
                atualizarTabela();
            }
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idProduto = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            String nomeProduto = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

            int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o produto '" + nomeProduto + "'?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                gerenciadorEstoque.excluirProduto(idProduto);
                atualizarTabela();
            }
        });

        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> mainFrame.mostrarPainel("principal"));

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnVoltar);
        add(painelBotoes, BorderLayout.SOUTH);


        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                
                atualizarTabela();
            }
        });
    }
    
    public void atualizarTabela() {
        modeloTabela.setRowCount(0);
        List<Produto> produtos = gerenciadorEstoque.getProdutosDisponiveis();
        for (Produto p : produtos) {
            modeloTabela.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidadeEstoque()});
        }
    }
}