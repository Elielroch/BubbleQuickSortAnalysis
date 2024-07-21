import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public static void main(String[] args) {
        String arquivoEntrada = "arq(1).txt";
        String arquivoSaida = "arq-saida.txt";

        List<Integer> numeros = lerNumeros(arquivoEntrada);
        if (numeros == null) {
            System.out.println("Erro ao ler o arquivo de entrada.");
            return;
        }

        long tempoInicio = System.currentTimeMillis();
        List<Integer> numerosOrdenados = quickSort(numeros);
        long tempoFim = System.currentTimeMillis();

        escreverNumeros(arquivoSaida, numerosOrdenados);

        long tempoGasto = tempoFim - tempoInicio;  // em ms
        long memoriaUsada = medirMemoria();  // em KB

        System.out.printf("Tempo gasto: %d ms%n", tempoGasto);
        System.out.printf("Memória usada: %d KB%n", memoriaUsada);
    }

    public static List<Integer> quickSort(List<Integer> lista) {
        if (lista.size() <= 1) {
            return lista;
        }
        int pivo = lista.get(lista.size() / 2);
        List<Integer> esquerda = new ArrayList<>();
        List<Integer> meio = new ArrayList<>();
        List<Integer> direita = new ArrayList<>();
        
        for (int num : lista) {
            if (num < pivo) {
                esquerda.add(num);
            } else if (num == pivo) {
                meio.add(num);
            } else {
                direita.add(num);
            }
        }

        List<Integer> resultado = new ArrayList<>();
        resultado.addAll(quickSort(esquerda));
        resultado.addAll(meio);
        resultado.addAll(quickSort(direita));

        return resultado;
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
        runtime.gc();  // Solicitar a coleta de lixo
        long memoriaUsada = (runtime.totalMemory() - runtime.freeMemory()) / 1024;  // em KB
        return memoriaUsada;
    }
}

