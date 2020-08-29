import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Arquivo {

  public static void EscreveNumeros(int n) {

    try {
      RandomAccessFile file = new RandomAccessFile("Escreve.txt", "rw");

      for (int i = 0; i < n; i++) {
        file.writeFloat(MyIO.readFloat());
      }

      file.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void LerInvertido(int num) {
    // n = 3
    // pos do ultimo float = (n - 1) * sizeof(float)
    // 0000|0000|0000
    // offset = (3 - 1) * 4 = 2 * 4 = 8
    // offset = 4

    try {
      RandomAccessFile file = new RandomAccessFile("Escreve.txt", "r");
      DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.UK);
      format.applyPattern("0.###");

      for (long offset = (num - 1) * Float.BYTES; offset >= 0; offset -= Float.BYTES) {
        file.seek(offset);
        MyIO.println(format.format(file.readFloat()));
      }

      file.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {

    int num = MyIO.readInt();

    EscreveNumeros(num);
    LerInvertido(num);

  }
}
