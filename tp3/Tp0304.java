import java.lang.*;
import java.util.*;
import java.io.*;

class Personagem {
	// Atributos

	private String nome;
	private int altura;
	private double peso;
	private String corDoCabelo;
	private String corDaPele;
	private String corDosOlhos;
	private String anoNascimento;
	private String genero;
	private String homeworld;

	// -------------------------------------------------------------------------------------------------------------------------Construtores
	// Construtor
	public Personagem(String path) {
		// MyIO.println("path: "+path);
		String dados = "";
		String[] dadosSeparados = new String[2];

		// Coleta de dados
		dados = dadosDoArquivo(path);
		// MyIO.println("dados: "+dados);
		// Tratamento da string
		dados = reduzirString(dados);
		// MyIO.println("dados tratados: "+dados);

		// Sepados os dados em um vetor
		dadosSeparados = separacaoDeDados(dados);

		// Atribuindo dados aos atributos
		nome = dadosSeparados[0];
		altura = Integer.parseInt(dadosSeparados[1]);
		peso = Double.parseDouble(dadosSeparados[2]);
		corDoCabelo = dadosSeparados[3];
		corDaPele = dadosSeparados[4];
		corDosOlhos = dadosSeparados[5];
		anoNascimento = dadosSeparados[6];
		genero = dadosSeparados[7];
		homeworld = dadosSeparados[8];

	}

	// Construtor alternativo
	public Personagem() {
		nome = "";
		altura = 0;
		peso = 0.0;
		corDoCabelo = "";
		corDoCabelo = "";
		corDaPele = "";
		corDosOlhos = "";
		anoNascimento = "";
		genero = "";
		homeworld = "";

	}

	// -----------------------------------------------------------------------------------------------Metodos
	// 'gets'

	public String getNome() {
		return this.nome;
	}

	public int getAltura() {
		return this.altura;
	}

	public double getPeso() {
		return this.peso;
	}

	public String getCorDoCabelo() {
		return this.corDoCabelo;
	}

	public String getCorDaPele() {
		return this.corDaPele;
	}

	public String getCorDosOlhos() {
		return this.corDosOlhos;
	}

	public String getAnoNascimento() {
		return this.anoNascimento;
	}

	public String getGenero() {
		return this.genero;
	}

	public String getHomeworld() {
		return this.homeworld;
	}

	// ------------------------------------------------------------------------------------------------Metodos
	// 'sets'
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setCorDoCabelo(String corDoCabelo) {
		this.corDoCabelo = corDoCabelo;
	}

	public void setCorDaPele(String corDoCabelo) {
		this.corDoCabelo = corDoCabelo;
	}

	public void setCorDosOlhos(String corDosOlhos) {
		this.corDosOlhos = corDosOlhos;
	}

	public void setAnoNascimento(String anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}

	// ----------------------------------------------------------------------------------------------Funcoes
	// adicionais

	/*
	 * Metodo para clonar dados de um objeto em seus atributos
	 */
	public Personagem clone() {
		Personagem c = new Personagem();

		c.setNome(this.getNome());
		c.setAltura(this.getAltura());
		c.setPeso(this.getPeso());
		c.setCorDoCabelo(this.getCorDoCabelo());
		c.setCorDaPele(this.getCorDaPele());
		c.setCorDosOlhos(this.getCorDosOlhos());
		c.setAnoNascimento(this.getAnoNascimento());
		c.setHomeworld(this.getHomeworld());

		return c;
	}

	/*
	 * Metodo para mostrar na tela os atributos da classe
	 */
	public void imprimir() {
		if (getPeso() % 1 == 0) {
			MyIO.println(" ## " + getNome() + " ## " + getAltura() + " ## " + (int) getPeso() + " ## "
					+ getCorDoCabelo() + " ## " + getCorDaPele() + " ## " + getCorDosOlhos() + " ## "
					+ getAnoNascimento() + " ## " + getGenero() + " ## " + getHomeworld() + " ## ");
		} else {
			MyIO.println(" ## " + getNome() + " ## " + getAltura() + " ## " + (int) getPeso() + " ## "
					+ getCorDoCabelo() + " ## " + getCorDaPele() + " ## " + getCorDosOlhos() + " ## "
					+ getAnoNascimento() + " ## " + getGenero() + " ## " + getHomeworld() + " ## ");
		}

	}

