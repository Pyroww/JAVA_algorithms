package br.com.comercio.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private static int proximoId = 1;
    private int id;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private double total;
    private LocalDateTime dataHora;

    public Venda(Cliente cliente) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public void calcularTotal() {
        this.total = 0;
        for (ItemVenda item : itens) {
            this.total += item.getSubtotal();
        }
        // Aplica desconto se o cliente for "Descontavel"
        if (cliente instanceof Descontavel) {
            this.total = ((Descontavel) cliente).aplicarDesconto(this.total);
        }
    }

    public List<ItemVenda> getItens() { return itens; }
    public int getId() { return id; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("================ RECIBO DE VENDA ==================\n");
        sb.append("ID da Venda: ").append(id).append("\n");
        sb.append("Data: ").append(dataHora.format(formatter)).append("\n");
        sb.append("Cliente: ").append(cliente.getNome()).append("\n");
        sb.append("------------------ ITENS ------------------------\n");
        for (ItemVenda item : itens) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("-------------------------------------------------\n");
        if (cliente instanceof Descontavel) {
            sb.append("Subtotal: R$ ").append(String.format("%.2f", itens.stream().mapToDouble(ItemVenda::getSubtotal).sum())).append("\n");
            sb.append("Desconto Premium Aplicado!\n");
        }
        sb.append("TOTAL DA VENDA: R$ ").append(String.format("%.2f", total)).append("\n");
        sb.append("=================================================\n");
        return sb.toString();
    }
}