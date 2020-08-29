class TP01Q15{
    public static boolean isFim(String s){
        return (s.length()>=3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isVogal(String s){
     boolean retorno = false;
     
        if(s.length()>1 && s.charAt(0)=='a' || s.charAt(0)=='A' || s.charAt(0)=='e' || s.charAt(0)=='E' || s.charAt(0)=='i' || s.charAt(0)=='I' || s.charAt(0)=='o' || s.charAt(0)=='O' || s.charAt(0)=='u' || s.charAt(0)=='U'){
          retorno = true;
            if(s.length()>1){
                s=s.substring(1, s.length());
                retorno = true;
            }
          return isVogal(s);
        }else if(s.length()==1){
          retorno = true;
        }else{
          retorno = false;
        }

    return retorno;
    }

    public static boolean isConsoante(String s){
     boolean retorno = false;

        if((s.length()>1 && (s.charAt(0)>='a' && s.charAt(0)<='z') || (s.charAt(0)>='A' && s.charAt(0)<='Z')) && s.charAt(0)!='a' && s.charAt(0)!='A' && s.charAt(0)!='e' && s.charAt(0)!='E' && s.charAt(0)!='i' && s.charAt(0)!='I' && s.charAt(0)!='o' && s.charAt(0)!='O' && s.charAt(0)!='u' && s.charAt(0)!='U'){
            if(s.length()>1){
                s=s.substring(1, s.length());
                retorno = true;
            }
          return isConsoante(s);
        }else if(s.length()==1){
            retorno = true;
        }else{
            retorno = false;
        }

    return retorno;
    }
    
    public static boolean isInteiro(String s){
     boolean retorno = false;

        if(s.length()>1 && s.charAt(0)>='0' && s.charAt(0)<='9'){
            if(s.length()>1){  
                s=s.substring(1, s.length());
                retorno = true;
            }
            return isInteiro(s);
        }else if (s.length()==1 && s.charAt(0)>='0' && s.charAt(0)<='9'){
            retorno = true;
        }else{
            retorno = false;
        }

    return retorno;
    }
    
    public static boolean isReal(String s, int virgula){
     boolean retorno = false;

     
        if(s.length()>1 && (s.charAt(0)>='0' && s.charAt(0)<='9'|| s.charAt(0) == ','||s.charAt(0) == '.') ){

                retorno = true;
                s=s.substring(1, s.length());
                    if(s.charAt(0)== ',' || s.charAt(0) == '.')
                        virgula++;
                return isReal(s, virgula);

        }else if(s.length()==1 && s.charAt(0) == ','||s.charAt(0) == '.' || s.charAt(0)>='0' && s.charAt(0)<='9'){
            retorno = true;
        }else{
            retorno = false;
        }

        if(virgula>1)
            retorno = false;
        
    return retorno;
    }
    public static void main(String args[]){
        String[] entrada = new String[1000];
        int numEntrada = 0;
        
        do{
            entrada[numEntrada]=MyIO.readLine();
        }while ( isFim(entrada[numEntrada++]) == false );
        numEntrada--;
        
        for(int i = 0; i < numEntrada; i++){
            System.out.print(isVogal(entrada[i]) == true ? "SIM" : "NAO");
            System.out.print(isConsoante(entrada[i]) == true ? " SIM" : " NAO");
            System.out.print(isInteiro(entrada[i]) == true ? " SIM" : " NAO");
            System.out.print(isReal(entrada[i], 0) == true ? " SIM" : " NAO");
            System.out.println();

        }
    }
}
