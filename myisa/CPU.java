import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class CPU
{
   //Hardware Component Class variables
   private ALU alu;
   private Registers registers;   
   private Memory memory;

   //IO devices
   private Scanner file;
   private Scanner keyboard;

   //Hardware devices
   private int programCounter;
   private ArrayList<ArrayList<String>> programMemory;
   private HashMap<String, Integer> instructionMap;   
   private Stack<Integer> stack;

   //Instruction variables
   private int instruction;
   private String opcode, op1, op2, op3;


   public CPU(Memory memory)
   {
      alu = new ALU();
      registers = new Registers();
      this.memory = memory;

      keyboard = new Scanner(in);
      programMemory = new ArrayList<ArrayList<String>>();
      instructionMap = new HashMap<String, Integer>();
      stack = new Stack<Integer>();      

      loadInstMap();
      programCounter = 0;
   }
      
   public int execute(String fileName)
   {
      //decoded variable information
      int reg1, reg2, reg3;
      int answer;
      boolean writeBack;

      programCounter = loadInstructions(fileName);
      if(programCounter == -1)
         return 2;
      if(programCounter == -2)
         return 3;
      for(int PC = 0; PC<programCounter; PC++)
      {
         answer = 0;
         writeBack = true;
         //STAGES 1 of the MIPS Pipeline
         fetchAndDecode(PC);

         out.println(opcode + " " + op1 + " " + op2 + " " + op3); 
         registers.printRegisters();
         //STAGE 2 of the MIPS pipeline
         instruction = instructionMap.get(opcode);
         reg1 = registers.getValue(op1);
         reg2 = registers.getValue(op2);
         reg3 = registers.getValue(op3);
         

         //Stage 3 and/or 4 of the MIPS Pipeline, depending on the instruction
         switch(instruction)
         {
            case 0: 
               answer = alu.add(reg2,reg3);
               break;
            case 1:
               answer = alu.sub(reg2, reg3);
               break;
            case 2:
               answer = alu.mul(reg2, reg3);
               break;
            case 3:
               answer = alu.div(reg2, reg3);
               break;
            case 4:
               answer = Integer.parseInt(op2);
               break;
            case 5:
               answer =  memory.load(reg2);
               break;
            case 6: 
               memory.store(reg2,reg1); 
               break;
            case 7: 
               out.print("Enter an integer: "); 
               answer = keyboard.nextInt();
               break;
            case 8: 
               out.println(reg1);
               break;
            case 9:
               
               break;
            case 10:
               break;
            //WHY THE PC-- & reg1 - 1
            //Since the loop counter automatically increments by 1 every time
            //that may alter the jump distance. For this to be fixed we must
            //alter the PC by -1 when jumps are taken
            case 11: 
               PC += reg1; 
               writeBack = false;
               PC--;
               break;
            case 12: 
               PC += alu.beq(reg1-1, reg2, reg3);               
               writeBack = false;
               break;
            case 13:
               PC += alu.bne(reg1-1, reg2, reg3);
               writeBack = false;
               break;
            case 14:
               PC += alu.bgt(reg1-1, reg2, reg3);
               writeBack = false;
               break;
            case 15:
               PC += alu.bge(reg1-1, reg2, reg3);
               writeBack = false;
               break;
            case 16:
               PC += alu.blt(reg1-1, reg2, reg3);
               writeBack = false;
               break;
            case 17:
               PC += alu.ble(reg1-1, reg2, reg3);
               writeBack = false;
               break;
            case 18:
               answer = alu.inc(reg1);
               break;
            case 19:
               answer = alu.dec(reg1);
               break;
            case 20:
               break;
            default:
               break;
         }

         //Stage 5 of the MIPS Pipeline - Register writeback
         if(writeBack)
            registers.setValue(op1,answer);
      }
      return 0;
   }

   private void fetchAndDecode(int pc)
   {
      ArrayList<String> inst = programMemory.get(pc);
      opcode = inst.get(0);
      op1 = inst.get(1);
      op2 = inst.get(2);
      op3 = inst.get(3);
   }

   private int loadInstructions(String fileName)
   {
      int PC = 0;
      String line;
      try
      {
         file=new Scanner(new File(fileName));
      }
      catch(FileNotFoundException f)
      {
         return -1;
      }
      
      while(file.hasNextLine())
      {
         line = file.nextLine();
         programMemory.add(
            new ArrayList<String>(
               Arrays.asList(
                  line.split(" "))));
         if(!validateInstruction(programMemory.get(PC)))
            return -2;
         PC++;
      }
      return PC;
   }

   private boolean validateInstruction(ArrayList<String> inst)
   {
      String regNames = "AFBCDEHLIXIYSPC0";
      String op0, op1, op2, op3;
      op0 = inst.get(0);
      op1 = inst.get(1);
      op2 = inst.get(2);
      op3 = inst.get(3);

      if(inst.size() > 4)
         return false;
      if(!instructionMap.containsKey(op0))
         return false;
      if(!regNames.contains(op1))
         return false;
      try  
      {  
         int i = Integer.parseInt(op2);
         return true;  
      }  
      catch(NumberFormatException nfe)  
      {  
         if(!regNames.contains(op2))
            return false;  
      }
      if(!regNames.contains(op3))
         return false;

      return true;

   }

   private void loadInstMap()
   {
      instructionMap.put("add",0);
      instructionMap.put("sub",1);
      instructionMap.put("mul",2);
      instructionMap.put("div",3);
      instructionMap.put("ldi",4);
      instructionMap.put("ld",5);
      instructionMap.put("st",6);
      instructionMap.put("in",7);
      instructionMap.put("out",8);
      instructionMap.put("push", 9);
      instructionMap.put("pop", 10);
      instructionMap.put("j", 11);
      instructionMap.put("beq", 12);
      instructionMap.put("bne", 13);
      instructionMap.put("bgt", 14);
      instructionMap.put("bge", 15);
      instructionMap.put("blt", 16);
      instructionMap.put("ble", 17);
      instructionMap.put("inc", 18);
      instructionMap.put("dec", 19);
   }

}
