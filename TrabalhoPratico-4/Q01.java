import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Q01{

    public static void main(String[] args) throws Exception {
        
        long startTime = System.currentTimeMillis();

        ArvoreBinaria a = new ArvoreBinaria();

        while (true) {
            String search = MyIO.readLine();
            if (search.equals("FIM"))
                break;
            Jogador jogador = new Jogador(Integer.parseInt(search), "", 0, 0, "", 0, "", "");
            jogador.ler();    
            a.inserir(jogador);
        }
        while (true) {
            String search = MyIO.readLine();
            if (search.equals("FIM"))
                break;
            a.pesquisar(search);
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double seconds = (double) elapsedTime / 1000.0;
        Arq.openWrite("matrícula_arvoreBinaria.txt");
        Arq.print("813168 \t"+ a.comp + " comparacoes \t" + seconds +" segundos");
        Arq.close();

        
    }
}

class ArvoreBinaria {
    private No raiz;
    public int comp;

    public ArvoreBinaria() {
        this.raiz = null;
        comp = 0;
    }

    public void inserir(Jogador jogador) throws Exception {
        raiz = inserir(jogador, raiz);
    }

    private No inserir(Jogador jogador, No i) throws Exception {
        if (i == null) {
            comp++;
            i = new No(jogador);

        } else if (jogador.getNome().compareTo(i.jogador.getNome()) < 0) {
            comp+=2;
            i.esq = inserir(jogador, i.esq);

        } else if (jogador.getNome().compareTo(i.jogador.getNome()) > 0) {
            comp+=3;
            i.dir = inserir(jogador, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public boolean pesquisar(String nome) {
        System.out.print(nome + " raiz ");
        return pesquisar(nome, raiz);
    }

    private boolean pesquisar(String nome, No i) {
        boolean resp;
        if (i == null) {
            comp++;
            resp = false;
            System.out.println("NAO");

        } else if (nome.compareTo(i.jogador.getNome()) == 0) {
            comp+=2;
            resp = true;
            System.out.println("SIM");

        } else if (nome.compareTo(i.jogador.getNome()) < 0) {
            comp+=3;
            System.out.print("esq ");
            resp = pesquisar(nome, i.esq);

        } else {
            System.out.print("dir ");
            resp = pesquisar(nome, i.dir);
        }
        return resp;

    }

}

class No{

    public Jogador jogador;
    public No esq, dir;

    public No(Jogador jogador) throws Exception{
        this(jogador, null, null);
    }

    public No(Jogador jogador, No esq, No dir) throws Exception{
        this.jogador = jogador;
        this.dir = dir;
        this.esq = esq;
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

