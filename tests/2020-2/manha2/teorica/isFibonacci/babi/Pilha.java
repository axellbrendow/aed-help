/**
 * Pilha dinamica
 * 
 * @author Max do Val Machado
 * @version 2 01/2015
 */
public class Pilha {
	private Celula topo;

	/**
	 * Construtor da classe que cria uma fila sem elementos.
	 */
	public Pilha() {
		topo = null;
	}

	/**
	 * Insere elemento na pilha (politica FILO).
	 * 
	 * @param x int elemento a inserir.
	 */
	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
    }
    
	/**
	 * Remove elemento da pilha (politica FILO).
	 * 
	 * @return Elemento removido.
	 * @trhows Exception Se a sequencia nao contiver elementos.
	 */
	public int remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}
		int resp = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
    }
    
    public int fibonacci(int i) {
        return i < 2 ? i : fibonacci(i - 1) + fibonacci(i - 2);
    }

    public int getTam(int tam, Celula celula) {
        return celula != null ? getTam(tam + 1, celula.prox) : tam;
    }

    public boolean isFibonacciRecursivo(int i) { // Imaginando i começando com 0
        try {
            int elemento = remover();
            return elemento == fibonacci(getTam(0, topo)) && isFibonacciRecursivo(i + 1);
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isFibonacciIterativo() {
        int tam = getTam(0, topo);
        Celula celula = topo;

        for (int i = 1; i <= tam; i++, celula = celula.prox)
            if (celula.elemento != fibonacci(tam - i))
                return false;

        return true;
    }

    // public static void printarIterativo(Pilha p1, Pilha p2) {
    //     Celula i = p1.topo, j = p2.topo;
    //     System.out.printf("%3s %3s\n", "p1", "p2");

    //     while (i != null || j != null) {
    //         System.out.printf("%3s %3s\n",
    //             i != null ? "" + i.elemento : " ",
    //             j != null ? "" + j.elemento : " "
    //         );

    //         if (i != null) i = i.prox;
    //         if (j != null) j = j.prox;
    //     }
    // }

    // public static void printarRec(Celula p1, Celula p2) {
    //     if (p1 != null || p2 != null) {
    //         System.out.printf("%3s %3s\n",
    //             p1 != null ? "" + p1.elemento : " ",
    //             p2 != null ? "" + p2.elemento : " "
    //         );

    //         printarRec(p1 != null ? p1.prox : null, p2 != null ? p2.prox : null);
    //     }
    // }

    // public static void printarRec(Pilha p1, Pilha p2) {
    //     System.out.printf("%3s %3s\n", "p1", "p2");
    //     printarRec(p1.topo, p2.topo);
    // }
}
