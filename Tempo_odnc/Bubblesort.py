import time
import psutil
import os

def bubble_sort(lista):
    n = len(lista)
    for i in range(n):
        for j in range(0, n-i-1):
            if lista[j] > lista[j+1]:
                lista[j], lista[j+1] = lista[j+1], lista[j]
    return lista

def ler_numeros(nome_arquivo):
    if not os.path.isfile(nome_arquivo):
        raise FileNotFoundError(f"O arquivo {nome_arquivo} não existe.")
    with open(nome_arquivo, 'r') as arquivo:
        return [int(linha.strip()) for linha in arquivo]

def escrever_numeros(nome_arquivo, numeros):
    with open(nome_arquivo, 'w') as arquivo:
        for numero in numeros:
            arquivo.write(f"{numero}\n")

def medir_memoria():
    processo = psutil.Process(os.getpid())
    return processo.memory_info().rss / 1024  

if __name__ == "__main__":
    arquivo_entrada = 'arq(1).txt'  
    arquivo_saida = 'arq-saida.txt'
    
    try:
        numeros = ler_numeros(arquivo_entrada)
    except FileNotFoundError as e:
        print(e)
        exit(1)
    
    tempo_inicio = time.time()
    numeros_ordenados = bubble_sort(numeros)
    tempo_fim = time.time()
    
    escrever_numeros(arquivo_saida, numeros_ordenados)
    
    tempo_gasto = (tempo_fim - tempo_inicio) * 1000  # em ms
    memoria_usada = medir_memoria()  # em KB
    
    print(f"Tempo gasto: {tempo_gasto:.2f} ms")
    print(f"Memória usada: {memoria_usada:.2f} KB")

