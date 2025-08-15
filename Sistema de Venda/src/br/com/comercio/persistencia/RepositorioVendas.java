package br.com.comercio.persistencia;

import br.com.comercio.modelo.Venda;
import java.util.ArrayList;
import java.util.List;

public class RepositorioVendas {
    private final List<Venda> vendas = new ArrayList<>();

    public void salvar(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> listarTodas() {
        return new ArrayList<>(vendas);
    }
}