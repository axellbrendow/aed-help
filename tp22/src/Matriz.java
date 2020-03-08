/**
 * AED II - TP2 - Matriz Flexivel
 * @author Rayane Paiva Reginaldo
 * @version - 0.1
 */

// -------------------------------------------------------------------- classe Matriz

public class Matriz {

   // ------------------------------------------ atributos

   private Celula inicio;          // inicio da Matriz

   private int linhas;
   private int colunas;

   // ------------------------------------------ construtores

   /**
    * Construtor padrao.
    */
   public Matriz(){
      this(3, 3);
   }

   /**
    * Construtor alternativo.
    * @param linha - quantidade de linhas
    * @param coluna - quantidade de colunas
    */
   public Matriz (int linha, int coluna){

      this.linhas = linha;
      this.colunas = coluna;

      this.inicio = new Celula();

      Celula pColuna = this.inicio;
      Celula pLinha = this.inicio;

      for (int i = 0 ; i < (linha - 1) ; i++){

         for (int j = 0 ; j < (coluna - 1) ; j++){

            pColuna.dir = new Celula();      // nova celula na coluna da direita
            pColuna.dir.esq = pColuna;       // conecta coluna da direita com coluna da esquerda
            pColuna = pColuna.dir;           // caminha para proxima coluna

            MyIO.println("---");
            MyIO.println("pl: " +  pLinha);
            MyIO.println("pl.inf 1: " +  pLinha.inf);
            //         MyIO.println("pl.inf.sup: " +  pLinha.inf.sup);
            //         MyIO.println("---");
         }  

         pLinha.inf = new Celula();  // nova celula na linha inferior


         pColuna = pLinha.inf;       // pColuna caminha para primeira coluna na linha de baixo 
         pLinha.inf.sup = pLinha;    // pLinha conecta celula abaixo com celula acima

         //         MyIO.println("---");
         //         MyIO.println("pl: " +  pLinha);
         MyIO.println("pl.inf 2: " +  pLinha.inf);
         MyIO.println("pl.inf.sup: " +  pLinha.inf.sup);
         MyIO.println("---");

         pLinha = pLinha.inf;        // pLinha caminha para nova linha abaixo
      }

      MyIO.println("MOSTRAR ENDERECOS: ");
      mostrar();
   }

   // ------------------------------------------ setters

   public void setLinhas(int i){
      this.linhas = i;
   }

   public void setColunas(int i){
      this.colunas = i;
   }

   // ------------------------------------------ getters  

   public int getLinhas(){
      return this.linhas;
   }

   public int getColunas(){
      return this.colunas;
   }

   public Celula getInicio(){
      return this.inicio;
   }

   // ------------------------------------------ clone

   /**
    * retorna um clone da lista.
    */
   public Matriz clone(){
      return null;
   }

   // ------------------------------------------ preencher

   public void preencherMatriz(String elementos){

      Celula pLinha;
      Celula pColuna;
      int i = 0;

      for (pLinha = this.inicio ; pLinha != null ; pLinha = pLinha.inf){

         for (pColuna = this.inicio ; pColuna != null ; pColuna = pColuna.dir){

            String num = "" + elementos.charAt(i);
            pColuna.elemento = Integer.parseInt(num);
            i++;            
         }  

         pColuna = pLinha.inf;
      }

      //      MyIO.println("Mostrar Dentro: ");
      //      mostrar();
   }

   // ------------------------------------------ soma      

   public Matriz soma(Matriz m){
      return null;
   }

   // ------------------------------------------ multiplicacao   

   public Matriz multiplica(Matriz m){
      return null;
   }

   // ------------------------------------------ isQuadrada   

   public boolean isQuadrada(){
      return true;
   }

   // ------------------------------------------ mostrarDiagonalPrincipal   

   public void mostrarDiagonalPrincipal(){}

   // ------------------------------------------ mostrarDiagonalSecundaria   

   public void mostrarDiagonalSecundaria(){}

   // ------------------------------------------ mostrar   

   public void mostrar(){      

      Celula pLinha;
      Celula pColuna;

      for (pLinha = this.inicio ; pLinha != null ; pLinha = pLinha.inf) {

         for (pColuna = this.inicio ; pColuna != null ; pColuna = pColuna.dir){

            MyIO.print(pColuna + " ");
         }

         MyIO.println("");
         pColuna = pLinha.inf;
      }
   }

   // ------------------------------------------    
   // ------------------------------------------ 

   public static void main(String [] args){

      Matriz m1;
      Matriz m2;

      int linhas;
      int colunas;

      String elementos;

      int nTestes = Integer.parseInt(MyIO.readLine());

      //      MyIO.println("=======> nTestes: " + nTestes);

      for (int i = 0 ; i < nTestes ; i++){

         elementos = "";

         // ------------- primeira matriz

         linhas = Integer.parseInt(MyIO.readLine());
         colunas = Integer.parseInt(MyIO.readLine());

         //         MyIO.println("=======> linhas m1: " + linhas);
         //         MyIO.println("=======> colunas m1: " + colunas);

         m1 = new Matriz(linhas, colunas);

         for (int k = 0 ; k < linhas ; k++){
            elementos += MyIO.readLine() + " ";
         }

         elementos = elementos.replaceAll(" ", "");
         //         MyIO.println("=======> elementos m1: " + elementos);

         m1.preencherMatriz(elementos);

         //         MyIO.println("MOSTRAR DEPOIS: ");
         //         m1.mostrar();

         elementos = "";

         // ------------- segunda matriz

         linhas = Integer.parseInt(MyIO.readLine());
         colunas = Integer.parseInt(MyIO.readLine());

         //         MyIO.println("=======> linhas m2: " + linhas);
         //         MyIO.println("=======> colunas m2: " + colunas);

         m2 = new Matriz(linhas, colunas);

         for (int m = 0 ; m < linhas ; m++){
            elementos += " " + MyIO.readLine();
         }

         elementos = elementos.replaceAll(" ", "");
         //         MyIO.println("=======> elementos m2: " + elementos);

         //         m2.preencherMatriz(elementos);

         /*
         // ------------- saida

         m1.mostrarDiagonalPrincipal();
         m1.mostrarDiagonalSecundaria();

         Matriz soma = m1.soma(m2);
         soma.mostrar();

         Matriz mult = m1.soma(m2);
         mult.mostrar();
         */
      }
   }

} // endClassMatriz

// -------------------------------------------------------------------- classe Celula

class Celula{

   // ------------------------------------------ atributos

   public int elemento;

   public Celula inf;
   public Celula sup;
   public Celula esq;
   public Celula dir;

   // ------------------------------------------ construtores

   /**
    * Construtor Padrao.
    */
   public Celula(){

      this(0, null, null, null, null);   
   }

   /**
    * Construtor Alternativo
    * @param elemento - elemento a ser inserido
    */
   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   /**
    * Construtor Alternativo.
    * @param elemento - elemento a ser inserido
    * @param inf - referencia (ou ponteiro) inferior
    * @param sup - referencia (ou ponteiro) superior
    * @param esq - referencia (ou ponteiro) esquerdo
    * @param dir - referencia (ou ponteiro) direito
    */
   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){

      this.elemento = elemento;

      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }

} // endClassCelula

// -------------------------------------------------------------------- end

