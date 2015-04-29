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
      int result = cpu.execute(fileName);
      if(result == 0)
         out.println("Program successfully terminated");
      else if(result == 2)
         out.println("Program exited with ERROR: FILE NOT FOUND");
      else if(result == 3)
         out.println("Program exited with ERROR: INVALID INSTRUCTION IN FILE");
   }
}
