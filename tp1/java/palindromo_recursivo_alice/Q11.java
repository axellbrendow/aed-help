class Q11{
    public static boolean isPalindromo(String s){
        int j = s.length()-1;
        return isPalindromo(s, 0, j);
    }

    public static boolean isPalindromo(String s, int i, int j){
        boolean resp = true;

        if (s.charAt(i) != s.charAt(j))
            resp = false;
        else
            if (i >= j)
                resp = isPalindromo(s, i+1, j-1);

        return resp;
    }

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args){
        String entrada[] = new String[1000];
        int numEntrada = 0;

        do {
            entrada[numEntrada] = MyIO.readLine(); 
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; //Desconsiderar última linha com FIM

        //Para cada linha de entrada, gerando uma de saida contendo SIM, se a palavra é palindromo, ou NÃO, se não é.
        for(int i = 0; i < numEntrada; i++){
            if(isPalindromo(entrada[i]) == true)
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
        }
    }
}