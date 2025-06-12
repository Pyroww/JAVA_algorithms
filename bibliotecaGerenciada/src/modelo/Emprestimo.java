package modelo;
public class Emprestimo {
    private String livro;
    private String usuarionome;
    private String dataEmprestimoInicial;
    private String dataEmprestimoDevolucao;

    public Emprestimo(String livro, String usuarionome, String dataEmprestimoInicial, String dataEmprestimoDevolucao) {
        this.livro = livro;
        this.usuarionome = usuarionome;
        this.dataEmprestimoInicial = dataEmprestimoInicial;
        this.dataEmprestimoDevolucao = dataEmprestimoDevolucao;
    }

    public String getLivro() { return livro; }
    public void setLivro(String livro) { this.livro = livro; }

    public String getUsuario() { return usuarionome; }
    public void setUsuario(String usuarionome) { this.usuarionome = usuarionome; }

    public String getDataEmprestimoInicial() { return dataEmprestimoInicial; }
    public void setDataEmprestimoInicial(String dataEmprestimoInicial) {
        this.dataEmprestimoInicial = dataEmprestimoInicial;
    }

    public String getDataEmprestimoDevolucao() { return dataEmprestimoDevolucao; }
    public void setDataEmprestimoDevolucao(String dataEmprestimoDevolucao) {
        this.dataEmprestimoDevolucao = dataEmprestimoDevolucao;
    }

    @Override
    public String toString() {
        System.out.print("\n");
            return 
             """
             Dados do emprestimo
             Livro: """ + livro +
                "\nCPF: " + usuarionome +
                "\nData da realização do emprestimo: " + dataEmprestimoInicial +
                "\nPrazo final do emprestimo: " + dataEmprestimoDevolucao +
                "";
        }
}