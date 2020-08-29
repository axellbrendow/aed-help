class PalindromoOld {
    public static boolean Palinjava(String str) {
        boolean is = true;
        int comeco = 0;
        int fim = (str.length() - 1);
        int fimindice = fim;
        for (int i = 0; i < fimindice && comeco < fim; i++) {
            if (str.charAt(comeco) != str.charAt(fim)) {
                is = false;
                i = str.length();
            } else {
                comeco++;
                fim--;
            }

        }
        return is;
    }

    public static void main(String[] args) {
        Arq.openWrite("pub.out.txt");
        Arq.openRead("pub.in.txt");

        String word = Arq.readLine();

        System.out.printf("word = '%s'%n", word);

        while (Arq.hasNext() == true) {
            boolean is = Palinjava(word);
            if (!is) {
                Arq.println("SIM");
            } else {
                Arq.println("NAO");
            }
            word = Arq.readString();
            System.out.println("Cheguei aqui novamente");
        }
        Arq.close();
        Arq.close();
    }
}
