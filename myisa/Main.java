import static java.lang.System.*;
import java.util.Scanner;

public class Main
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(in);
      Memory mem = new Memory();
      CPU cpu = new CPU(mem);

      out.print("Enter the name of the file you wish to execute: ");
      String fileName = kb.nextLine();
      cpu.execute(fileName);
   }
}
