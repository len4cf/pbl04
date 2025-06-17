import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String[] arquivos = {
                "aleatorio_100.csv", "aleatorio_1000.csv", "aleatorio_10000.csv",
                "crescente_100.csv", "crescente_1000.csv", "crescente_10000.csv",
                "decrescente_100.csv", "decrescente_1000.csv", "decrescente_10000.csv"
        };

        for (String arquivo : arquivos) {
            try {
                System.out.println("\nArquivo: " + arquivo);
                int[] dados = lerCsv(arquivo);

                testAlgoritmo("Bubble Sort", dados, "bubble");
                testAlgoritmo("Insertion Sort", dados, "insertion");
                testAlgoritmo("Quick Sort", dados, "quick");

            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + arquivo);
            }
        }
    }

    public static int[] lerCsv(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int count = -1;  // Começa em -1 pra ignorar o cabeçalho

        int totalLinhas = 0;
        while ((line = reader.readLine()) != null) {
            totalLinhas++;
        }
        reader.close();

        int[] data = new int[totalLinhas - 1];

        reader = new BufferedReader(new FileReader(filePath));
        count = -1;
        while ((line = reader.readLine()) != null) {
            if (count >= 0) {
                data[count] = Integer.parseInt(line.trim());
            }
            count++;
        }
        reader.close();
        return data;
    }


    private static void testAlgoritmo(String nome, int[] dadosOriginais, String tipo) {
        int[] dados = Arrays.copyOf(dadosOriginais, dadosOriginais.length);
        long inicio = System.nanoTime();

        switch (tipo) {
            case "bubble":
                SortAlgorithms.bubbleSort(dados);
                break;
            case "insertion":
                SortAlgorithms.insertionSort(dados);
                break;
            case "quick":
                SortAlgorithms.quickSort(dados);
        }

        long fim = System.nanoTime();
        long tempo = fim - inicio;
        System.out.println(nome + " - Tempo: " + tempo + " nanosegundos");
    }
}
