import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Q09 extends Jogador {
    
    static int comp = 0;
    static int mov = 0;
    public static void main(String args[]){

        long startTime = System.currentTimeMillis();
        Jogador vetor[] = new Jogador[700];
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
        vetor = sort(vetor, n);
        
        for(int i=1;i<n;i++){
            Jogador tmp = vetor[i];
            int j = i - 1; 
            while(j>-1 && (tmp.getAltura() == vetor[j].getAltura() && tmp.getNome().compareTo(vetor[j].getNome()) < 0)){ 
                comp++;    
                vetor[j+1] = vetor[j];
                mov++;
                j--;
            }
            vetor[j+1]=tmp;
            mov++;
        }
        
        for(int i =0; i < n; i++){
            
            vetor[i].imprimir();
        }
        
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double seconds = (double) elapsedTime / 1000.0;
        Arq.openWrite("matrícula_heapsort.txt");
        Arq.print("813168 \t"+ comp + " comparacoes \t" + mov + " movimentacoes \t"+seconds+" segundos");
        Arq.close();
        
    }
    public static Jogador[] sort(Jogador array[], int n){
        Jogador tmp[] = new Jogador[n+1];
      for(int i = 0; i < n; i++){
         tmp[i+1] = array[i];
      }
      array = tmp;

      for(int tamheap=2;tamheap <= n;tamheap++){
        construir(tamheap,array);
      }

      int tamheap = n;
      while(tamheap > 1){
        mov+=3;
        swap(array,1,tamheap--);
        reconstruir(tamheap, tmp);
      }

      tmp = array;
      array = new Jogador[n];
      for(int i = 0;i < n; i++){
        mov++;
        array[i]= tmp[i+1];
      }
      return array;  
    }
    public static void construir(int tamheap, Jogador vetor[]){
        for(int i=tamheap;i>1 && vetor[i].getAltura() > vetor[i/2].getAltura();i/=2){
            mov+=3;
            swap(vetor,i, i/2);
            comp++;
        }
        comp++;
    }
    public static void reconstruir(int tamheap, Jogador vetor[]){
        int i = 1;
        while(i <=(tamheap/2)){
            int filho = getMaiorFilho(i, tamheap, vetor);
            comp++;
            if(vetor[i].getAltura() < vetor[filho].getAltura()){
                mov+=3;
                swap(vetor,i, filho);
                i= filho;
            }else 
                i = tamheap;
        }
    }
    public static int getMaiorFilho(int i, int tamheap, Jogador vetor[]){
        int maior;
        comp++;
        if(2*i == tamheap || vetor[2*i].getAltura()>vetor[2*i+1].getAltura()){
            maior = 2*i;
        }else
            maior = 2*i +1;
        return maior;    
    }
    
    public static void swap(Jogador vetor[],int a,int b){
        Jogador tmp = vetor[a];
        vetor[a]=vetor[b];
        vetor[b]=tmp;

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