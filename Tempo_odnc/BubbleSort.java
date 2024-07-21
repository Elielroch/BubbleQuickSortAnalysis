import java.io.*;
import java.util.ArrayList;
import java.util.List;

 public class BubbleSort {

    public static void main(String[] args) {
        String arquivoEntrada = "arq(1).txt";
        String arquivoSaida = "arq-saida.txt";

        List<Integer> numeros = lerNumeros(arquivoEntrada);
        if (numeros == null) {
            System.out.println("Erro ao ler o arquivo de entrada.");
            return;
        }

        long tempoInicio = System.currentTimeMillis();
        List<Integer> numerosOrdenados = bubbleSort(numeros);
        long tempoFim = System.currentTimeMillis();

        escreverNumeros(arquivoSaida, numerosOrdenados);

        long tempoGasto = tempoFim - tempoInicio;  
        long memoriaUsada = medirMemoria();  

        System.out.printf("Tempo gasto: %d ms%n", tempoGasto);
        System.out.printf("Memória usada: %d KB%n", memoriaUsada);
    }

    public static List<Integer> bubbleSort(List<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        return lista;
    }

    public static List<Integer> lerNumeros(String nomeArquivo) {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                numeros.add(Integer.parseInt(linha.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return numeros;
    }

    public static void escreverNumeros(String nomeArquivo, List<Integer> numeros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int numero : numeros) {
                bw.write(String.valueOf(numero));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long medirMemoria() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();  
        long memoriaUsada = (runtime.totalMemory() - runtime.freeMemory()) / 1024;  
        return memoriaUsada;
    }
}
