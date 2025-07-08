## Estrutura de pastas

O espaço de trabalho contém duas pastas por padrão, onde:

- `src`: a pasta para manter os códigos-fonte
- `lib`: a pasta para manter as dependências

Além disso, há as subpastas de 'src' onde há as classes e a sua main, sendo elas:
- `aplicacao`: aonde se iniciará o algoritmo, contendo o arquivo Aplicacao.java
- `modelo`: aonde haverá os objetos principais do algoritmo, sendo eles Emprestimo.java, Livro.java e Usuario.java, neles vão estar guardados alguns metódos e o propio objeto e seus atributos
- `Repositorio`: aonde haverá o "Sistema de banco de dados" simulado pelo arquivo BibliotecaRepositorio.java
- `servico`: aonde haverá os classes resposaveis por cadastro, exclusão, edição de nformações, dos livros, usuarios e emprestimo, estas são as classes que conversam com arquivos da pasta 'modelo', e cadastram diretamente no arquivo 'BibliotecaRepostiorio.java' da pasta 'repositorio'.



