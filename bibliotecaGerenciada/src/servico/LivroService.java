package servico;
import java.util.List;
import java.util.Scanner;
import modelo.*;
import repositorio.BibliotecaRepositorio;

public class LivroService {
    
    public static List<Livro> getLivros() {
        return BibliotecaRepositorio.getLivros();
    }
    public static void cadastrarLivro(Scanner teclado) {
        System.out.print("Título: ");
        String titulo = teclado.nextLine();
        System.out.print("Autor: ");
        String autor = teclado.nextLine();
        System.out.print("ID do livro: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Editora: ");
        String editora = teclado.nextLine();
        System.out.print("Ano de publicação: ");
        int anoPublicacao = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Gênero: ");
        String genero = teclado.nextLine();

        BibliotecaRepositorio.getLivros().add(new Livro(titulo, autor, id, editora, anoPublicacao, genero));
        System.out.println("Livro cadastrado com sucesso!");
    }

    public static void listarLivros() {
        if (BibliotecaRepositorio.getLivros().isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (int i = 0; i < BibliotecaRepositorio.getLivros().size(); i++) {
                System.out.println((i + 1) + " - " + BibliotecaRepositorio.getLivros().get(i));
            }
        }
    }

    public static void editarLivro(Scanner teclado) {
        System.out.print("Informe o ID do livro que deseja editar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        for (Livro livro : BibliotecaRepositorio.getLivros()) {
            if (livro.getId_livro() == id) {
                System.out.print("Novo título: ");
                String titulo = teclado.nextLine();
                System.out.print("Novo autor: ");
                String autor = teclado.nextLine();
                System.out.print("Nova editora: ");
                String editora = teclado.nextLine();
                System.out.print("Novo ano de publicação: ");
                int anoPublicacao = teclado.nextInt();
                teclado.nextLine();
                System.out.print("Novo gênero: ");
                String genero = teclado.nextLine();


                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setEditora(editora);
                livro.setAnoPublicacao(anoPublicacao);
                livro.setGenero(genero);

                System.out.println("Livro atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void excluirLivro(Scanner teclado) {
        listarLivros();
        System.out.print("Escolha o número do livro para excluir: ");
        int indice = teclado.nextInt() - 1;
        teclado.nextLine();
        if (indice >= 0 && indice < BibliotecaRepositorio.getLivros().size()) {
            BibliotecaRepositorio.getLivros().remove(indice);
            System.out.println("Livro excluído com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void buscarLivrosPorAutor(Scanner teclado) {
    System.out.print("Digite o nome do autor para buscar: ");
    String autorBusca = teclado.nextLine().toLowerCase();
    boolean encontrado = false;

    for (Livro livro : BibliotecaRepositorio.getLivros()) {
        if (livro.getAutor().toLowerCase().contains(autorBusca)) {
            System.out.println(livro);
            encontrado = true;
        }
    }

    if (!encontrado) {
        System.out.println("Nenhum livro encontrado para o autor informado.");
    }
}

    public static Livro consultarLivroPorIsbn(int id) {
    for (Livro livro : BibliotecaRepositorio.getLivros()) {
        if (livro.getId_livro() == id) {
            return livro;
        }
    }
    return null;
}

    public static void consultarLivroPorIsbn(Scanner teclado) {
        System.out.print("Digite o ISBN do livro para consulta: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        Livro livro = consultarLivroPorIsbn(id);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

}
