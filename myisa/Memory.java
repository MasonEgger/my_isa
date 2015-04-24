package myisa;

import java.util.HashMap;

public class Memory
{
   private HashMap<Byte,Integer> memory;

   public Memory()
   {
      memory = new HashMap<Byte,Integer>();
   }

   private int load(byte location)
   {
      return memory.get(location);
   }

   private void store(byte location, int value)
   {
      memory.put(location,value);
   }
}
