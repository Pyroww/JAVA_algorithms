package br.com.comercio.gui;

import br.com.comercio.modelo.Cliente;
import br.com.comercio.modelo.ClientePremium;
import br.com.comercio.servico.GerenciadorClientes;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PainelClientes extends JPanel {
    private MainFrame mainFrame;
    private GerenciadorClientes gerenciadorClientes;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public PainelClientes(MainFrame mainFrame, GerenciadorClientes gerenciadorClientes) {
        this.mainFrame = mainFrame;
        this.gerenciadorClientes = gerenciadorClientes;
        setLayout(new BorderLayout(10, 10));

        
        String[] colunas = {"ID", "Nome", "Tipo de Cliente"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnAdicionar = new JButton("Adicionar Cliente");
        btnAdicionar.addActionListener(e -> {
            DialogoCliente dialogo = new DialogoCliente(mainFrame, null, gerenciadorClientes);
            dialogo.setVisible(true);
            
            atualizarTabela();
        });
        
        JButton btnEditar = new JButton("Editar Cliente");
        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idCliente = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            Cliente cliente = gerenciadorClientes.buscarPorId(idCliente).orElse(null);
            
            if (cliente != null) {
                DialogoCliente dialogo = new DialogoCliente(mainFrame, cliente, gerenciadorClientes);
                dialogo.setVisible(true);
                
                atualizarTabela();
            }
        });

        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> mainFrame.mostrarPainel("principal"));

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnVoltar);
        add(painelBotoes, BorderLayout.SOUTH);

       
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                
                atualizarTabela();
            }
        });
    }
    
    /**
     * 
     */
    public void atualizarTabela() {
        
        modeloTabela.setRowCount(0);
        
        
        List<Cliente> clientes = gerenciadorClientes.listarTodosClientes();
        
        
        for (Cliente c : clientes) {
            String tipo = (c instanceof ClientePremium) ? "Premium" : "Normal";
            modeloTabela.addRow(new Object[]{c.getId(), c.getNome(), tipo});
        }
    }
}