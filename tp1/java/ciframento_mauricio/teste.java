public class teste {

    public static boolean isFim(String str) {
        return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');

    }

    public static String codificaString(String s) {

        String sCript = "";

        for (int i = 0; i < s.length(); i++) {

            int letra = (int) s.charAt(i);

            if (letra >= 0 && letra <= 255) {
                letra += 3;
            }
            sCript += (char) letra;
        }

        return sCript;

    }

    public static void main(String[] args) {
        String str = "";
        String strCript;

        str = MyIO.readLine();

        while (!isFim(str)) {
            strCript = codificaString(str);
            MyIO.print(strCript + "\n");
            str = MyIO.readLine();
        }

    }

}
