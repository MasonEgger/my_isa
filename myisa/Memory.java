
import java.util.HashMap;

public class Memory
{
   private HashMap<Integer,Integer> memory;

   public Memory()
   {
      memory = new HashMap<Integer,Integer>();
      loadMemory();
   }

   public int load(int location)
   {
      return memory.get(location);
   }

   public void store(int location, int value)
   {
      memory.put(location,value);
   }

   private void loadMemory()
   {
      for(int i = -32768; i<32768; i++)
         memory.put(i,0);
   }

}
