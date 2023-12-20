import java.util.Random;

public class Q04 {
    public static void main(String[] args) throws Exception {
        
        //Criando um gerador com a seed 4
        Random gerador = new Random();
        gerador.setSeed(4);

        // Ciclo que para quando FIM é digitado
        while (true) {
            // leitura da string
            String frase = MyIO.readLine();
            if (frase.equals("FIM"))
                break;
            else {
                MyIO.println(randomizador(frase, gerador));

            }
        }
    }

    //metodo que vai realizar a substituição das letras
    public static String randomizador(String frase, Random gerador) {

        char letra = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        char letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

        String randomfrase = "";
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == letra)
                randomfrase += letra2;
            else
                randomfrase += frase.charAt(i);
        }
        return randomfrase;

    }

}