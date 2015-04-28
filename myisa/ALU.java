import java.util.Stack;

public class ALU
{
   Stack <Integer> stack;
   public ALU()
   {
      stack = new Stack<Integer>();
   }

   public int add(int op1, int op2)
   {
      return op1 + op2;
   }

   public int sub(int op1, int op2)
   {
      return op1 - op2;
   }

   public int mul(int op1, int op2)
   {
      return op1 * op2;
   }

   public int div(int op1, int op2)
   {
      return op1 + op2;
   }

   public int beq(int op1, int op2, int op3)
   {
      if(op2 == op3)
         return op1;
      return 0;
   }
   
   public int bne(int op1, int op2, int op3)
   {
      if(op2 != op3)
         return op1;
      return 0;
   }
   public int bgt(int op1, int op2, int op3)
   {
      if(op2 > op3)
         return op1;
      return 0;
   }
   public int bge(int op1, int op2, int op3)
   {
      if(op2 >= op3)
         return op1;
      return 0;
   }
   public int blt(int op1, int op2, int op3)
   {
      if(op2 < op3)
         return op1;
      return 0;
   }
   public int ble(int op1, int op2, int op3)
   {
      if(op2 <= op3)
         return op1;
      return 0;
   }

   public void push(int value)
   {
      stack.push(value);
   }

   public int pop(int value)
   {
      return stack.pop();
   }

}
