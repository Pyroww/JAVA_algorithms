package br.com.comercio.gui;

import br.com.comercio.modelo.Cliente;
import br.com.comercio.modelo.ItemVenda;
import br.com.comercio.modelo.Produto;
import br.com.comercio.modelo.Venda;
import br.com.comercio.servico.CaixaService;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PainelVendas extends JPanel {
    private MainFrame mainFrame;
    private CaixaService caixaService;

    private JComboBox<Cliente> comboClientes;
    private JComboBox<Produto> comboProdutos;
    private JSpinner spinnerQuantidade;
    private JButton btnAdicionarItem;
    private JTable tabelaCarrinho;
    private DefaultTableModel modeloTabelaCarrinho;
    private JButton btnFinalizarVenda;
    private JLabel lblTotal;

    private Venda vendaAtual;
    private boolean isUpdatingComboBox = false; 

    public PainelVendas(MainFrame mainFrame, CaixaService caixaService) {
        this.mainFrame = mainFrame;
        this.caixaService = caixaService;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JPanel painelSelecao = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        comboClientes = new JComboBox<>();
        comboProdutos = new JComboBox<>();
        spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        btnAdicionarItem = new JButton("Adicionar ao Carrinho");

        painelSelecao.add(new JLabel("Cliente:"));
        painelSelecao.add(comboClientes);
        painelSelecao.add(new JLabel("Produto:"));
        painelSelecao.add(comboProdutos);
        painelSelecao.add(new JLabel("Qtd:"));
        painelSelecao.add(spinnerQuantidade);
        painelSelecao.add(btnAdicionarItem);
        add(painelSelecao, BorderLayout.NORTH);

        
        String[] colunas = {"Produto", "Qtd", "Preço Unit.", "Subtotal"};
        modeloTabelaCarrinho = new DefaultTableModel(colunas, 0);
        tabelaCarrinho = new JTable(modeloTabelaCarrinho);
        add(new JScrollPane(tabelaCarrinho), BorderLayout.CENTER);

        
        JPanel painelSul = new JPanel(new BorderLayout());
        lblTotal = new JLabel("Total: R$ 0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
        painelSul.add(lblTotal, BorderLayout.WEST);

        JPanel painelBotoesSul = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnFinalizarVenda = new JButton("Finalizar Venda");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        painelBotoesSul.add(btnFinalizarVenda);
        painelBotoesSul.add(btnVoltar);
        painelSul.add(painelBotoesSul, BorderLayout.EAST);
        add(painelSul, BorderLayout.SOUTH);

        
        btnVoltar.addActionListener(e -> mainFrame.mostrarPainel("principal"));
        
        
        comboClientes.addActionListener(e -> {
            
            if (!isUpdatingComboBox && comboClientes.getSelectedItem() != null) {
                iniciarNovaVenda();
            }
        });

        btnAdicionarItem.addActionListener(e -> adicionarItemAoCarrinho());
        btnFinalizarVenda.addActionListener(e -> finalizarVenda());

        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                atualizarDadosIniciais();
            }
        });
    }

    /**
     * 
     * 
     */
    public void atualizarDadosIniciais() {
        
        isUpdatingComboBox = true;

     
        Cliente clienteSelecionadoAntes = (Cliente) comboClientes.getSelectedItem();
        comboClientes.removeAllItems();
        caixaService.getClientes().forEach(comboClientes::addItem);
        
       
        if (clienteSelecionadoAntes != null) {
            for (int i = 0; i < comboClientes.getItemCount(); i++) {
                if (comboClientes.getItemAt(i).getId() == clienteSelecionadoAntes.getId()) {
                    comboClientes.setSelectedIndex(i);
                    break;
                }
            }
        }
        
       
        comboProdutos.removeAllItems();
        caixaService.getProdutos().stream()
                .filter(p -> p.getQuantidadeEstoque() > 0)
                .forEach(comboProdutos::addItem);

        
        isUpdatingComboBox = false;
        
        
        if (comboClientes.getItemCount() > 0) {
             if(comboClientes.getSelectedIndex() == -1){
                comboClientes.setSelectedIndex(0);
             } else {
                iniciarNovaVenda();
             }
        } else {
             
             vendaAtual = null;
             atualizarCarrinho();
        }
    }

    /**
     * 
     */
    private void iniciarNovaVenda() {
        Cliente clienteSelecionado = (Cliente) comboClientes.getSelectedItem();
        if (clienteSelecionado != null) {
            vendaAtual = caixaService.iniciarVenda(clienteSelecionado.getId());
            atualizarCarrinho();
        }
    }

    /**
     * 
     */
    private void adicionarItemAoCarrinho() {
        Produto produtoSelecionado = (Produto) comboProdutos.getSelectedItem();
        int quantidade = (int) spinnerQuantidade.getValue();

        if (vendaAtual == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente para iniciar a venda.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (produtoSelecionado != null) {
            boolean sucesso = caixaService.adicionarItem(vendaAtual, produtoSelecionado.getId(), quantidade);
            if (sucesso) {
                atualizarCarrinho();
            } else {
                JOptionPane.showMessageDialog(this, "Estoque insuficiente para a quantidade solicitada.", "Estoque Baixo", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum produto selecionado ou não há produtos disponíveis.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 
     */
    private void atualizarCarrinho() {
        modeloTabelaCarrinho.setRowCount(0);
        if (vendaAtual != null) {
            for (ItemVenda item : vendaAtual.getItens()) {
                modeloTabelaCarrinho.addRow(new Object[]{
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        String.format("%.2f", item.getProduto().getPreco()),
                        String.format("%.2f", item.getSubtotal())
                });
            }
            vendaAtual.calcularTotal(); 
            lblTotal.setText(String.format("Total: R$ %.2f", vendaAtual.getTotal()));
        } else {
            lblTotal.setText("Total: R$ 0.00");
        }
    }

    /**
     * 
     */
    private void finalizarVenda() {
        if (vendaAtual != null && !vendaAtual.getItens().isEmpty()) {
            caixaService.finalizarVenda(vendaAtual);
            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!\n\n" + vendaAtual.toString(), "Venda Concluída", JOptionPane.INFORMATION_MESSAGE);
            atualizarDadosIniciais(); 
        } else {
            JOptionPane.showMessageDialog(this, "O carrinho está vazio. Adicione itens para finalizar a venda.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}