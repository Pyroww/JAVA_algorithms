package br.com.comercio.servico;

import br.com.comercio.modelo.Produto;
import br.com.comercio.persistencia.RepositorioProdutos;
import java.util.List;
import java.util.Optional;

public class GerenciadorEstoque {
    private final RepositorioProdutos repositorioProdutos;

    public GerenciadorEstoque(RepositorioProdutos repositorioProdutos) {
        this.repositorioProdutos = repositorioProdutos;
    }

    public void adicionarProduto(String nome, double preco, int quantidade) {
        Produto novoProduto = new Produto(0, nome, preco, quantidade);
        repositorioProdutos.salvar(novoProduto);
    }

    public boolean temEstoqueSuficiente(int produtoId, int quantidadeDesejada) {
        Optional<Produto> produtoOpt = repositorioProdutos.buscarPorId(produtoId);
        return produtoOpt.isPresent() && produtoOpt.get().getQuantidadeEstoque() >= quantidadeDesejada;
    }

    public void baixarEstoque(int produtoId, int quantidade) {
        Optional<Produto> produtoOpt = repositorioProdutos.buscarPorId(produtoId);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        }
    }

    public List<Produto> getProdutosDisponiveis() {
        return repositorioProdutos.listarTodos();
    }
    
    
    public Optional<Produto> buscarPorId(int id) {
        return repositorioProdutos.buscarPorId(id);
    }

    public boolean excluirProduto(int produtoId) {
        return repositorioProdutos.removerPorId(produtoId);
    }
    
    
    /**
       
       @param id 
       @param novoNome 
       @param novoPreco 
       @param novaQuantidade 
       @return 
     */

    public boolean editarProduto(int id, String novoNome, double novoPreco, int novaQuantidade) {
       
        if (novoNome == null || novoNome.trim().isEmpty()) {
            System.out.println("❌ Erro: O nome do produto não pode ser vazio.");
            return false;
        }
        if (novoPreco < 0) {
            System.out.println("❌ Erro: O preço não pode ser negativo.");
            return false;
        }
        if (novaQuantidade < 0) {
            System.out.println("❌ Erro: A quantidade em estoque não pode ser negativa.");
            return false;
        }
        if (repositorioProdutos.buscarPorId(id).isEmpty()){
            return false;
        }

        Produto produtoAtualizado = new Produto(id, novoNome, novoPreco, novaQuantidade);
        return repositorioProdutos.atualizar(produtoAtualizado);
    }
}