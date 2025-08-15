package br.com.comercio.persistencia;

import br.com.comercio.modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioProdutos {
    private final List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public RepositorioProdutos() {
        produtos.add(new Produto(proximoId++, "Arroz 5kg", 25.50, 50));
        produtos.add(new Produto(proximoId++, "Feijão 1kg", 8.90, 40));
        produtos.add(new Produto(proximoId++, "Óleo de Soja", 9.00, 30));
        produtos.add(new Produto(proximoId++, "Refrigerante 2L", 10.50, 60));
    }

    public Produto salvar(Produto produto) {
        Produto novoProduto = new Produto(proximoId++, produto.getNome(), produto.getPreco(), produto.getQuantidadeEstoque());
        produtos.add(novoProduto);
        return novoProduto;
    }

    public Optional<Produto> buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public boolean removerPorId(int id) {
        return produtos.removeIf(produto -> produto.getId() == id);
    }

    
    /**
     * 
     * @param produtoAtualizado 
     * @return 
     */
    public boolean atualizar(Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produtoAtualizado.getId()) {
                produtos.set(i, produtoAtualizado);
                return true;
            }
        }
        return false;
    }
}