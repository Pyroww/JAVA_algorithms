package repositorio;

import java.util.ArrayList;
import java.util.List;
import modelo.Emprestimo;
import modelo.Livro;
import modelo.Usuario;

public class BibliotecaRepositorio {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Livro> livros = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    private static List<Emprestimo> historicoEmprestimos = new ArrayList<>();
    
    public static List<Livro> getLivros() {
        return livros;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public static List<Emprestimo> getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }

}
