import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

class Q07 {

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();

        Hash h = new Hash();

        while (true) {
            String search = MyIO.readLine();
            if (search.equals("FIM"))
                break;
            Jogador jogador = new Jogador(Integer.parseInt(search), "", 0, 0, "", 0, "", "");
            jogador.ler();
            h.inserir(jogador);
        }
        while (true) {
            String search = MyIO.readLine();
            if (search.equals("FIM"))
                break;
            Jogador jogador = new Jogador(-1, search, 0, 0, "", 0, "", "");
            jogador.ler();
            System.out.println(h.pesquisar(jogador) ? jogador.getNome() + " SIM" : jogador.getNome() + " NAO");
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double seconds = (double) elapsedTime / 1000.0;
        Arq.openWrite("matrícula_hashReserva.txt");
        Arq.print("813168 \t" + h.comp + " comparacoes \t" + seconds + " segundos");
        Arq.close();

    }
}

class Hash {
    Jogador tabela[];
    int m1, m2, m, reserva;
    int comp;
    final int NULO = -1;

    public Hash() {
        this(21, 9);
    }

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Jogador[this.m];
        this.comp = 0;
        for (int i = 0; i < m1; i++) {
            tabela[i] = null;
        }
        reserva = 0;
    }

    public int h(Jogador jogador) {
        return jogador.getAltura() % m1;
    }

    public boolean inserir(Jogador jogador) {
        boolean resp = false;
        if (jogador.getId() != NULO) {
            int pos = h(jogador);
            if (tabela[pos] == null) {
                comp++;
                tabela[pos] = jogador;
                resp = true;
            } else if (reserva < m2) {
                comp+=2;
                tabela[m1 + reserva] = jogador;
                reserva++;
                resp = true;
            }
        }
        comp++;
        return resp;
    }

    public boolean pesquisar(Jogador jogador) {
        boolean resp = false;
        int pos = h(jogador);
        if (tabela[pos] == jogador) {
            resp = true;
        } else if (tabela[pos] != null) {
            comp++;
            for (int i = 0; i < reserva; i++) {
                comp++;
                if (tabela[m1 + i] == jogador) {
                    resp = true;
                    i = reserva;
                }
            }
        }
        comp++;
        return resp;
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
                String nome = "";
                try {
                    id = Integer.parseInt(campos[0]);
                    nome = campos[1];

                    if (id == this.id || nome == this.nome) {
                        // O jogador com o ID desejado foi encontrado

                        this.id = Integer.parseInt(campos[0]);

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
