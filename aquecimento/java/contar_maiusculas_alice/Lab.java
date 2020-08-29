public class LabM {
    public static int contarMaiusculas(String str) {
        int numMaiusculas = 0;
        int tamanho = str.length();

        for (int i = 0; i < tamanho; i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                numMaiusculas++;
            }
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
            MyIO.println(contarMaiusculas(str));
            str = MyIO.readLine();
        }
    }
}
