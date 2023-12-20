import java.io.*;
import java.net.URL;
import java.net.URLConnection;

class Q07 {
    public static void main(String[] args) {
        
        //Ciclo que encerra com "FIM"  
        while(true){
          String nome = MyIO.readLine();
          if(nome.equals("FIM"))
          break;
          String urlString = MyIO.readLine();
          String texto = URLopener(urlString);
          MyIO.println(verificadorletras(nome, texto));
          }
         
    }

    public static String URLopener(String urlString) {
        try {
            // Cria uma URL com a string fornecida
            URL url = new URL(urlString);
    
            // Abre uma conexão com a URL
            URLConnection connection = url.openConnection();
    
            // Cria um BufferedReader para ler o conteúdo da página
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    
            // Lê o conteúdo da página linha por linha
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
    
            // Fecha o BufferedReader
            reader.close();
    
            // Retorna o conteúdo da página como uma string
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            // Caso haja algum erro durante a leitura da página, retorne nulo e imprima o erro
            return null;
        }
    }

    // Compara cada caractere do HTML com as letras e palavras desejadas
    public static String verificadorletras(String nome, String texto) {
        
        //contadores
        int a = 0, e = 0, i = 0, o = 0, u = 0, aAg = 0, eAg = 0, iAg = 0, oAg = 0, uAg = 0, aCr = 0, eCr = 0, iCr = 0,
                aTil = 0, oTil = 0, aCx = 0, eCx = 0, oCx = 0, consoante = 0,
                br = 0, table = 0;
        
        for (int count = 0; count < texto.length(); count++) {
            char letra = texto.charAt(count);

            if ((letra >= 'b' && letra <= 'z')
                    && (letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u')) {
                consoante++;
            } else if (letra == 'e') {
                e++;
            } else if (letra == 'a') {
                a++;
            } else if (letra == 'i') {
                i++;
            } else if (letra == 'o') {
                o++;
            } else if (letra == 'u') {
                u++;
            } else if (letra == 227) {
                aTil++;
            } else if (letra == 245) {
                oTil++;
            } else if (letra == 225) {
                aAg++;
            } else if (letra == 233) {
                eAg++;
            } else if (letra == 237) {
                iAg++;
            } else if (letra == 243) {
                oAg++;
            } else if (letra == 250) {
                uAg++;
            } else if (letra == 226) {
                aCx++;
            } else if (letra == 234) {
                eCx++;
            } else if (letra == 244) {
                oCx++;
            } else if (letra == 224) {
                aCr++;
            } else if (letra == 232) {
                eCr++;
            } else if (letra == 236) {
                iCr++;
            } else if (letra == 60) {
                if (count + 3 < texto.length()) {
                    String tag = texto.substring(count, count + 4);
                    if (tag.equals("<br>")) {
                        br++;
                        consoante -= 2;
                    }
                }
                if (count + 6 < texto.length()) {
                    String tag = texto.substring(count, count + 7);
                    if (tag.equals("<table>")) {
                        table++;
                        a--;
                        e--;
                        consoante -=3;
                    }
                }
                
            }

        }
        String resp = "a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") á(" + aAg + ") é(" + eAg
                + ") í(" + iAg + ") ó(" + oAg + ") ú(" + uAg + ") à(" + aCr + ") è(" + eCr + ") ì(" + iCr +
                ") ò(0) ù(0) ã(" + aTil + ") õ(" + oTil + ") â(" + aCx + ") ê(" + eCx + ") î(0) ô("
                + oCx + ") û(0) consoante(" + consoante + ") <br>(" + br + ") <table>(" + table + ") " + nome;
        return resp;
    }

}
