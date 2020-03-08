import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Date;

/*
Problemas:


*/

//celulaDupla: TAD com ponteiro para o proximo elemento, com o ultimo apontando como prox null.
class CelulaDupla {
    public TimeListaDuplaQuicksort elemento;
    // elemento
    public CelulaDupla prox;
    public CelulaDupla ant;

    public CelulaDupla() {

    }

    public CelulaDupla(TimeListaDuplaQuicksort x) {
        this.elemento = x;
        this.prox = this.ant = null;

    }
}

class ListaFlexDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    /**
     * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
     */
    public ListaFlexDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }


    /**
     * Retorna o elemento na pos x, sem alterar a lista dupla
     * @param pos int posicao do elemento a ser retornado
     */
    public CelulaDupla elementoNaPosicao(int pos) throws Exception{
        CelulaDupla resp;
        int tamanho = tamanho();

        if(pos < 0 || pos >= tamanho)
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");

        // Caminhar ate a posicao anterior a desejada
        CelulaDupla i = primeiro.prox;
        for(int j = 0; j < pos; j++, i = i.prox);

        resp = i;
        //MyIO.println("Elemento na posicao "+pos+"= "+i.elemento.getNomeArquivo());


        return resp;
    }


    /**
     * Insere um elemento na primeira posicao da lista.
     * @param x TimeListaDuplaQuicksort time a ser inserido.
     */
    public void inserirInicio(TimeListaDuplaQuicksort x) {
        CelulaDupla tmp = new CelulaDupla(x);

        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }else{
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * @param x TimeListaDuplaQuicksort elemento a ser inserido.
     */
    public void inserirFim(TimeListaDuplaQuicksort x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    /**
     * Remove um elemento da primeira posicao da lista.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public TimeListaDuplaQuicksort removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        TimeListaDuplaQuicksort resp = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;
        return resp;
    }

    /**
     * Remove um elemento da ultima posicao da lista.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public TimeListaDuplaQuicksort removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }
        TimeListaDuplaQuicksort resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return resp;
    }

    /**
     * Insere um elemento em uma posicao especifica considerando que o
     * primeiro elemento valido esta na posicao 0.
     * @param x int elemento a ser inserido.
     * @param pos int posicao da insercao.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public void inserir(TimeListaDuplaQuicksort x, int pos) throws Exception {

        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0){
            inserirInicio(x);
        } else if (pos == tamanho){
            inserirFim(x);
        } else {
            // Caminhar ate a posicao anterior a insercao
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);

            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    /**
     * Remove um elemento de uma posicao especifica da lista
     * considerando que o primeiro elemento valido esta na posicao 0.
     * @param posicao Meio da remocao.
     * @return resp int elemento a ser removido.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public TimeListaDuplaQuicksort remover(int pos) throws Exception {
        TimeListaDuplaQuicksort resp;
        int tamanho = tamanho();

        if (primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");

        } else if(pos < 0 || pos >= tamanho){
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0){
            resp = removerInicio();
        } else if (pos == tamanho - 1){
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a insercao
            CelulaDupla i = primeiro.prox;
            for(int j = 0; j < pos; j++, i = i.prox);

            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            resp = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }

        return resp;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        int j = 0;

        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox, j++) {
            //System.out.printf("[%d] ", j);
            i.elemento.imprimir();
            //MyIO.println(i.elemento.getApelido());
        }
    }

    /**
     * Mostra os elementos da lista de forma invertida
     * e separados por espacos.
     */
    public void mostrarInverso() {
        System.out.print("[ ");
        for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
            System.out.print(i.elemento + " ");
        }
        System.out.println("] "); // Termina de mostrar.
    }

    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * @return resp int tamanho
     */
    public int tamanho() {
        int tamanho = 0;
        for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return tamanho;
    }

    /**
     * Funcao sobrecarregada para chamar a versao recursiva do quicksort.
     * @return numero de comparacoes feitas
     * @throws Exception se houver algum erro de remocao ou insercao
     */

    public int quicksort()throws Exception{
        return quicksort(0, this.tamanho()-1);
    }

    /**
     * Vers?o recursiva do quicksort, a ser chamado por uma funcao sobrecarregada acima, sem par?meteos
     * @param esq = limite na esquerda do quicksort
     * @param dir = limite na direita do quicksort
     * @return n?mero de compara??es entre apelidos dos times realizadas
     * @throws Exception com problemas no getApelido(), que n?o devem ocorrer de forma alguma.
     */
    public int quicksort(int esq, int dir) throws Exception{
        int comparacoes = 0;
       
        int i = esq, j = dir;
        //System.out.printf("Posicao atual de esq e dir: %d e %d.\n", esq, dir);

        CelulaDupla pivo = primeiro;

        //movimentar o pivo pra mediana
        for(int k = 0; k <= (dir+esq)/2; k++, pivo = pivo.prox);

        while (i <= j) {

            // os dois fors abaixo s?o a versao lista dupla de "while (array[j] > pivo) j--;"

            String apelidoPivo = pivo.elemento.getApelido().replace(" ", "");

            for(CelulaDupla tmp = this.elementoNaPosicao(i); tmp.elemento.getApelido().replace(" ", "").compareTo(apelidoPivo) < 0 && i < dir; tmp = tmp.prox, i=i+1, comparacoes++);
            for(CelulaDupla tmp = this.elementoNaPosicao(j); tmp.elemento.getApelido().replace(" ", "").compareTo(apelidoPivo) > 0 && j > esq; tmp = tmp.ant, j=j-1, comparacoes++);

            // add as duas comparacoes nao contabilizadas
            comparacoes += 2;

            if (i <= j) {
                //System.out.println("Swappando i e j, nas posicoes "+i + " "+j);
                swap(i, j);
                i++;
                j--;
            }
        }
        //chamadas recursivas
        if (j > esq)
            comparacoes += quicksort(esq, j+1);
        if (i < dir)
            comparacoes += quicksort(i, dir);

        return comparacoes;
    }

    public void swap(int pos1, int pos2) throws Exception{
        
        TimeListaDuplaQuicksort tmp = elementoNaPosicao(pos1).elemento;
        elementoNaPosicao(pos1).elemento = elementoNaPosicao(pos2).elemento;
        elementoNaPosicao(pos2).elemento = tmp;

    }
}

