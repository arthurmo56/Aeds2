
class Q01 {
    public static void main(String[] args) {

        // Ciclo que so encerra quado "FIM" é digitado
        while (true) {

            String frase = MyIO.readLine();
            if (frase.equals("FIM"))
                break;
            else if (epalindromo(frase))
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
        }
    }

    // Verifica se a palavra é palindromo e retorna true ou false
    public static boolean epalindromo(String frase) {

        // Verifica se as palavras de posições opostas são iguais
        int esq = 0, dir = frase.length() - 1;
        while (esq < dir) {
            if (frase.charAt(esq) != frase.charAt(dir))
                return false;

            esq++;
            dir--;
        }
        return true;
    }
}