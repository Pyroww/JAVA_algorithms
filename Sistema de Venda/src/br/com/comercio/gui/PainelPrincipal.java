package br.com.comercio.gui;

import java.awt.*;
import javax.swing.*;

public class PainelPrincipal extends JPanel {
    private MainFrame mainFrame;

    public PainelPrincipal(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout()); 

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 1, 20, 20));
        
        Font fonteBotao = new Font("Arial", Font.BOLD, 22);

        JButton btnVendas = new JButton("Realizar Venda");
        btnVendas.setFont(fonteBotao);
        btnVendas.addActionListener(e -> mainFrame.mostrarPainel("vendas"));
        
        JButton btnEstoque = new JButton("Gerenciar Estoque");
        btnEstoque.setFont(fonteBotao);
        btnEstoque.addActionListener(e -> mainFrame.mostrarPainel("estoque"));
        
        JButton btnClientes = new JButton("Gerenciar Clientes");
        btnClientes.setFont(fonteBotao);
        btnClientes.addActionListener(e -> mainFrame.mostrarPainel("clientes"));
        
        JButton btnHistorico = new JButton("Histórico de Vendas");
        btnHistorico.setFont(fonteBotao);
        btnHistorico.addActionListener(e -> mainFrame.mostrarPainel("historico"));
        
        JButton btnSair = new JButton("Sair do Sistema");
        btnSair.setFont(fonteBotao);
        btnSair.addActionListener(e -> System.exit(0));

        painelBotoes.add(btnVendas);
        painelBotoes.add(btnEstoque);
        painelBotoes.add(btnClientes);
        painelBotoes.add(btnHistorico);
        painelBotoes.add(btnSair);
        
        add(painelBotoes);
    }
}