public class TimeListaDuplaQuicksort {

    private String nome, apelido, estadio, tecnico, liga, nomeArquivo;
    private int capacidade, fundacaoDia, fundacaoMes, fundacaoAno;
    private long paginaTam;

    public TimeListaDuplaQuicksort() {
        nome = apelido = estadio = tecnico = liga = nomeArquivo = "";
        paginaTam = capacidade = fundacaoDia = fundacaoMes = fundacaoAno = 0;
    }

    public TimeListaDuplaQuicksort (String nomeArq) throws Exception {
        ler(nomeArq);
    }

    public static void main(String[] args) throws Exception {
        long inicio = new Date().getTime();

        MyIO.setCharset("UTF-8");

        String[] entradas = new String[100];
        int quantidadeDeFrases = 0;

        // ler a entrada com os times at? inserir FIM

        do {
            entradas[quantidadeDeFrases] = MyIO.readLine();
        } while (!estaNoFinal(entradas[quantidadeDeFrases++]));

        // tirar o fim da contagem
        quantidadeDeFrases--;

        // MyIO.println("Numero de modificacoes: "+quantidadeModificacoes);
        // array de referencias a objetos
        TimeListaDuplaQuicksort[] conjuntoTimes = new TimeListaDuplaQuicksort[quantidadeDeFrases];

        // criar os objetos de acordo.
        for (int i = 0; i < quantidadeDeFrases; i++) {
            // criar o objeto e chamar o construtor
            conjuntoTimes[i] = new TimeListaDuplaQuicksort(entradas[i]);
        }

        // lista da questao

        ListaFlexDupla listaDosTimes = new ListaFlexDupla();

        // adicionar os times mencionados previamente no fim da lista
        for (int i = 0; i < quantidadeDeFrases; i++) {
            listaDosTimes.inserirFim(conjuntoTimes[i]);
        }

       //MyIO.println("Chegando no quicksort");
        int totalComparacoes = listaDosTimes.quicksort();
        
        // metodo de printar tudo
        listaDosTimes.mostrar();


        long fim = new Date().getTime();

        long execucao = fim-inicio;
        Arq.openWrite("649651_quicksort.txt");

        Arq.print("649651\t"+execucao+"\t"+totalComparacoes+"\t");

        Arq.close();

        // fim da main
    }

