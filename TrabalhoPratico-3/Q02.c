#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define maxtam 60
#define stringtam 110
#define TAMVET 200

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
void imprimir(Jogador *jogador, int i)
{
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           i, jogador->nome, jogador->altura, jogador->peso,
           jogador->anoNascimento, jogador->universidade,
           jogador->cidadeNascimento, jogador->estadoNascimento);
}


// Ler
void ler(Jogador *a)
{
    FILE *file;
    char linha[200]; // Tamanho máximo de uma linha
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
            continue; // Ignora a primeira linha com cabeçalhos
        }
        int i, j, len;
    len = strlen(linha);

    // Percorra a string e verifique se há duas vírgulas consecutivas
    for (i = 0; i < len - 1; i++) {
        if (linha[i] == ',' && linha[i + 1] == ',') {
            // Desloque todos os caracteres à direita da segunda vírgula
            // para abrir espaço para o espaço em branco
            for (j = len; j > i + 1; j--) {
                linha[j] = linha[j - 1];
            }

            // Insira um espaço em branco no lugar das duas vírgulas
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
                a->anoNascimento = 0; // Defina um valor padrão, se o campo estiver vazio ou nulo
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

// Struct e Construtres
typedef struct Lista{
    Jogador* array;
    int n;
    int capacidade;

}Lista;

Lista listaPadrao(){
    Lista a;
    a.array = (Jogador*)malloc(sizeof(Jogador)*10);
    a.n = 0;
    a.capacidade = 10;
    return a;
}

Lista lista(int tamanho){
    Lista a;
    a.array = (Jogador*)malloc(sizeof(Jogador)*tamanho);
    a.n = 0;
    a.capacidade = tamanho;
    return a;
}

// Metodos de inserir
void InserirInicio(Lista *a, Jogador jogador){

    if(a->n>= a->capacidade){
            printf("Erro ao Insreir!");
        }

        ler(&jogador);

        for(int i = a->n; i > 0; i--){
         a->array[i] = a->array[i-1];
        }

        a->array[0] = jogador;
        a->n++;
}

void Inserir(Lista *a, Jogador jogador, int posicao){

    if(a->n>= a->capacidade || posicao < 0 || posicao > a->capacidade ){
            printf("Erro ao Insreir!");
        }

    ler(&jogador);

    for(int i = a->n; i > posicao; i--){
         a->array[i] = a->array[i-1];
        }

        a->array[posicao] = jogador;
        a->n++;      
}

void InserirFim(Lista *a, Jogador jogador){

    if(a->n>= a->capacidade){
            printf("Erro ao Insreir!");
        }

    ler(&jogador);
    
    a->array[a->n++] = jogador;    
}

// Metodos de Remover
Jogador RemoverInicio(Lista *a){
    
    if(a->n<= 0){
            printf("Erro ao Remover!");
        }
    
    Jogador resp = a->array[0];
    a->n--;

    for(int i=0; i < a->n; i++){
        a->array[i] = a->array[i+1]; 
    }

    return resp;
}

Jogador Remover(Lista *a, int posicao){
    
    if(a->n<= 0 || posicao < 0 || posicao >= a->n){
            printf("Erro ao Remover!");
        }
    
    Jogador resp = a->array[posicao];
    a->n--;

    for(int i= posicao; i < a->n; i++){
        a->array[i] = a->array[i+1]; 
    }

    return resp;
}

Jogador RemoverFim(Lista *a){
    
    if(a->n<= 0){
            printf("Erro ao Remover!");
        }
    
    return a->array[--(a->n)];

}

int main()
{
    
    Lista listajog = lista(200);
    char search[20];
    while(1)
    {
        fgets(search,sizeof(search),stdin);
        if(!strcmp(search,"FIM\n"))
            break;
        Jogador a = novojogadorPadrao();
        setID(&a,atoi(search));
        ler(&a);
        InserirFim(&listajog, a);
    }
    int qtdreg;
    scanf("%d", &qtdreg);
    for(int i = 0; i <= qtdreg; i++){
        int num;
        Jogador a = novojogadorPadrao();
        fgets(search,sizeof(search),stdin);
        char *token = strtok(search, " ");
        if(token[0] == 'I'){
            if(token[1] == 'I'){
                num =atoi(strtok(NULL," "));
                setID(&a, num);
                InserirInicio(&listajog,a);

            }else if(token[1] == 'F'){
                num =atoi(strtok(NULL," "));
                setID(&a, num);
                InserirFim(&listajog,a);

            }else{
                int pos = atoi(strtok(NULL," "));
                num =atoi(strtok(NULL," "));
                setID(&a, num);
                Inserir(&listajog,a, pos);

            }
        }else if(token[0] == 'R'){
            if(token[1] == 'I'){
                a = RemoverInicio(&listajog);
                printf("(R) %s\n", getNome(&a));
            }else if(token[1] == 'F'){
                a = RemoverFim(&listajog);
                printf("(R) %s\n", getNome(&a));
            }else{
                int pos = atoi(strtok(NULL," "));
                a = Remover(&listajog, pos);
                printf("(R) %s\n", getNome(&a));
            }
        }
    }

    for(int i = 0 ; i < listajog.n; i++){
        imprimir(&listajog.array[i], i);
    }




    free(listajog.array);
    
    return 0;
}