	/*
	 * Metodo para mostrar na tela os atributos da classe com o peso 0
	 */
	public void imprimirPadrao() {
		MyIO.println("  ## " + getNome() + " ## " + getAltura() + " ## " + "0 ## " + getCorDoCabelo() + " ## "
				+ getCorDaPele() + " ## " + getCorDosOlhos() + " ## " + getAnoNascimento() + " ## " + getGenero()
				+ " ## " + getHomeworld() + " ## ");
	}

	/*
	 * Funcao para coletar os dados contidos em um arquivo Parametro path : string
	 * contendo o endereco do arquivo Retorno : string contendo os dados colhidos
	 */
	public static String dadosDoArquivo(String path) {
		String str = "";
		// Abertura do arquivo
		Arq.openRead(path);
		// MyIO.println("PathNoarquivo: "+path);
		// Coleta de dados
		str = Arq.readLine();

		Arq.close();
		// MyIO.println("fim do dado: "+str);
		return str;

	}

	/*
	 * Funcao para excluir dados irrelevantes de uma string Parametro str : string
	 * contendo todos dados Retorno : string contendo apenas os dados relevantes
	 */
	public static String reduzirString(String str) {
		String dadosRelevantes = "";
		int contadorDoisPontos = 0;
		char aspasSimples = (char) 39;
		int contadorAspasSimples = 0;
		int j = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ':' && contadorDoisPontos < 9) {
				contadorDoisPontos++;
				j = i + 1;
				while (contadorAspasSimples < 2) {
					if (str.charAt(j) == aspasSimples) {
						contadorAspasSimples++;
					}

					if (contadorAspasSimples <= 2) {
						dadosRelevantes += str.charAt(j);

					}
					j++;
				}
				i = j;
				contadorAspasSimples = 0;
			}
		}
		return dadosRelevantes;
	}

	/*
	 * Funcao que separada dados de uma string em um vetor de strings Parametro str
	 * : string contendo todos dados Retorno : dados separados em um vetor de string
	 */
	public static String[] separacaoDeDados(String str) {
		String[] resultado = new String[9];
		char aspasSimples = (char) 39;
		int j = 0;
		int contadorDados = 0;

		// Inicializando as strings
		for (int i = 0; i < 9; i++) {
			resultado[i] = "";
		}

		// Separando os dados
		while (j < str.length()) {
			if (str.charAt(j) == aspasSimples) {
				j++;
				while (str.charAt(j) != aspasSimples) {
					resultado[contadorDados] += str.charAt(j);
					j++;
				}
				contadorDados++;
			}
			j++;
		}

		// Tratamento caso os dados da altura e peso nao estejam no padrao correto
		if (resultado[1].charAt(0) == 'u') {
			resultado[1] = "0";
		}
		if (resultado[2].charAt(0) == 'u') {
			resultado[2] = "0";
			resultado[2] += '.';
			resultado[2] += '0';
		} else {
			String aux = "";
			char c;
			for (int i = 0; i < resultado[2].length(); i++) {

				c = resultado[2].charAt(i);
				if (c == ',') {
					aux += '.';
				} else {
					aux += c;
				}
			}
			resultado[2] = aux;
		}

		return resultado;
	}

}

class Celula {
	public Personagem elemento;
	public Celula anterior;
	public Celula proximo;

	Celula() {
		elemento = null;
		anterior = proximo = null;
	}

	Celula(Personagem p) {
		this.elemento = p;

		this.anterior = this.proximo = null;

	}
}

class Lista {
	// Atributos
	private Celula primeiro, ultimo;
	int tamanho;

	// -----------------------------------------------------------Construtores
	/*
	 * Construtor padrao
	 */
	Lista() {
		this.primeiro = new Celula();
		this.ultimo = this.primeiro;
		this.tamanho = 0;
	}

	// ---------------------------------------------------------Metodos de insercao

	/*
	 * Metodo para inserir um personagem no inicio da lista
	 * 
	 * @param p : novo objeto a ser inserido na lista
	 */
	public void inserirFim(Personagem p) throws Exception {

		this.ultimo.proximo = new Celula(p);

		this.ultimo.proximo.anterior = this.ultimo;

		this.ultimo = this.ultimo.proximo;

		this.tamanho++;

	}

	// ------------------------------------------------------------------------Metodos
	// adicionais

	/*
	 * Metodo para mostrar o nome de todos os elementos da lista
	 */
	public void mostrar() {
		for (Celula i = this.primeiro.proximo; i != null; i = i.proximo) {
			MyIO.println(i.elemento.getNome() + " " + i.elemento.getCorDoCabelo());
		}
	}

