package br.com.comercio.gui;

import br.com.comercio.modelo.Venda;
import br.com.comercio.servico.CaixaService;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.*;

public class PainelHistorico extends JPanel {
    private MainFrame mainFrame;
    private CaixaService caixaService;
    private JTextArea txtAreaHistorico;

    public PainelHistorico(MainFrame mainFrame, CaixaService caixaService) {
        this.mainFrame = mainFrame;
        this.caixaService = caixaService;
        setLayout(new BorderLayout(10, 10));
        
        
        setBorder(BorderFactory.createTitledBorder("Histórico de Vendas Realizadas"));

        
        txtAreaHistorico = new JTextArea();
        txtAreaHistorico.setEditable(false); 
        txtAreaHistorico.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        
        
        add(new JScrollPane(txtAreaHistorico), BorderLayout.CENTER);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> mainFrame.mostrarPainel("principal"));
        painelBotoes.add(btnVoltar);
        
        add(painelBotoes, BorderLayout.SOUTH);

        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                
                atualizarHistorico();
            }
        });
    }

    /**
     * 
     * 
     */
    public void atualizarHistorico() {
        
        List<Venda> vendas = caixaService.getHistoricoVendas();
        
        
        if (vendas.isEmpty()) {
            txtAreaHistorico.setText("Nenhuma venda registrada até o momento.");
            return;
        }

        
        StringBuilder sb = new StringBuilder();
        for (Venda venda : vendas) {
            
            sb.append(venda.toString());
            sb.append("\n=================================================\n\n"); 
        }
        
        
        txtAreaHistorico.setText(sb.toString());
        
    
        txtAreaHistorico.setCaretPosition(0);
    }
}