public class Q12 {
    public static void main(String[] args) throws Exception {
        //Ciclo que para quando FIM é digitado
        while (true) {

            //leitura da string
            String frase = MyIO.readLine();
            if (frase.equals("FIM"))
                break;
            else{ 
                
                MyIO.println(cifCesar(frase));
            
            }
        }
    }
    //inicializa o metodo recursivo
    public static String cifCesar(String frase){
        return cifCesar(frase, 0, "");
    }
    //Função que realiza o ciframento
    public static String cifCesar(String frase,int i, String frasecif){

        if(i >=frase.length())
            return frasecif;
        else
        {
            frasecif += (char) ((int)frase.charAt(i)+3);
            return cifCesar(frase, i+1, frasecif);
        }    
     
    }

   
}

