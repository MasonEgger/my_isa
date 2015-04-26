import java.util.HashMap;
import java.util.ArrayList;

public class Registers
{
   private int[][] registers;
   public Registers()
   {
      registers = new int[7][4];
   }

   public int getValue(String addr)
   {
      if(addr.equals("A"))
      {
         return registers[0][0];     
      }
      else if(addr.equals("B"))
      {
         return registers[1][0];
      }
      else if(addr.equals("C"))
      {
         return registers[1][1];
      }
      else if(addr.equals("D"))
      {
         return registers[2][0];
      }
      else if(addr.equals("E"))
      {
         return registers[2][1];
      }
      else if(addr.equals("F"))
      {
         return registers[0][1];
      }
      else if(addr.equals("H"))
      {
         return registers[3][0];
      }
      else if(addr.equals("L"))
      {
         return registers[3][1];
      }
      else if(addr.equals("AF"))
      {
         return registers[0][0] + registers[0][1];
      }
      else if(addr.equals("BC"))
      {
         return registers[1][0] + registers[1][1];
      }
      else if(addr.equals("DE"))
      {
         return registers[2][0] + registers[2][1];
      }
      else if(addr.equals("HL"))
      {
         return registers[3][0] + registers[3][1];
      }
      else if(addr.equals("IX"))
      {
         return registers[4][0] + registers[4][1];
      }
      else if(addr.equals("IY"))
      {
         return registers[4][2] + registers[4][3];
      }
      else if(addr.equals("SP"))
      {
         return registers[5][0] + registers[5][1];
      }
      else if(addr.equals("SP"))
      {
         return registers[6][0] + registers[6][1];
      }
      return 0;
   }   

   private int[] adjustData(int value)
   {
      int[] data = new int[2];
      int lowLowerBound = -128;
      int lowUpperBound = 127;
      int highLowerBound = -32640;
      int highUpperBound = 32639;
      if(value > lowUpperBound)
      {
         data[0] = lowUpperBound;
         if(value - lowUpperBound > highUpperBound)
            data[1] = highUpperBound;
         else
            data[1] = value - lowUpperBound;
      }
      else if(value < lowLowerBound)
      {
         data[0] = lowLowerBound;
         if(value - lowLowerBound < highLowerBound)
            data[1] = highLowerBound;
         else
            data[1] = value - lowLowerBound;
      }
      else
      {
         data[0] = value;
         data[1] = 0;
      }
      return data;

   }
   public void setValues(String addr, int value)
   {
      int[] data = adjustData(value);
      int low = data[0];
      int high = data[1];

      if(addr.equals("A"))
      {
         registers[0][0]= low;
      }
      else if(addr.equals("B"))
      {
         registers[1][0] = low;
      }
      else if(addr.equals("C"))
      {
         registers[1][1] = low;
      }
      else if(addr.equals("D"))
      {
         registers[2][0] = low;
      }
      else if(addr.equals("E"))
      {
         registers[2][1] = low;
      }
      else if(addr.equals("F"))
      {
         registers[0][1] = low;
      }
      else if(addr.equals("H"))
      {
         registers[3][0] = low;
      }
      else if(addr.equals("L"))
      {
         registers[3][1] = low;
      }
      else if(addr.equals("AF"))
      {
         registers[0][0] = low;
         registers[0][1] = high;
      }
      else if(addr.equals("BC"))
      {         
         registers[1][0] = low;
         registers[1][1] = high;
      }
      else if(addr.equals("DE"))
      {
         registers[2][0] = low;
         registers[2][1] = high;
      }
      else if(addr.equals("HL"))
      {
         registers[3][0] = low;
         registers[3][1] = high;
      }
      else if(addr.equals("IX"))
      {
         registers[4][0] = low;
         registers[4][1] = high;
      }
      else if(addr.equals("IY"))
      {
         registers[4][2] = low;
         registers[4][3] = high;
      }
      else if(addr.equals("SP"))
      {
         registers[5][0] = low;
         registers[5][1] = high;
      }
      else if(addr.equals("PC"))
      {
         registers[6][0] = low;
         registers[6][1] = high;
      }
   }
}
