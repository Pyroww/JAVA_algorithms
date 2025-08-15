package br.com.comercio.modelo;

public class ClientePremium extends Cliente implements Descontavel {
    private final double PERCENTUAL_DESCONTO = 0.10; 

    public ClientePremium(int id, String nome) {
        super(id, nome);
    }

    @Override
    public double aplicarDesconto(double valorOriginal) {
        return valorOriginal * (1 - PERCENTUAL_DESCONTO);
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Premium (10% OFF)";
    }
}