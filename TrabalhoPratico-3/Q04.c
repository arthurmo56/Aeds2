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
void imprimir(Jogador *jogador)
{
    printf("## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           jogador->nome, jogador->altura, jogador->peso,
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
typedef struct FilaCircular{
    Jogador* array;
    int primeiro, ultimo;
    int tamanho;

}FilaCircular;

FilaCircular FilaCircularPadrao(){
    FilaCircular a;
    a.array = (Jogador*)malloc(sizeof(Jogador)*6);
    a.primeiro = a.ultimo = 0;
    a.tamanho = 6;
    return a;
}

FilaCircular filaCircular(int tamanho){
    FilaCircular a;
    a.array = (Jogador*)malloc(sizeof(Jogador)*(tamanho+1));
    a.primeiro = a.ultimo = 0;
    a.tamanho = tamanho+1;
    return a;
}

// Metodos de inserir
void Inserir(FilaCircular *a, Jogador jogador){

    if(((a->ultimo+1) % a->tamanho) == a->primeiro){
            a->primeiro = (a->primeiro+1)% a->tamanho;
        }

    ler(&jogador);
    a->array[a->ultimo] = jogador;
    a->ultimo = (a->ultimo+1) % a->tamanho;
}

// Metodos de Remover
Jogador Remover(FilaCircular *a){

    if(a->primeiro == a->ultimo){
            printf("Erro ao Remover!");
    }

    Jogador resp = a->array[a->primeiro];
    a->primeiro = (a->primeiro+1)% a->tamanho;
    return resp;
}

int calcMedia(FilaCircular *a){

    int i = a->primeiro, count = 0;
    float media = 0;
    while (i != a->ultimo) {
        media += getAltura(&a->array[i]);
        i = (i+1)% a->tamanho;
        count ++;
    }
    media /= count;
    if(media - (int)media > 0.4){
        media += 1;
    }
    return (int)media;
}

void mostrar(FilaCircular *a){
    int i = a->primeiro;
    int count = 0;
    while(i != a->ultimo){
        printf("[%d] ", count);
        imprimir(&a->array[i]);
        i = (i + 1) % a->tamanho;
        count++;
    }
}

int main()
{

    FilaCircular jog = FilaCircularPadrao();
    char search[10];
    int media;
    while(1)
    {
        fgets(search,sizeof(search),stdin);
        if(!strcmp(search,"FIM\n"))
            break;
        Jogador a = novojogadorPadrao();
        setID(&a,atoi(search));
        Inserir(&jog, a);
        media = calcMedia(&jog);
        printf("%d\n",media);
    }
    int qtdreg;
    scanf("%d", &qtdreg);
    for(int i = 0; i <= qtdreg; i++){
        int num;
        Jogador a = novojogadorPadrao();
        fgets(search,sizeof(search),stdin);
        char *token = strtok(search, " ");
        if(token[0] == 'I'){
            num =atoi(strtok(NULL," "));
            setID(&a, num);
            Inserir(&jog,a);
            media = calcMedia(&jog);
            printf("%d\n",media);


        }else if(token[0] == 'R'){
            a = Remover(&jog);
            printf("(R) %s\n", getNome(&a));

        }
    }
    mostrar(&jog);

    free(jog.array);

    return 0;
}
