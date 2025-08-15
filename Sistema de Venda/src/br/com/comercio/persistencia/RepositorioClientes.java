package br.com.comercio.persistencia;

import br.com.comercio.modelo.Cliente;
import br.com.comercio.modelo.ClienteNormal;
import br.com.comercio.modelo.ClientePremium;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioClientes {
    private final List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    public RepositorioClientes() {
        clientes.add(new ClienteNormal(proximoId++, "João da Silva"));
        clientes.add(new ClientePremium(proximoId++, "Maria Souza"));
        clientes.add(new ClienteNormal(proximoId++, "Carlos Pereira"));
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public Cliente salvar(String nome, boolean isPremium) {
        Cliente novoCliente;
        if (isPremium) {
            novoCliente = new ClientePremium(proximoId++, nome);
        } else {
            novoCliente = new ClienteNormal(proximoId++, nome);
        }
        clientes.add(novoCliente);
        return novoCliente;
    }

    
    /**
     * 
     * @param clienteAtualizado 
     * @return 
     */
    public boolean atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteAtualizado.getId()) {
                clientes.set(i, clienteAtualizado);
                return true;
            }
        }
        return false;
    }
}