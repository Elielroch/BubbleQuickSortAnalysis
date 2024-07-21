import time
import psutil
import os

def quick_sort(lista):
    if len(lista) <= 1:
        return lista
    pivo = lista[len(lista) // 2]
    esquerda = [x for x in lista if x < pivo]
    meio = [x for x in lista if x == pivo]
    direita = [x for x in lista if x > pivo]
    return quick_sort(esquerda) + meio + quick_sort(direita)

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
    numeros_ordenados = quick_sort(numeros)
    tempo_fim = time.time()
    
    escrever_numeros(arquivo_saida, numeros_ordenados)
    
    tempo_gasto = (tempo_fim - tempo_inicio) * 1000  
    memoria_usada = medir_memoria()  
    
    print(f"Tempo gasto: {tempo_gasto:.2f} ms")
    print(f"Memória usada: {memoria_usada:.2f} KB")
