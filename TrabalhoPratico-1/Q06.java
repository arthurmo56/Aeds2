class Q06 {
    public static void main(String[] args) throws Exception {

        // Ciclo que acaba com "FIM"
        while (true) {
            String frase = MyIO.readLine();
            if (frase.equals("FIM"))
                break;
            MyIO.print((vogal(frase) ? "SIM " : "NAO "));
            MyIO.print((consoante(frase) ? "SIM " : "NAO "));
            MyIO.print((numint(frase) ? "SIM " : "NAO "));
            MyIO.println((numreal(frase) ? "SIM" : "NAO"));
        }
    }

    // metodo que vai verificar se é vogal
    public static boolean vogal(String s) {
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'a' && s.charAt(i) != 'e' && s.charAt(i) != 'i' &&
                    s.charAt(i) != 'o' && s.charAt(i) != 'u') {
                return false;
            }

        }
        return true;
    }

    // metodo que vai verificar se é consoante
    public static boolean consoante(String s) {
        s = s.toLowerCase();

        // verifica se não é vogal
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' ||
                    s.charAt(i) == 'o' || s.charAt(i) == 'u' || (s.charAt(i) >= 48 && s.charAt(i) <= 57))
                return false;
        }
        return true;
    }

    // metodo que vai verificar se é numero inteiro
    public static boolean numint(String s) {
        for (int i = 0; i < s.length(); i++) {
            // verifica se o caractere não é numero
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57))
                return false;
        }
        return true;
    }

    // metodo que vai verificar se é numero real
    public static boolean numreal(String s) {
        // contador dos pontos e virgulas
        int count = 0;

        // for que vai verificar se o caractere é numero, ponto ou virgula
        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i) >= 48 && s.charAt(i) <= 57) ||
                    s.charAt(i) == ',' || s.charAt(i) == '.'))
                return false;
            // se for ponto ou virgula incrementa o contador
            if (s.charAt(i) == ',' || s.charAt(i) == '.')
                count++;
        }
        // se o contador for menor ou igual a 1 retorna true
        if (count <= 1)
            return true;
        else
            return false;

    }
}
