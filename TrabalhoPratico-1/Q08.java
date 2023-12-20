import java.io.*;

public class Q08 {
    public static void main(String[] args) {

        try {

            File arquivoExistente = new File("valores.txt");
            if (arquivoExistente.exists()) {
                // Exclua o arquivo existente para sobrescrevê-lo
                arquivoExistente.delete();
            }

            // Leitura do número de valores reais
            int n = MyIO.readInt();

            // Abre o arquivo texto para escrita
            RandomAccessFile arquivo = new RandomAccessFile("valores.txt", "rw");

            // Leitura e escrita dos valores reais no arquivo
            for (int i = 0; i < n; i++) {
                double valor = MyIO.readDouble();
                arquivo.writeDouble(valor);

            }

            // Fecha o arquivo
            arquivo.close();

            // Reabre o arquivo para leitura
            arquivo = new RandomAccessFile("valores.txt", "r");

            arquivo.seek(arquivo.length());

            // Lê os valores de trás para frente
            for (int i = 0; i < n; i++) {
                arquivo.seek((arquivo.length() - i * 8) - 8); // 8 bytes por double
                double valor = arquivo.readDouble();
                //verifica se o valor é inteiro para printar sem casas decimais
                if (temdecimal(valor)) {
                    int valorint = (int) valor;
                    System.out.println(valorint);
                } else
                    System.out.println(valor);
            }

            // Fecha o arquivo novamente
            arquivo.close();
        } catch (IOException e) {
            System.err.println("Erro de E/S: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Formato inválido: " + e.getMessage());
        }
    }

    public static boolean temdecimal(double valor) {
        return valor == (int) valor;
    }
}
