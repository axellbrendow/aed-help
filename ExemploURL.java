import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ExemploURL {
	public static String vogals = "aeiouáéíóúàèìòùãõâêîôû";
	public static String consoants = "bcdfghjklmnpqrstvwxyz"; // BCDFGHJKLMNPQRSTVWXYZ
	public static String[] tags = new String[] { "<br>", "<table>" };
	
	public static int[] vogalsCount = new int[vogals.length()];
	public static int[] consoantsCount = new int[consoants.length()];
	public static int[] tagsCount = new int[tags.length];
	
	public static void imprimirQuantidadeDe(String str, int quantidade)
	{
		MyIO.print(str + "(" + quantidade + ") ");
	}
	
	public static String contarString(String str, String input, int[] arrayToUpdate, int index)
	{
		String newInput = input.replaceAll(str, "");
		int difference = input.length() - newInput.length();
		int count = difference / str.length();

		arrayToUpdate[index] += count;
		
		return newInput;
	}
	
	public static String contar(String letras, String input, int[] arrayToUpdate)
	{
		for (int i = 0; i < letras.length(); i++)
		{
			input = contarString("" + letras.charAt(i), input, arrayToUpdate, i);
		}
		
		return input;
	}
	
	public static void main(String[] args) throws Exception {
//		String name = "Jessica Jones";
//		String address = "http://maratona.crc.pucminas.br/series/Jessica_Jones.html";
		MyIO.setCharset("UTF-8");
		String name;
		String address;
		String line;
		URL url;
		BufferedReader br;
		
		while ( !( line = MyIO.readLine() ).startsWith("FIM") )
		{
			name = line;
			address = MyIO.readLine();

			try {
				url = new URL(address);
				br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				line = br.readLine();
				
				while (line != null)
				{
					line = line.replaceAll(" |\r|\n|\t", "");

					for (int i = 0; i < tags.length; i++)
					{
						line = contarString(tags[i], line, tagsCount, i); // Conta quantas vezes cada tag aparece
					}

					line = contar(vogals, line, vogalsCount); // Conta quantas vezes cada uma das vogais aparece
					contar(consoants, line, consoantsCount); // Conta quantas vezes cada uma das consoantes aparece
					
					line = br.readLine();
				}
				
				br.close();
			} catch (MalformedURLException excecao) {
				excecao.printStackTrace();
			} catch (IOException excecao) {
				excecao.printStackTrace();
			}
			// Começa a criar a saída
			for (int i = 0; i < vogals.length(); i++)
			{
				imprimirQuantidadeDe("" + vogals.charAt(i), vogalsCount[i]); // Imprime a vogal e sua quantidade
				vogalsCount[i] = 0; // Reseta a quantidade dessa vogal
			}
			
			int sum = 0;
			
			for (int i = 0; i < consoantsCount.length; i++)
			{
				sum += consoantsCount[i]; // Soma a quantidade de consoantes
				consoantsCount[i] = 0; // Reseta a quantidade dessa consoante
			}
			
			MyIO.print("consoante(" + sum + ") "); // Imprime a quantidade de consoantes

			for (int i = 0; i < tags.length; i++)
			{
				imprimirQuantidadeDe(tags[i], tagsCount[i]); // Imprime a tag e sua quantidade
				tagsCount[i] = 0; // Reseta a quantidade dessa tag
			}
			
			MyIO.println(name); // Imprime o nome da página
		}
	}
}
