package br.com.comercio.servico;

import br.com.comercio.modelo.Cliente;
import br.com.comercio.modelo.ClienteNormal;
import br.com.comercio.modelo.ClientePremium;
import br.com.comercio.persistencia.RepositorioClientes;
import java.util.List;
import java.util.Optional;

public class GerenciadorClientes {
    private final RepositorioClientes repositorioClientes;

    public GerenciadorClientes(RepositorioClientes repositorioClientes) {
        this.repositorioClientes = repositorioClientes;
    }

    public void cadastrarCliente(String nome, boolean isPremium) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("❌ Erro: O nome do cliente não pode ser vazio.");
            return;
        }
        repositorioClientes.salvar(nome, isPremium);
    }

    public List<Cliente> listarTodosClientes() {
        return repositorioClientes.listarTodos();
    }

    
    public Optional<Cliente> buscarPorId(int id) {
        return repositorioClientes.buscarPorId(id);
    }

    
    /**
     * 
     * @param id 
     * @param novoNome 
     * @param novoStatusPremium 
     * @return 
     */
    public boolean editarCliente(int id, String novoNome, boolean novoStatusPremium) {
        
        if (repositorioClientes.buscarPorId(id).isEmpty()) {
            return false;
        }
        if (novoNome == null || novoNome.trim().isEmpty()) {
            System.out.println("❌ Erro: O nome do cliente não pode ser vazio.");
            return false;
        }

        
        Cliente clienteAtualizado;
        if (novoStatusPremium) {
            clienteAtualizado = new ClientePremium(id, novoNome);
        } else {
            clienteAtualizado = new ClienteNormal(id, novoNome);
        }
        
        return repositorioClientes.atualizar(clienteAtualizado);
    }
}