public class TP01Q03 {

    public static String codificaString ( String s){

        String sCript = "";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ' ') {
                sCript += ' ';

            } else {
                char letra = s.charAt(i);

                if (letra >= 'A' && letra <= 'Z') {
                    letra = (char) ('A' + ((letra - 'A' + 3) % 26));
                   
                } else if (letra >= 'a' && letra <= 'z') {
                    letra = (char) ('a' + ((letra - 'a' + 3) % 26));
                }
                sCript += letra;
            }
        }
        
        return sCript;

    }

    public static void main (String []args){
        String str = "";
        String strCript;

        str = MyIO.readLine("Digite uma string: ");

       strCript =  codificaString(str);

       MyIO.println(strCript);

    }

}