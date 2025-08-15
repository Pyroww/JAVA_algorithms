package br.com.comercio.modelo;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private double precoUnitarioCongelado; // Preço no momento da venda

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitarioCongelado = produto.getPreco();
    }

    public double getSubtotal() {
        return quantidade * precoUnitarioCongelado;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }

    @Override
    public String toString() {
        return String.format("%s | Qtd: %d | Preço Unit.: R$ %.2f | Subtotal: R$ %.2f",
                produto.getNome(), quantidade, precoUnitarioCongelado, getSubtotal());
    }
}