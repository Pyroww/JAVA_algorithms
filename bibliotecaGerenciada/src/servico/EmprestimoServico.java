package servico;
import java.util.Scanner;
import modelo.*;
import repositorio.BibliotecaRepositorio;

public class EmprestimoServico {
    

    public static void fazerEmprestimo(Scanner teclado) {
        System.out.print("Digite o CPF do usuário: ");
        String cpf = teclado.nextLine();
        

        Usuario usuario = Usuarioservico.consultarUsuarioPorCpf(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado. Empréstimo não pode ser realizado.");
            return;
        }

        System.out.print("Livro emprestado (ID): ");
        int idLivro = teclado.nextInt();
        teclado.nextLine();

        Livro livro = buscarLivroPorId(idLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (!livro.isDisponivel()) {
            System.out.println("Este livro já está emprestado e não está disponível.");
            return;
        }

        System.out.print("Digite novamente o CPF do usuário: ");
        String usuarionome = teclado.nextLine();
        if (!usuarionome.equals(cpf)) {
            System.out.println("CPF errado. Empréstimo não pode ser realizado.");
            return;
        }

        System.out.print("Data do empréstimo (dd/mm/aaaa): ");
        String dataEmprestimoInicial = teclado.nextLine();
        System.out.print("Data de devolução (dd/mm/aaaa): ");
        String dataEmprestimoDevolucao = teclado.nextLine();

        BibliotecaRepositorio.getEmprestimos().add(new Emprestimo(livro.getTitulo(), usuarionome, dataEmprestimoInicial, dataEmprestimoDevolucao));
        BibliotecaRepositorio.getHistoricoEmprestimos().add(new Emprestimo(livro.getTitulo(), usuarionome, dataEmprestimoInicial, dataEmprestimoDevolucao));
        livro.setDisponivel(false);

        System.out.println("Empréstimo realizado com sucesso!");
    }

    private static Livro buscarLivroPorId(int idLivro) {
        for (Livro l : LivroService.getLivros()) {
            if (l.getId_livro() == idLivro) {
                return l;
            }
        }
        return null;
    }

    private static boolean isLivroEmprestado(String livro) {
        for (Emprestimo emprestimo : BibliotecaRepositorio.getEmprestimos()) {
            if (emprestimo.getLivro().equalsIgnoreCase(livro)) {
                return true;
            }
        }
        return false;
    }

    public static void listarEmprestimos() {
        if (BibliotecaRepositorio.getEmprestimos().isEmpty()) {
            System.out.println("Nenhum empréstimo realizado.");
        } else {
            for (int i = 0; i < BibliotecaRepositorio.getEmprestimos().size(); i++) {
                System.out.println((i + 1) + " - " + BibliotecaRepositorio.getEmprestimos().get(i));
            }
        }
    }

    public static void listarHistoricoEmprestimos() {
        if (BibliotecaRepositorio.getHistoricoEmprestimos().isEmpty()) {
            System.out.println("Nenhum empréstimo realizado até o momento.");
        } else {
            System.out.println("===== Histórico de Empréstimos =====");
            for (Emprestimo e : BibliotecaRepositorio.getHistoricoEmprestimos()) {
                System.out.println(e);
            }
        }
    }

    public static void editarEmprestimo(Scanner teclado) {
        listarEmprestimos();
        System.out.print("Escolha o número do empréstimo para editar: ");
        int indice = teclado.nextInt() - 1;
        teclado.nextLine();
        if (indice >= 0 && indice < BibliotecaRepositorio.getEmprestimos().size()) {
            Emprestimo emprestimo = BibliotecaRepositorio.getEmprestimos().get(indice);
            System.out.print("Novo livro: ");
            emprestimo.setLivro(teclado.nextLine());
            System.out.print("Novo usuário: ");
            emprestimo.setUsuario(teclado.nextLine());
            System.out.print("Nova data de empréstimo (dd/mm/aaaa): ");
            emprestimo.setDataEmprestimoInicial(teclado.nextLine());
            System.out.print("Nova data de devolução (dd/mm/aaaa): ");
            emprestimo.setDataEmprestimoDevolucao(teclado.nextLine());
            System.out.println("Empréstimo editado com sucesso!");
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    public static void devolverLivro(Scanner teclado) {
        listarEmprestimos();
        System.out.print("Escolha o número do empréstimo para devolver: ");
        int indice = teclado.nextInt() - 1;
        teclado.nextLine();

        if (indice >= 0 && indice < BibliotecaRepositorio.getEmprestimos().size()) {
            Emprestimo emprestimo = BibliotecaRepositorio.getEmprestimos().remove(indice);
            Livro livro = buscarLivroPorTitulo(emprestimo.getLivro());
            if (livro != null) {
                livro.setDisponivel(true);
            }
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    private static Livro buscarLivroPorTitulo(String titulo) {
        for (Livro l : LivroService.getLivros()) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }
}
