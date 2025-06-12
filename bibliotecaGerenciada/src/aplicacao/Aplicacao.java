package aplicacao;
import java.util.Scanner;
import servico.*;



public class Aplicacao {
        public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);
            int escolha = 0;
            
            do {
                
                System.out.println("""
                                   ========================================
                                   || Bem vindo ao Sistema da Biblioteca ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||      1 - Gerenciador de livros     ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||    2 - Gerenciador de usuários     ||
                                   ========================================""");

                System.out.println("""
                                   ========================================
                                   ||    3 - gerenciador de emprestimos  ||
                                   ========================================""");
                
                System.out.println("""
                                   ========================================
                                   ||    0 - para sair do sistema        ||
                                   ========================================""");
                

                switch (escolha) {
                    case 1:
                        gerenciadorDeLivros(teclado);
                        break;
                    case 2:
                       gerenciadorDeUsuarios(teclado);
                        break;
                    case 3:
                        gerenciadorDeEmprestimo(teclado);
                        break;
                    case 0:
                        
                        break;
                    default:
                        
                        System.out.println("Opção inválida, tente novamente.");
                }
                System.out.print("Escolha uma opção: ");
                escolha = teclado.nextInt();
                teclado.nextLine();
                System.out.println("\u001B[2J"); 
                System.out.flush();

             } while (escolha != 0);
             
        }

