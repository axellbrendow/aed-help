public class TP01Q01 {

	public static boolean isFim(String str) {
		return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');

	}

	public static boolean isPalindromo(String str) {
		int j = str.length() - 1;
		boolean isPalindromo = true;
 
		for(int i = 0; isPalindromo && i < j; i++,j--) {
			isPalindromo = str.charAt(i) == str.charAt(j);		
		} 
		return isPalindromo;
	}

	public static void main(String[] args) {

		String[] str = new String[1000];
		int numLinha = 0;

		do {
			str[numLinha] = MyIO.readLine();
			
		} while (!isFim(str[numLinha++]));
		numLinha--;

		for (int i = 0; i < numLinha; i++) {
			
			if(isPalindromo(str[i])) {
				MyIO.println("SIM");
			} else {
				MyIO.println("NAO");
			}
		}
				
	}
}
