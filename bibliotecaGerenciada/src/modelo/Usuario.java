package modelo;   
public class Usuario {
    private String nome;
    private String email;
    private String cpf;
    private String endereco;
    private String telefone;
    private String dataNascimento;
    
        public Usuario(String nome, String email, String endereco, String telefone, String dataNascimento, String cpf) {
            this.nome = nome;
            this.email = email;
            this.endereco = endereco;
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
            this.cpf = cpf;
                }
// getters
        public String getNome() {
            return nome;
        }

        public String getCpf() {
            return cpf;
        }

        public String getEmail() {
            return email;
        }

        public String getEndereco() {
            return endereco;
        }

        public String getTelefone() {
            return telefone;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

// Setters
        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }


        
        @Override
        public String toString() {
            System.out.print("\n");
            return 
             """
             Dados do usu\u00e1rio
             Nome: """ + nome +
                "\nCPF: " + cpf +
                "\nEmail: " + email +
                "\nEndereco: " + endereco +
                "\nTelefone: " + telefone +
                "\nData de Nascimento: " + dataNascimento +
                "";
        }

    }
