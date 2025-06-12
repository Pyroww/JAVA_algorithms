package modelo;


public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int id_livro;
    private String genero;
    private boolean disponivel;
    

    public Livro(String titulo, String autor, int id_livro, String editora, int anoPublicacao, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.id_livro = id_livro;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.disponivel = true;
    }

    // setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public int getId_livro() {
        return id_livro;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    
    @Override
    public String toString() {
        System.out.print("\n");
            return 
             """
             Dados do livro
             Titulo: """ + titulo +
                "\nAutor: " + autor +
                "\nIsbn: " + id_livro +
                "\nEditora: " + editora +
                "\nAno de publicação: " + anoPublicacao +
                "\nGenero: " + genero +
                " | Disponível: " + (disponivel ? "Sim" : "Não") +
                "";
        }

}