//==========================================================================================================================================//
// ===================================================Menu gerenciador de livros============================================================//
//==========================================================================================================================================//
        public static void gerenciadorDeLivros(Scanner teclado) {
            int escolha1 = 7;
            int escolha3;
            do { 

                System.out.println("\u001B[2J"); 
                System.out.flush();
                System.out.println("""
                                   ========================================
                                   || Bem vindo ao gerenciador de livros ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         1 - Cadastrar livro        ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||   2 - Editar informa\u00e7\u00f5es do livro  ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         3 - Listar livros          ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         4 - excluir livro          ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||    5 - Buscar livro por autor      ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||     6 - Buscar livro por isbn      ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||     0 - para voltar ao menu        ||
                                   ========================================
                                   """);

                System.out.print("Escolha uma opção: ");
                escolha1 = teclado.nextInt();
                teclado.nextLine();
                
                
                

                switch (escolha1) {
                    
                    case 1:
                        System.out.println("Cadastrar livro");
                        LivroService.cadastrarLivro(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 2:

                        System.out.println("Editar informações do livro");
                        LivroService.editarLivro(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 3:
                        System.out.println("Listar livros");
                        LivroService.listarLivros();
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 4:
                        System.out.println("Excluir livro");
                        LivroService.excluirLivro(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    
                    case 5:
                        System.out.println("Buscar livro por autor");
                        LivroService.buscarLivrosPorAutor(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;

                    case 6:
                        System.out.println("Buscar livro por ISBN");
                        LivroService.consultarLivroPorIsbn(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 7:

                    case 0:
                        System.out.println("\u001B[2J"); 
                        System.out.flush();
                        System.out.println("Voltando ao menu anterior...");
                        System.out.println("""
                                   ========================================
                                   || Bem vindo ao Sistema da Biblioteca ||
                                   ========================================""");
                        System.out.println("""
                                        ========================================
                                        ||      1 - Gerenciador de livros     ||
                                        ========================================""");
                        System.out.println("""
                                        ========================================
                                        ||    2 - Gerenciador de usuários     ||
                                        ========================================""");
                        System.out.println("""
                                   ========================================
                                   ||    3 - gerenciador de emprestimos  ||
                                   ========================================""");

                        System.out.println("""
                                        ========================================
                                        ||    0 - para sair do sistema        ||
                                        ========================================""");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        
                }


            } while (escolha1 != 0);

        }

//==========================================================================================================================================//
// ======================================================Menu gerenciador de usuários=======================================================//
//==========================================================================================================================================//

        public static void gerenciadorDeUsuarios(Scanner teclado) {
            int escolha2 = 7;
            do {
                System.out.println("\u001B[2J"); 
                System.out.flush();

                System.out.println("""
                                   ========================================
                                   ||Bem vindo ao gerenciador de usu\u00e1rios||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         1 - Cadastrar usu\u00e1rio      ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||  2 - Editar informa\u00e7\u00f5es do usu\u00e1rio ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         3 - Listar usu\u00e1rios        ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         4 - excluir usu\u00e1rio        ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||     5 - Buscar usuário por CPF     ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||     0 - para sair do programa      ||
                                   ========================================
                                   """);
                System.out.print("Escolha uma opção: ");
                escolha2 = teclado.nextInt();
                teclado.nextLine();
                
                

                switch (escolha2) {
                    case 1:
                        System.out.println("Cadastrar usu\u00e1rio");
                        Usuarioservico.cadastrarUsuario(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();

                        break;
                    case 2:
                        System.out.println("Editar informa\u00e7\u00f5es do usu\u00e1rio");
                        Usuarioservico.editarUsuario(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 3:
                        
                        System.out.println("Listar usu\u00e1rios");
                        Usuarioservico.listarUsuarios();
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 4:
                        System.out.println("Excluir usu\u00e1rio");
                        Usuarioservico.excluirUsuario(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    
                    case 5:
                        System.out.println("Consulta de usuário por CPF");
                        Usuarioservico.consultarUsuarioPorCpf(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                    case 7:
                    
                    case 0:
                        System.out.println("\u001B[2J"); 
                        System.out.flush();
                        System.out.println("Saindo do programa...");
                        System.out.println("""
                                   ========================================
                                   || Bem vindo ao Sistema da Biblioteca ||
                                   ========================================""");
                        System.out.println("""
                                        ========================================
                                        ||      1 - Gerenciador de livros     ||
                                        ========================================""");
                        System.out.println("""
                                        ========================================
                                        ||    2 - Gerenciador de usuários     ||
                                        ========================================""");
                        System.out.println("""
                                   ========================================
                                   ||    3 - gerenciador de emprestimos  ||
                                   ========================================""");

                        System.out.println("""
                                        ========================================
                                        ||    0 - para sair do sistema        ||
                                        ========================================""");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        
                }

            } while (escolha2 != 0);
        }
//==========================================================================================================================================//
//=======================================================Menu gerenciador de emprestimo=====================================================//
//==========================================================================================================================================//
        public static void gerenciadorDeEmprestimo(Scanner teclado) {
            int escolha2 = 7;
            do {
                System.out.println("\u001B[2J"); 
                System.out.flush();

                System.out.println("""
                                   ========================================
                                   ||     Gerenciador de emprestimos     ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         1 - Fazer emprestimo       ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||        2 - Editar emprestimo       ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||       3 - Listar emprestimos       ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||         4 - Devolver livro         ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||5 - Listar histórico de Emprestimos ||
                                   ========================================""");
                System.out.println("""
                                   ========================================
                                   ||     0 - para sair do programa      ||
                                   ========================================
                                   """);
                System.out.print("Escolha uma opção: ");
                escolha2 = teclado.nextInt();
                teclado.nextLine();

                switch (escolha2) {
                    case 1:
                        System.out.println("Digite as informações do emprestimo");
                        EmprestimoServico.fazerEmprestimo(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 2:
                        System.out.println("Editar emprestimo");
                        EmprestimoServico.editarEmprestimo(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 3:
                        System.out.println("Listar emprestimos");
                        EmprestimoServico.listarEmprestimos();
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 4:
                        System.out.println("Devolver livro");
                        EmprestimoServico.devolverLivro(teclado);
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 5:
                        System.out.println("Listar histórico de emprestimos");
                        EmprestimoServico.listarHistoricoEmprestimos();
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        break;
                    case 7:
                    
                    case 0:
                        System.out.println("\u001B[2J"); 
                        System.out.flush();
                        System.out.println("Saindo do programa...");
                        System.out.println("""
                                   ========================================
                                   || Bem vindo ao Sistema da Biblioteca ||
                                   ========================================""");
                        System.out.println("""
                                        ========================================
                                        ||      1 - Gerenciador de livros     ||
                                        ========================================""");
                        System.out.println("""
                                        ========================================
                                        ||    2 - Gerenciador de usuários     ||
                                        ========================================""");
                        System.out.println("""
                                   ========================================
                                   ||    3 - gerenciador de emprestimos  ||
                                   ========================================""");

                        System.out.println("""
                                        ========================================
                                        ||    0 - para sair do sistema        ||
                                        ========================================""");
                        
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        System.out.println("\n\nPressione Enter para continuar...");
                        teclado.nextLine();
                        
                }

            } while (escolha2 != 0);
        }
}
