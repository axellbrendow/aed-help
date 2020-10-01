public class PilhaPrincipal {

    public static void main(String[] args) {
        Pilha p1 = new Pilha();

        p1.inserir(0);
        p1.inserir(1);
        p1.inserir(1);
        p1.inserir(2);
        p1.inserir(3);
        p1.inserir(5);

        System.out.println("isFibonacciIterativo = " + p1.isFibonacciIterativo());
        System.out.println("isFibonacciRecursivo = " + p1.isFibonacciRecursivo(0));
    }
}
