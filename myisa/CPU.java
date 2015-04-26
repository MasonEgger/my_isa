import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class CPU
{
   private ALU alu;
   private Registers registers;   
   private Scanner file;

   public CPU()
   {
      alu = new ALU();
      registers = new Registers();
   }
      
   public int execute(String fileName)
   {
      if(!loadInstructions(fileName))
         return 2;
      while(file.hasNextLine())
      {
         if(!fetchAndDecode())
            return 1; //invalid instruction
      }
      return 0;
   }

   private boolean fetchAndDecode()
   {
      String line = file.nextLine();
      String[] inst = line.split(" ");
      if(!validateInstruction(inst))
         return false;
      return true;      
   }

   private boolean loadInstructions(String fileName)
   {
      try
      {
         file=new Scanner(new File(fileName));
      }
      catch(FileNotFoundException f)
      {
         return false;
      }
        
      return true;
   }

   private boolean validateInstruction(String[] inst)
   {
      return true;
   }

}
