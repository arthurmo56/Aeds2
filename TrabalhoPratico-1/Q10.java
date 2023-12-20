public class Q10 {
    public static void main(String[] args) throws Exception {
        // Ciclo que so encerra quado "FIM" é digitado
        while (true) {

            String frase = MyIO.readLine();
            if (frase.equals("FIM"))
                break;
            else 
                System.out.println(epalindromo(frase)?"SIM":"NAO");
        }
    }

    //inicializa o metodo recursivo
    public static boolean epalindromo(String frase){
        return epalindromo(frase, 0);
    }
    // Verifica se a palavra é palindromo e retorna true ou false
    public static boolean epalindromo(String frase, int i) {
        
        //condição de parada
        if(i > frase.length()/2) {
            return true;
        }else if(frase.charAt(i) == frase.charAt(frase.length()-i-1)){
            return epalindromo(frase, i+1);
        }else 
            return false;
        
      
    }
}