	/*
	 * Metodo para mostrar qual elemento da lista esta em cada posicao
	 */
	public void mostrarPadrao() {
		Celula j = this.primeiro.proximo;

		for (int i = 0; i < this.tamanho; i++) {
			MyIO.print("[" + i + "]");
			j.elemento.imprimirPadrao();
			j = j.proximo;
		}

	}

	/*
	 * Metodo para movimentar dois elementos da lista
	 */
	public void swap(Celula i, Celula j) {
		Personagem aux = i.elemento;

		i.elemento = j.elemento;

		j.elemento = aux;
	}

	/*
	 * Metodo para organizar a lista em ordem alfabetica sem retirar a organizacao
	 * pela cor do cabelo
	 * 
	 * public void swapNome(int k) { boolean menor; int j ;
	 * 
	 * for (int i = 1; i <= k; i++) { menor = true; j = i; while(j > 0 &&
	 * this.lista[j].getCorDoCabelo().compareTo(this.lista[j-1].getCorDoCabelo()) ==
	 * 0 && menor) {
	 * 
	 * if(this.lista[j].getNome().compareTo(this.lista[j-1].getNome()) < 0) {
	 * 
	 * swap(j,j-1);j--;
	 * 
	 * }else { menor = false; } } } }
	 */

	public Celula calcularPivo(Celula i, Celula j) {
		Celula pivo;
		int contador = 0;

		for (Celula tmp = i; tmp != j; tmp = tmp.proximo) {
			contador++;
		}

		pivo = i;

		for (int k = 0; k <= contador / 2; k++) {
			pivo = pivo.proximo;
		}

		return pivo;

	}

	public void quick() {
		quick(this.primeiro.proximo, this.ultimo);
	}

	public void quick(Celula esq, Celula dir) {
		Celula i = esq;
		Celula j = dir;

		Celula pivo = calcularPivo(i, j);

		MyIO.println("pivo" + pivo.elemento.getNome());

		while (j.proximo != i && i.anterior != j && i.anterior != j.proximo) {

			while (i.proximo != null && i.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) < 0) {
				i = i.proximo;
			}

			while (j.anterior != null && j.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) > 0) {
				j = j.anterior;
			}

			if (j.proximo != i && i.anterior != j && i.anterior != j.proximo) {
				swap(i, j);

				i = i.proximo;
				j = j.anterior;

			}
		}

		if (i != null && dir != null && (i.proximo != dir && dir.proximo != i && i != dir)) {

			quick(i, dir);
		}

		if (j != null && esq != null && (j.proximo != esq && esq.proximo != j && j != esq)) {

			quick(esq, j);
		}
	}

}

class Tp0304 {
	/*
	 * Funcao para testar se a string corresponde a 'FIM' Parametro str: string a
	 * ser analizada Retorno: true caso a string corresponda a 'FIM'
	 */
	public static boolean isFim(String str) {
		int tamanho = str.length();
		boolean resp = false;
		if (tamanho == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
			resp = true;
		}
		return resp;
	}

	/*
	 * Funcao para mudar o encoding ISO8859-1 para UTF-8 Paramentro str : string a
	 * ser convertida Retorno : dados no encoding UTF-8
	 */
	public static String converterEncoding(String str) throws Exception {

		byte[] isoBytes = str.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");
	}

	/*
	 * Funcao para colher o nome do arquivo contido na entrada
	 * 
	 * @param entrada: string contendo os dados da entrada
	 * 
	 * @return string contendo apenas o path do arquivo
	 */
	public static String colherPath(String entrada) {

		String path = ".";
		int i = 0;
		while (entrada.charAt(i) != '/') {
			i++;
		}

		for (int j = i; j < entrada.length(); j++) {
			path += entrada.charAt(j);
		}
		return path;
	}

	/*
	 * Funcao para colher a posicao de insercao ou remocao contidos na entrada
	 * 
	 * @param entrada: string contendo os dados da entrada
	 * 
	 * @return inteiro da posicao colhida
	 */
	public static int colherPosicao(String entrada) {

		String path = "";
		int posicao;

		path += entrada.charAt(3);
		path += entrada.charAt(4);

		posicao = Integer.parseInt(path);

		return posicao;
	}

	public static void main(String[] args) throws Exception {
		String str = "";
		String s;
		boolean fim = false;
		Lista l = new Lista();

		do {

			str = MyIO.readString();

			s = converterEncoding(str);

			fim = isFim(s);

			if (!fim) {

				Personagem p = new Personagem(s);

				l.inserirFim(p);
			}
		} while (!fim);

		l.quick();

		l.mostrar();

		// l.ordenarQuickParcial(0,l.getN() -1,10);

		// l.swapNome(10);
		//
	}
}
