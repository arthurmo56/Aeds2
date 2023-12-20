import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Q13 extends Jogador {
    
    static int comp = 0;
    static int mov = 0;
    public static void main(String args[]){

        long startTime = System.currentTimeMillis();
        Jogador vetor[] = new Jogador[500];
        int n=0;
        while (true) {

            String search = MyIO.readLine();
            if (search.equals("FIM"))
                break;

            vetor[n] = new Jogador();    
            vetor[n].setId(Integer.parseInt(search));
            try {
                vetor[n++].ler();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } 
        }
        sort(vetor, n);
        
        for(int i =0; i < n; i++){
            
            vetor[i].imprimir();
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double seconds = (double) elapsedTime / 1000.0;
        Arq.openWrite("matrícula_mergesort.txt");
        Arq.print("813168 \t"+ comp + " comparacoes \t" + mov + " movimentacoes \t"+seconds+" segundos");
        Arq.close();
        
    }
    public static void sort(Jogador vetor[], int n){
        mergeSort(vetor, 0, n-1);

    }
    public static void mergeSort(Jogador[] vetor, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(vetor, esq, meio);
            mergeSort(vetor, meio + 1, dir);
            merge(vetor, esq, meio, dir);
        }
    }

    public static void merge(Jogador[] vetor, int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        Jogador[] a1 = Arrays.copyOfRange(vetor, esq, esq + n1);
        Jogador[] a2 = Arrays.copyOfRange(vetor, meio + 1, meio + 1 + n2);

        int i = 0, j = 0, k = esq;

        while (i < n1 && j < n2) {
            comp++;
            if (a1[i].getUniversidade().compareTo(a2[j].getUniversidade()) < 0 ||
                (a1[i].getUniversidade().equals(a2[j].getUniversidade()) && a1[i].getNome().compareTo(a2[j].getNome()) <= 0)) {
                vetor[k++] = a1[i++];
                mov++;
            } else {
                vetor[k++] = a2[j++];
                mov++;
            }
        }

        while (i < n1) {
            vetor[k++] = a1[i++];
            mov++;
        }

        while (j < n2) {
            vetor[k++] = a2[j++];
            mov++;
        }
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

    Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento,
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
        String resp = "[";
        resp += id + " ## " + nome + " ## " + altura + " ## ";
        resp += peso + " ## " + anoNascimento + " ## ";
        resp += universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]";
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

                        this.universidade = (campos[4] != null && !campos[4].isEmpty()) ? campos[4] : "nao informado"; // Universidade do jogador

                        this.anoNascimento = Integer.parseInt(campos[5]); // Ano de nascimento do jogador

                        this.cidadeNascimento = (campos.length > 6 && campos[6] != null && !campos[6].isEmpty()) ? campos[6] : "nao informado"; // Cidade de nascimento do jogador

                        this.estadoNascimento = (campos.length > 7 && campos[7] != null && !campos[7].isEmpty()) ? campos[7] : "nao informado"; // Estado de nascimento do jogador

                        break; // para de ler o arquivo assim que encontrar o jogador

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