    // metodos set e get

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getFundacaoDia() {
        return fundacaoDia;
    }

    public void setFundacaoDia(int fundacaoDia) {
        this.fundacaoDia = fundacaoDia;
    }

    public int getFundacaoMes() {
        return fundacaoMes;
    }

    public void setFundacaoMes(int funcacaoMes) {
        this.fundacaoMes = funcacaoMes;
    }

    public int getFundacaoAno() {
        return fundacaoAno;
    }

    public void setFundacaoAno(int fundacaoAno) {
        this.fundacaoAno = fundacaoAno;
    }

    public long getPaginaTam() {
        return paginaTam;
    }

    public void setPaginaTam(long paginaTam) {
        this.paginaTam = paginaTam;
    }

    // metodo clone
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void imprimir() {
        System.out.println(toString());
    }

    public String toString() {
        String resp = nome + " ## " + apelido + " ## ";
        resp += ((fundacaoDia > 9) ? "" : "0") + fundacaoDia + ((fundacaoMes > 9) ? "/" : "/0") + fundacaoMes + "/"
                + fundacaoAno + " ## ";
        resp += estadio + " ## " + capacidade + " ## " + tecnico + " ## " + liga + " ## " + nomeArquivo + " ## "
                + paginaTam + " ## ";
        return resp;
    }

    public String removePunctuation(String campo) {
        campo = campo.replace(".", "");
        campo = campo.replace(",", "");
        campo = campo.replace(";", "");
        String resp = "";
        for (int i = 0; i < campo.length(); i++) {
            if (Character.isDigit(campo.charAt(i)))
                resp += campo.charAt(i);
            else
                i = campo.length();
        }

        return resp;
    }

    public int getMes(String campo) {
        int resp = 0;
        if (campo.contains("january") == true) {
            resp = 1;
        } else if (campo.contains("february") == true) {
            resp = 2;
        } else if (campo.contains("march") == true) {
            resp = 3;
        } else if (campo.contains("april") == true) {
            resp = 4;
        } else if (campo.contains("may") == true) {
            resp = 5;
        } else if (campo.contains("june") == true) {
            resp = 6;
        } else if (campo.contains("july") == true) {
            resp = 7;
        } else if (campo.contains("august") == true) {
            resp = 8;
        } else if (campo.contains("september") == true) {
            resp = 9;
        } else if (campo.contains("october") == true) {
            resp = 10;
        } else if (campo.contains("november") == true) {
            resp = 11;
        } else if (campo.contains("december") == true) {
            resp = 12;
        }
        return resp;
    }

    public String getSubstringEntre(String s, String antes, String depois) {
        String resp = "";
        int posInicio, posFim;

        posInicio = s.indexOf(antes) + antes.length();

        if (antes.compareTo(depois) != 0) {
            posFim = s.indexOf(depois);
        } else {
            posFim = s.indexOf(depois, posInicio);
        }

        if (0 <= posInicio && posInicio < posFim && posFim < s.length()) {
            resp = s.substring(posInicio, posFim);
        }

        return resp;
    }

    public String removeTags(String str) {

        String strRegEx = "<[^>]*>";

        str = str.replace("&#91;8&#93;", "");
        str = str.replace("\"", "");
        str = str.replace("&#91;1&#93;", "");
        str = str.replace(";note 1&#93;", " ");
        str = str.replace("&#91;1&#93;", " ");
        str = str.replace("&amp;", " ");
        str = str.replace("&#91;", " ");
        str = str.replace("&#91", " ");
        str = str.replace("1&#93", " ");
        str = str.replace("&#160;", " ");

        str = str.replaceAll(strRegEx, "");
        /*
         * //replace &nbsp; with space str = str.replace("&nbsp;", " "); //replace &amp;
         * with & str = str.replace("&amp;", "&");
         */

        // OR remove all HTML entities
        str = str.replaceAll("&.*?;", " ");

        return str;
    }

