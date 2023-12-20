import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Q11 extends ListaDupla {

    /*
     * II inserir inicio
     * IF inserir fim
     * I* inserir pos
     * RI remover inicio
     * RF remover fim
     * R* remover pos
     */
    public static void main(String[] args) throws Exception {
        
        long startTime = System.currentTimeMillis();
        
        ListaDupla lista = new ListaDupla();

        while (true) {
            String search = MyIO.readLine();
            if (search.equals("FIM"))
                break;
            lista.InserirFim(new Jogador(Integer.parseInt(search), "", 0, 0, "", 0, "", ""));

        }

        lista.quicksort();

        lista.mostrar();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double seconds = (double) elapsedTime / 1000.0;
        Arq.openWrite("matrícula_quicksort3.txt");
        Arq.print("813168 \t"+ lista.comp + " comparacoes \t" + lista.mov + " movimentacoes \t"+seconds+" segundos");
        Arq.close();

    }
}

class ListaDupla extends CelulaDupla {
    // Atributos
    public CelulaDupla primeiro;
    public CelulaDupla ultimo;
    public int comp;
    public int mov;

    // Construtores
    public ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
        comp = 0;
        mov = comp;
    }

    // Metodos de Inserir
    public void InserirFim(Jogador jogador) throws Exception {

        ultimo.prox = new CelulaDupla(jogador);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
        ultimo.jogador.ler();

    }

    // Metodo de Ordenacao
    public void quicksort() {
        quicksort(primeiro.prox, ultimo);
    }

    public void quicksort(CelulaDupla esq, CelulaDupla dir) {
        
            CelulaDupla i = esq, j = dir;
            Jogador pivo = i.jogador;
            while (verificar(i, j)){
                comp++;
                while (i.jogador.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0 ||
                        i.jogador.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0 &&  i.jogador.getNome().compareTo(pivo.getNome()) < 0 )
                    i = i.prox;
                comp++;
                while (j.jogador.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0 ||
                        j.jogador.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0 &&  j.jogador.getNome().compareTo(pivo.getNome()) > 0 )
                    j = j.ant;

                comp++;
                if (verificar(i, j)) {
                    swap(i, j);
                    mov++;
                    i = i.prox;
                    j = j.ant;
                }
            }
            comp++;
            if (verificar2(esq, j))
                quicksort(esq, j);
            comp++;
            if (verificar2(i, dir))
                quicksort(i, dir);
        
    }

    public void swap(CelulaDupla i, CelulaDupla j) {
        Jogador tmp = i.jogador;
        i.jogador = j.jogador;
        j.jogador = tmp;
        tmp = null;
    }

    public boolean verificar(CelulaDupla esq, CelulaDupla dir) {
        // true se i <= j
        if( esq == null || dir == null ) {
                return false;
        }
        boolean resp;
        int i = 0;
        int j = i;
        for(CelulaDupla a = primeiro; a != esq; a = a.prox, i++);
        for(CelulaDupla a = primeiro; a != dir; a = a.prox, j++);
        if(i <= j)
            resp = true;
        else 
            resp = false;

        return resp;
    }

    public boolean verificar2(CelulaDupla esq, CelulaDupla dir) {
        // true se i <= j
        if( esq == null || dir == null ) {
                return false;
        }
        boolean resp;
        int i = 0;
        int j = i;
        for(CelulaDupla a = primeiro; a != esq; a = a.prox, i++);
        for(CelulaDupla a = primeiro; a != dir; a = a.prox, j++);
        if(i < j)
            resp = true;
        else 
            resp = false;
            
        return resp;
    }

    public int tamanho() {

        int count = 0;

        for (CelulaDupla i = primeiro; i != ultimo; i = i.prox)
            count++;

        return count;
    }

    public void mostrar() {
        int i;
        CelulaDupla j = primeiro.prox;
        for (i = 0; i < tamanho(); i++, j = j.prox) {
            System.out.println(j.jogador.tostring());
        }
    }

}

class CelulaDupla extends Jogador {
    public Jogador jogador;
    public CelulaDupla prox;
    public CelulaDupla ant;

    public CelulaDupla() {
        this(new Jogador());
    }

    public CelulaDupla(Jogador jogador) {
        this.jogador = jogador;
        this.ant = this.prox = null;
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
        resp = "[" + id;
        resp += " ## " + nome + " ## " + altura + " ## ";
        resp += peso + " ## " + anoNascimento + " ## ";
        resp += universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento;
        resp += "]";
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
