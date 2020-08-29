class Palindromo {
	public static boolean Palinjava(String str) {
		boolean is = true;
		int comeco = 0;
		int fim = (str.length() - 1);
		int fimindice = fim;
		for (int i = 0; i < fimindice && comeco < fim; i++) {
			if (str.charAt(comeco) != str.charAt(fim)) {
				is = false;
				i = str.length();
			} else {
				comeco++;
				fim--;
			}

		}
		return is;
	}

	public static void main(String[] args) {

		String line = MyIO.readLine();

		while (!line.contains("FIM")) {
			boolean is = Palinjava(line);
			if (is) {
				MyIO.println("SIM");
			} else {
				MyIO.println("NAO");
			}
			line = MyIO.readLine();
		}
	}
}