    public void ler(String nomeArquivo) throws Exception {

        FileReader file = new FileReader(nomeArquivo);
        BufferedReader buffer = new BufferedReader(file);
        String html = "";
        String line = buffer.readLine();
        while (line != null) {
            html += line;
            line = buffer.readLine();
        }

        buffer.close();
        file.close();

        html = html.substring(html.indexOf("Full name"));
        html = html.substring(0, html.indexOf("<table style"));
        String campos[] = html.split("<tr>");

        this.nomeArquivo = nomeArquivo;

        for (String campo : campos) {
            // Full name
            if (removeTags(campo).contains("Full name")) {
                campo = removeTags(campo);
                this.nome = campo.substring(9).trim();

                // Nickname(s)
            } else if (removeTags(campo).contains("Nickname(s)")) {
                campo = removeTags(campo);
                this.apelido = campo.substring(11).trim();

                // Founded
            } else if (removeTags(campo).toLowerCase().contains("founded")) {
                campo = removeTags(campo.split("<br />")[0]);
                this.fundacaoMes = this.getMes(campo.toLowerCase());

                if (this.fundacaoMes == 0) {
                    this.fundacaoDia = 0;
                    campo = campo.substring(7);
                    this.fundacaoAno = Integer.parseInt(campo.substring(0, 4));
                } else {
                    campo = campo.substring(7);
                    String data[] = campo.split(" ");
                    if (data.length < 3) {
                        this.fundacaoDia = 0;
                        this.fundacaoAno = Integer.parseInt(data[1].substring(0, 4));
                    } else {
                        if (campo.contains(",")) {
                            this.fundacaoDia = Integer.parseInt(data[1].replace("th", "").replace(",", ""));
                            this.fundacaoAno = Integer.parseInt(data[2].substring(0, 4));
                        } else if (Character.isDigit(data[0].charAt(0))) {
                            this.fundacaoDia = Integer.parseInt(data[0]);
                            this.fundacaoAno = Integer.parseInt(data[2].substring(0, 4));
                        } else {
                            this.fundacaoDia = 0;
                            this.fundacaoAno = Integer.parseInt(data[1].substring(0, 4));
                        }
                    }
                }

                // Ground
            } else if (removeTags(campo).toLowerCase().contains("ground")) {
                campo = removeTags(campo);
                this.estadio = campo.substring(6).trim();

                // Capacity
            } else if (removeTags(campo).toLowerCase().contains("capacity")) {
                campo = campo.split("<br")[0];
                campo = removeTags(campo.split("</td>")[0].replace(" ", ""));
                this.capacidade = Integer.parseInt(removePunctuation(campo.substring(8).split(";")[0]));

                // Coach
            } else if (removeTags(campo).toLowerCase().contains("coach") || campo.toLowerCase().contains("manager")) {
                campo = removeTags(campo).replace("(es)", "").trim();

                if (campo.toLowerCase().contains("coach")) {
                    int index = campo.toLowerCase().indexOf("coach");
                    this.tecnico = campo.substring(index + 5).trim();
                }

                else if (campo.toLowerCase().contains("manager") && (this.tecnico == null || this.tecnico.isEmpty())) {
                    int index = campo.toLowerCase().indexOf("manager");
                    this.tecnico = campo.substring(index + 7).trim();
                }
                // League
            } else if (removeTags(campo).contains("League") && (this.liga == null || this.liga.isEmpty())) {
                campo = removeTags(campo);
                this.liga = campo.substring(6).trim();
            }
        }

        File f = new File(nomeArquivo);
        setPaginaTam(f.length());

    }

    public static boolean estaNoFinal(String frase) {
        return (frase.length() >= 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
    }

}