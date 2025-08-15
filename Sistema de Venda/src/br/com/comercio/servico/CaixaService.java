package br.com.comercio.servico;

import br.com.comercio.modelo.Cliente;
import br.com.comercio.modelo.ItemVenda;
import br.com.comercio.modelo.Produto;
import br.com.comercio.modelo.Venda;
import br.com.comercio.persistencia.RepositorioClientes;
import br.com.comercio.persistencia.RepositorioProdutos;
import br.com.comercio.persistencia.RepositorioVendas;
import java.util.List;
import java.util.Optional;

public class CaixaService {
    private final RepositorioClientes repositorioClientes;
    private final RepositorioProdutos repositorioProdutos;
    private final RepositorioVendas repositorioVendas;
    private final GerenciadorEstoque gerenciadorEstoque;

    public CaixaService(RepositorioClientes repoClientes, RepositorioProdutos repoProdutos, RepositorioVendas repoVendas) {
        this.repositorioClientes = repoClientes;
        this.repositorioProdutos = repoProdutos;
        this.repositorioVendas = repoVendas;
        this.gerenciadorEstoque = new GerenciadorEstoque(repoProdutos);
    }

    public Venda iniciarVenda(int clienteId) {
        Optional<Cliente> clienteOpt = repositorioClientes.buscarPorId(clienteId);
        if (clienteOpt.isPresent()) {
            return new Venda(clienteOpt.get());
        }
        return null; // ou lançar exceção
    }

    public boolean adicionarItem(Venda venda, int produtoId, int quantidade) {
        if (venda == null) return false;

        Optional<Produto> produtoOpt = repositorioProdutos.buscarPorId(produtoId);
        if (produtoOpt.isPresent() && gerenciadorEstoque.temEstoqueSuficiente(produtoId, quantidade)) {
            Produto produto = produtoOpt.get();
            ItemVenda item = new ItemVenda(produto, quantidade);
            venda.adicionarItem(item);
            gerenciadorEstoque.baixarEstoque(produtoId, quantidade);
            return true;
        }
        return false;
    }

    public void finalizarVenda(Venda venda) {
        if (venda != null && !venda.getItens().isEmpty()) {
            venda.calcularTotal();
            repositorioVendas.salvar(venda);
            System.out.println("\n✅ Venda finalizada com sucesso!");
            System.out.println(venda);
        } else {
            System.out.println("❌ Carrinho vazio. Venda cancelada.");
        }
    }

    public List<Cliente> getClientes() {
        return repositorioClientes.listarTodos();
    }

    public List<Produto> getProdutos() {
        return repositorioProdutos.listarTodos();
    }

    public List<Venda> getHistoricoVendas() {
        return repositorioVendas.listarTodas();
    }
}