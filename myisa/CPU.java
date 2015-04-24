package myisa;

import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class CPU
{
   private ALU alu;
   private Registers registers;   
   private Scanner file;

   public CPU(String fileName)
   {
      alu = new ALU();
      registers = new Registers();
      try
      {
         file=new Scanner(new File(fileName));
      }
      catch(FileNotFoundException f)
      {
         out.println("No file found");
      }
   }
   
   public void fetchAndDecode()
   {
   }

}
