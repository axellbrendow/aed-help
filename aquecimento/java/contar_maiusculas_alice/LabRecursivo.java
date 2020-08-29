public class LabMRecursivo {
    public static int contarMaiusculas(String str, int i, int tamanho) {
        int numMaiusculas = 0;

        if (i < tamanho) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                numMaiusculas++;
            }

            numMaiusculas += contarMaiusculas(str, i + 1, tamanho);
        }

        return numMaiusculas;
    }

    public static void main(String[] args) {
        String str = MyIO.readLine();
        
        while (
            str.length() < 3 ||
            !(
                str.charAt(0) == 'F' &&
                str.charAt(1) == 'I' &&
                str.charAt(2) == 'M'
            )
        ) {
            MyIO.println(contarMaiusculas(str, 0, str.length()));
            str = MyIO.readLine();
        }
    }
}
