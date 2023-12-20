import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Q06 extends Pilha {

    /*
     * II inserir inicio
     * IF inserir fim
     * I* inserir pos
     * RI remover inicio
     * RF remover fim
     * R* remover pos
     */
    public static void main(String[] args) throws Exception {
        Pilha array = new Pilha();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String search = sc.nextLine();
            if (search.equals("FIM"))
                break;
            array.Inserir(new Jogador(Integer.parseInt(search), "", 0, 0, "", 0, "", ""));

        }

        int qtd_reg = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < qtd_reg; i++) {
            String registo = sc.nextLine();
            String[] campos = registo.split(" ");
            if (registo.charAt(0) == 'I') {
                
                array.Inserir(new Jogador(Integer.parseInt(campos[1]), "", 0, 0, "", 0, "", ""));
            }
            if (registo.charAt(0) == 'R') {

                System.out.println("(R) " + array.Remover().getNome());   
            }
        }
        sc.close();
        array.mostrar();
        
    }
}

class Pilha extends Celula {
    // Atributos
    public Celula topo;

    // Construtores
    public Pilha() {
        topo = null;
    }

    // Metodos de Inserir
    public void Inserir(Jogador jogador) throws Exception {

        Celula tmp = new Celula(jogador);
        tmp.jogador.ler();
        tmp.prox = topo;
        topo = tmp;
        tmp = null;

    }
    // Metodos de Remover
    public Jogador Remover() throws Exception {

        if (topo == null) {
            throw new Exception("Pilha vazia");
        }
        
        Jogador resp = topo.jogador;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;

        return resp;
    }
    public int tamanho(){
        int resp;
        Celula i = topo;
        for(resp = 0; i != null; i = i.prox, resp++);
        return resp;
    }

    public void mostrar() {
        
        int i;
        Celula c;
        Celula ultimo = null;
        for(i = 0; i < tamanho(); i++ ){
            for(c = topo;c.prox != ultimo; c = c.prox);
            System.out.println("[" + i + "]" + c.jogador.tostring());
            ultimo = c;
        }
        
    }
}

class Celula extends Jogador {
    public Jogador jogador;
    public Celula prox;

    public Celula() {
        this(new Jogador());
    }

    public Celula(Jogador jogador) {
        this.jogador = jogador;
        prox = null;
    }

}

class Jogador {

    // Atributos
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    // Construtores
    Jogador() {
        this(-1, "", 0, 0, "", 0, "", "");
    }

    Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
            String cidadeNascimento,
            String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    // Clone
    protected Jogador clone() throws CloneNotSupportedException {
        Jogador novo = new Jogador();
        novo.id = this.id;
        novo.nome = this.nome;
        novo.altura = this.altura;
        novo.peso = this.peso;
        novo.universidade = this.universidade;
        novo.anoNascimento = this.anoNascimento;
        novo.cidadeNascimento = this.cidadeNascimento;
        novo.estadoNascimento = this.estadoNascimento;
        return novo;
    }

    // Imprimir
    public void imprimir() {

        System.out.println(tostring());
    }

    public String tostring() { // Transforma no formato desejado
        String resp = "";
        // resp = "[" + id;
        resp += " ## " + nome + " ## " + altura + " ## ";
        resp += peso + " ## " + anoNascimento + " ## ";
        resp += universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + " ##";
        // resp = "]";
        return resp;
    }

    // Ler
    public void ler() throws Exception {
        try {
            String nomeArquivo = "/tmp/players.csv";
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            boolean primeiraLinha = true; // Flag para controlar a primeira linha

            while ((linha = bufferedReader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; // Ignora a primeira linha com cabeçalhos
                }

                String[] campos = linha.split(",");
                int id = 0;
                try {
                    id = Integer.parseInt(campos[0]);

                    if (id == this.id) {
                        // O jogador com o ID desejado foi encontrado

                        this.nome = campos[1]; // Nome do jogador

                        this.altura = Integer.parseInt(campos[2]); // Altura do jogador

                        this.peso = Integer.parseInt(campos[3]); // Peso do jogador

                        this.universidade = (campos[4] != null && !campos[4].isEmpty()) ? campos[4]
                                : "nao informado"; // Universidade
                                                   // do
                                                   // jogador

                        this.anoNascimento = Integer.parseInt(campos[5]); // Ano de nascimento do jogador

                        this.cidadeNascimento = (campos.length > 6 && campos[6] != null && !campos[6].isEmpty())
                                ? campos[6]
                                : "nao informado"; // Cidade de nascimento do jogador

                        this.estadoNascimento = (campos.length > 7 && campos[7] != null && !campos[7].isEmpty())
                                ? campos[7]
                                : "nao informado"; // Estado de nascimento do jogador

                        break; // Para de ler o arquivo assim que encontrar o jogador

                    }

                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter um número: " + e.getMessage());
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

}
