import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class J10869 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] str = br.readLine().split(" ");
    int a = Integer.parseInt(str[0]);
    int b = Integer.parseInt(str[1]);

    System.out.println(a + b);
    System.out.println(a - b);
    System.out.println(a * b);
    System.out.println(a / b);
    System.out.println(a % b);
  }
}