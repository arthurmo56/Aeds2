class Q03 {
    public static void main(String[] args) {

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

    //Função que realiza o ciframento
    public static String cifCesar(String frase){
        
        String frasecif ="";
        /*  
        for que vai pegar o valor do caractere na tabela ascii
        e somar 3 e depois adiciona o caractere da nova posicão
        em outra string
        */
        for(int i=0;i<frase.length();i++)
        {
            int posascii = (int)frase.charAt(i) + 3;
            
            frasecif += (char) posascii;
  
        }
        
        //Retorna frase codificada
        return frasecif;
    }

   
}

