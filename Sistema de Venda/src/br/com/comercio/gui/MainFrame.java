package br.com.comercio.gui;

import br.com.comercio.persistencia.RepositorioClientes;
import br.com.comercio.persistencia.RepositorioProdutos;
import br.com.comercio.persistencia.RepositorioVendas;
import br.com.comercio.servico.CaixaService;
import br.com.comercio.servico.GerenciadorClientes;
import br.com.comercio.servico.GerenciadorEstoque;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel paineisContainer;

    public MainFrame() {
        super("Sistema de Gestão Comercial");

        // --- 1. Inicializar a lógica de negócio (serviços e repositórios) ---
        RepositorioProdutos repoProdutos = new RepositorioProdutos();
        RepositorioClientes repoClientes = new RepositorioClientes();
        RepositorioVendas repoVendas = new RepositorioVendas();
        
        CaixaService caixaService = new CaixaService(repoClientes, repoProdutos, repoVendas);
        GerenciadorEstoque gerenciadorEstoque = new GerenciadorEstoque(repoProdutos);
        GerenciadorClientes gerenciadorClientes = new GerenciadorClientes(repoClientes);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        
        cardLayout = new CardLayout();
        paineisContainer = new JPanel(cardLayout);

        
        PainelPrincipal painelPrincipal = new PainelPrincipal(this);
        PainelVendas painelVendas = new PainelVendas(this, caixaService);
        PainelEstoque painelEstoque = new PainelEstoque(this, gerenciadorEstoque);
        PainelClientes painelClientes = new PainelClientes(this, gerenciadorClientes);
        PainelHistorico painelHistorico = new PainelHistorico(this, caixaService);
        
        
        paineisContainer.add(painelPrincipal, "principal");
        paineisContainer.add(painelVendas, "vendas");
        paineisContainer.add(painelEstoque, "estoque");
        paineisContainer.add(painelClientes, "clientes");
        paineisContainer.add(painelHistorico, "historico");

        
        add(paineisContainer);
    }

    /**
     * 
     * 
     * @param nome 
     */
    public void mostrarPainel(String nome) {
        cardLayout.show(paineisContainer, nome);
    }
}