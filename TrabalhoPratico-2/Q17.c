#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define maxtam 60
#define stringtam 110
#define tamvetor 1000


// Atributos
typedef struct Jogador
{

    int id;
    char nome[maxtam];
    int altura;
    int peso;
    char universidade[maxtam];
    int anoNascimento;
    char cidadeNascimento[maxtam];
    char estadoNascimento[maxtam];

} Jogador;

// Construtores
Jogador novojogador(int id, char nome[], int altura, int peso, char universidade[], int anoNascimento, char cidadeNascimento[],
                    char estadoNascimento[])
{
    Jogador novo;
    novo.id = id;
    strcpy(novo.nome,nome);
    novo.altura = altura;
    novo.peso = peso;
    strcpy(novo.universidade,universidade);
    novo.anoNascimento = anoNascimento;
    strcpy(novo.cidadeNascimento,cidadeNascimento);
    strcpy(novo.estadoNascimento,estadoNascimento);
    return novo;
}
Jogador novojogadorPadrao()
{
    return novojogador(-1, "", 0, 0, "", 0, "", "");
}
// Setters
void setID(Jogador *a, int id)
{
    a->id = id;
}
void setNome(Jogador *a, char nome[])
{
    strcpy(a->nome,nome);
}
void setAltura(Jogador *a, int altura)
{
    a->altura = altura;
}
void setPeso(Jogador *a, int peso)
{
    a->peso = peso;
}
void setUniversidade(Jogador *a, char universidade[])
{
    strcpy(a->universidade,universidade);
}
void setAno(Jogador *a, int ano)
{
    a->anoNascimento = ano;
}
void setCidade(Jogador *a, char cidade[])
{
    strcpy(a->cidadeNascimento,cidade);
}
void setEstado(Jogador *a, char estado[])
{
    strcpy(a->estadoNascimento,estado);
}

// Getters
int getId(Jogador *a)
{
    return a->id;
}
char* getNome(Jogador *a)
{
    return a->nome;
}
int getAltura(Jogador *a)
{
    return a->altura;
}
int getPeso(Jogador *a)
{
    return a->peso;
}
char* getUniversidade(Jogador *a)
{
    return a->universidade;
}
int getAno(Jogador *a)
{
    return a->anoNascimento;
}
char* getCidade(Jogador *a)
{
    return a->cidadeNascimento;
}
char* getEstado(Jogador *a)
{
    return a->estadoNascimento;
}

// Clone
Jogador clone(Jogador *a)
{
    Jogador copia;
    copia.id = a->id;
    strcpy(copia.nome,a->nome);
    copia.altura = a->altura;
    copia.peso = a->peso;
    strcpy(copia.universidade,a->universidade);
    copia.anoNascimento = a->anoNascimento;
    strcpy(copia.cidadeNascimento,a->cidadeNascimento);
    strcpy(copia.estadoNascimento,a->estadoNascimento);
    return copia;
}

// Imprimir
void imprimir(Jogador *jogador)
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogador->id, jogador->nome, jogador->altura, jogador->peso,
           jogador->anoNascimento, jogador->universidade,
           jogador->cidadeNascimento, jogador->estadoNascimento);
}


