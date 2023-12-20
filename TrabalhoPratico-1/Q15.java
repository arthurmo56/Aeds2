public class Q15 {
    public static void main(String args[]) {
        
        //Ciclo que acaba com "FIM"
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

    // inicializa o metodo recursivo
    public static boolean vogal(String s) {
        s = s.toLowerCase();
        return vogal(s, 0);
    }

    public static boolean vogal(String s, int i) {

        if (i >= s.length())
            return true;
        else if (s.charAt(i) != 'a' && s.charAt(i) != 'e' && s.charAt(i) != 'i' &&
                s.charAt(i) != 'o' && s.charAt(i) != 'u')
            return false;
        else
            return vogal(s, i + 1);

    }

    // inicializa o metodo recursivo
    public static boolean consoante(String s) {
        s = s.toLowerCase();
        return consoante(s, 0);
    }

    public static boolean consoante(String s, int i) {

        if (i >= s.length())
            return true;
        else if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' ||
                s.charAt(i) == 'o' || s.charAt(i) == 'u' || (s.charAt(i) >= 48 && s.charAt(i) <= 57))
            return false;
        else
            return consoante(s, i + 1);

    }

    // inicializa o metodo recursivo
    public static boolean numint(String s) {
        return numint(s, 0);
    }

    public static boolean numint(String s, int i) {

        if (i >= s.length())
            return true;
        else if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57))
            return false;
        else
            return numint(s, i + 1);

    }

    // inicializa o metodo recursivo
    public static boolean numreal(String s) {
        return numreal(s, 0, 0);
    }

    //count é o numero de pontos ou virgulas que tem na string
    public static boolean numreal(String s, int i, int count) {
       
        if (i >= s.length())
            return true;
        else if(count > 1|| !((s.charAt(i) >= 48 && s.charAt(i) <= 57) ||
                 s.charAt(i) == ',' || s.charAt(i) == '.'))
            return false;
        else if(s.charAt(i) == ',' || s.charAt(i) == '.')
            return numreal(s, i+1, count+1);        
        else
            return numreal(s, i+1, count);

    }
}
