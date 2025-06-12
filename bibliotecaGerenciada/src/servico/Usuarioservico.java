package servico;

import java.util.Scanner;
import modelo.*;
import repositorio.BibliotecaRepositorio;

public class Usuarioservico {
    

    public static void cadastrarUsuario(Scanner teclado) {
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF (somente números): ");
        String cpf = teclado.nextLine();
        
        System.out.print("Email: ");
        String email = teclado.nextLine();
        System.out.print("Endereço: ");
        String endereco = teclado.nextLine();
        System.out.print("Telefone: ");
        String telefone = teclado.nextLine();
        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = teclado.nextLine();
        
        BibliotecaRepositorio.getUsuarios().add(new Usuario(nome, email, endereco, telefone, dataNascimento, cpf));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void listarUsuarios() {
        if (BibliotecaRepositorio.getUsuarios().isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (int i = 0; i < BibliotecaRepositorio.getUsuarios().size(); i++) {
                System.out.println((i + 1) + " - " + BibliotecaRepositorio.getUsuarios().get(i));
            }
        }
    }

    public static void editarUsuario(Scanner teclado) {
    listarUsuarios();
    System.out.print("Selecione o número do usuário a ser editado: ");
    int indice = teclado.nextInt() - 1;
    teclado.nextLine();
    
    if (indice < 0 || indice >= BibliotecaRepositorio.getUsuarios().size()) {
        System.out.println("Usuário não encontrado.");
    } else {
        Usuario usuario = BibliotecaRepositorio.getUsuarios().get(indice);
        System.out.print("Novo nome (atual: " + usuario.getNome() + "): ");
        usuario.setNome(teclado.nextLine());
        System.out.print("Novo email (atual: " + usuario.getEmail() + "): ");
        usuario.setEmail(teclado.nextLine());
        System.out.print("Novo endereço (atual: " + usuario.getEndereco() + "): ");
        usuario.setEndereco(teclado.nextLine());
        System.out.print("Novo telefone (atual: " + usuario.getTelefone() + "): ");
        usuario.setTelefone(teclado.nextLine());
        System.out.println("Usuário atualizado com sucesso!");
    }
}

      public static void excluirUsuario(Scanner teclado) {
        listarUsuarios();
        System.out.print("Escolha o número do usuário para excluir: ");
        int indice = teclado.nextInt() - 1;
        teclado.nextLine();
        if (indice >= 0 && indice < BibliotecaRepositorio.getUsuarios().size()) {
            BibliotecaRepositorio.getUsuarios().remove(indice);
            System.out.println("Usuário excluído com sucesso");
        } else {
            System.out.println("Usuário não encontrado.");
        }
      }

      public static Usuario consultarUsuarioPorCpf(String cpf) {
        for (Usuario usuario : BibliotecaRepositorio.getUsuarios()) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null; // não encontrado
    }

    public static void consultarUsuarioPorCpf(Scanner teclado) {
        System.out.print("Digite o CPF para consulta: ");
        String cpf = teclado.nextLine();
        Usuario usuario = consultarUsuarioPorCpf(cpf);

        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
      
    }



