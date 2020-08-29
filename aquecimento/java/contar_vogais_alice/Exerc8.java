class Exerc8{
    public static int contarVogais(String s){
        return contarVogais(s, 0);
    }

    /**
     * Conta a quantidade de vogais de uma string a partir de uma posição.
     * 
     * @param s String a ter suas vogais contadas.
     * @param i Posição inicial de busca.
     * 
     * @return Quantidade de vogais a partir da posição {@code i}.
     */
    public static int contarVogais(String s, int i){
        int quantVogais = 0;

        if(i != s.length()){
            if (s.charAt(i) == 'a'
                    || s.charAt(i) == 'e'
                    || s.charAt(i) == 'i'
                    || s.charAt(i) == 'o'
                    || s.charAt(i) == 'u'
                    || s.charAt(i) == 'A'
                    || s.charAt(i) == 'E'
                    || s.charAt(i) == 'I'
                    || s.charAt(i) == 'O'
                    || s.charAt(i) == 'U'
            ){
                quantVogais++;
            }
            
            quantVogais += contarVogais(s, i+1);
        }

        return quantVogais;
    }
    
    public static void main(String[] args){
        String s = "Alice";

        System.out.printf("%d\n", contarVogais(s));
    }
}