// Ler
void ler(Jogador *a)
{
    FILE *file;
    char linha[200]; // Tamanho m�ximo de uma linha
    char *nomeArquivo = "/tmp/players.csv";

    file = fopen(nomeArquivo, "r");

    if (file == NULL)
    {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    int primeiraLinha = 1; // Flag para controlar a primeira linha

    while (fgets(linha, sizeof(linha), file) != NULL)
    {
        if (primeiraLinha)
        {
            primeiraLinha = 0;
            continue; // Ignora a primeira linha com cabe�alhos
        }
        int i, j, len;
        len = strlen(linha);

        // Percorra a string e verifique se h� duas v�rgulas consecutivas
        for (i = 0; i < len - 1; i++)
        {
            if (linha[i] == ',' && linha[i + 1] == ',')
            {
                // Desloque todos os caracteres � direita da segunda v�rgula
                // para abrir espa�o para o espa�o em branco
                for (j = len; j > i + 1; j--)
                {
                    linha[j] = linha[j - 1];
                }

                // Insira um espa�o em branco no lugar das duas v�rgulas
                linha[i + 1] = ' ';

                // Aumente o tamanho da string
                len++;
            }
        }


        char *token;
        int id;

        linha[strcspn(linha, "\n\r")] = '\0';

        token = strtok(linha, ",");
        id = atoi(token);

        if (id == a->id)
        {
            // O jogador com o ID desejado foi encontrado

            token = strtok(NULL, ","); // Nome do jogador
            strcpy(a->nome, token);

            token = strtok(NULL, ","); // Altura do jogador
            a->altura = atoi(token);

            token = strtok(NULL, ","); // Peso do jogador
            a->peso = atoi(token);

            token = strtok(NULL, ","); // Universidade do jogador
            if (token != NULL && strcmp(token," ") && strlen(token) > 0)
            {
                strcpy(a->universidade, token);
            }
            else
            {
                strcpy(a->universidade, "nao informado");
            }
            token = strtok(NULL, ","); // Ano de nascimento do jogador
            if (token != NULL && strlen(token) > 0)
            {
                a->anoNascimento = atoi(token);
            }
            else
            {
                a->anoNascimento = 0; // Defina um valor padr�o, se o campo estiver vazio ou nulo
            }

            token = strtok(NULL, ","); // Cidade de nascimento do jogador
            if (token != NULL && strcmp(token," ") && strlen(token) > 0)
            {
                strcpy(a->cidadeNascimento, token);

            }
            else
            {
                strcpy(a->cidadeNascimento, "nao informado");
            }

            token = strtok(NULL, ","); // Estado de nascimento do jogador
            if (token != NULL && strcmp(token," ") && strlen(token) > 0)
            {
                strcpy(a->estadoNascimento, token);

            }
            else
            {
                strcpy(a->estadoNascimento, "nao informado");
            }

            break; // para de ler o arquivo assim que encontrar o jogador
        }
    }

    fclose(file);
}

// Swap para a ordena��o
void swap(Jogador *a, Jogador *b)
{

    Jogador tmp = *a;
    *a = *b;
    *b = tmp;
}

int comparar(int pos, char nome1[],char nome2[])
{
    if(strcmp(nome1,"nao informado")==0)
        return 2;

    if(strcmp(nome2,"nao informado")==0)
        return 1;

    else if(pos>= strlen(nome1)||pos>= strlen(nome2))
    {

        return (strlen(nome1)>strlen(nome2))? 1 : 2;
    }

    else if(pos+1>= strlen(nome1) && pos+1>= strlen(nome2) && nome1[pos]==nome2[pos])
    {

        return 0;
    }

    if(nome1[pos]<nome2[pos])
    {
        return 1;
    }

    else if(nome1[pos]>nome2[pos])
    {

        return 2;
    }
    else
    {
        return comparar(pos+1,nome1,nome2);
    }
}


int comparar1(char nome1[], char nome2[])
{
    return comparar(0,nome1,nome2);
}

void construir(Jogador *array, int tamHeap){
    for(int i = tamHeap; i > 1 && (array[i].altura > array[i/2].altura|| 
        (array[i].altura == array[i/2].altura && strcmp(array[i].nome,array[i/2].nome)>0)); i /= 2){
        swap(&array[i], &array[i/2]);
    }
}

int getMaiorFilho(Jogador *array, int i, int tamHeap){
    int filho;
    if (2*i == tamHeap || array[2*i].altura > array[2*i+1].altura || 
        (array[2*i].altura == array[2*i+1].altura && strcmp(array[2*i].nome,array[2*i+1].nome)>0)){
        filho = 2*i;
    } else {
        filho = 2*i + 1;
    }
    return filho;
}

void reconstruir(Jogador *array, int tamHeap){
    int i = 1;
    while(i <= (tamHeap/2)){
        int filho = getMaiorFilho(array, i, tamHeap);
        if(array[i].altura <= array[filho].altura){
            swap(&array[i], &array[filho]);
            i = filho;
        }else{
            i = tamHeap;
        }
    }
}

void heapsort(Jogador *array, int n, int k) {
    //Alterar o vetor ignorando a posicao zero
    Jogador arrayTmp[n+1];
    for(int i = 0; i < n; i++){
        arrayTmp[i+1] = array[i];
    }

    //Contrucao do heap
    for(int tamHeap = 2; tamHeap <= k; tamHeap++){
        construir(arrayTmp, tamHeap);
    }
    
    for(int i=k+1; i<=n;i++){
        if(arrayTmp[i].altura<arrayTmp[1].altura){
            swap(&arrayTmp[i],&arrayTmp[1]);
            reconstruir(arrayTmp, k);
        }
    }    
    //Ordenacao propriamente dita
    int tamHeap = k;
    while(tamHeap > 1){
        swap(&arrayTmp[1], &arrayTmp[tamHeap--]);
        reconstruir(arrayTmp, tamHeap);
    }

    //Alterar o vetor para voltar a posicao zero
    for(int i = 0; i < n; i++){
        array[i] = arrayTmp[i+1];
    }
}

int main()
{
    int k = 10;
    Jogador vetor[tamvetor];
    int n =0;
    char search[10];

    // Preenchendo o vetor
    while(1)
    {

        fgets(search, sizeof(search), stdin);
        if(!strcmp(search,"FIM\n"))
            break;
        if(n>=tamvetor)
        {
            printf("vetor cheio");
            return 1;
        }
        vetor[n] = novojogadorPadrao();
        setID(&vetor[n], atoi(search));
        ler(&vetor[n]);
        n++;
    }
    heapsort(vetor, n, k);
    for(int i=0; i<k; i++)
    {
        imprimir(&vetor[i]);
    }
    return 0;

}