import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class CPU
{
   private ALU alu;
   private Registers registers;   
   private Scanner file;
   private Scanner keyboard;
   private Memory memory;
   private int programCounter;
   private ArrayList<ArrayList<String>> programMemory;
   private HashMap<String, Integer> instructionMap;   

   private int instruction;
   private String opcode, op1, op2, op3;
   private int immediate;
   private int reg1, reg2, reg3;

   public CPU(Memory memory)
   {
      alu = new ALU();
      registers = new Registers();
      keyboard = new Scanner(in);
      this.memory = memory;
      programMemory = new ArrayList<ArrayList<String>>();
      instructionMap = new HashMap<String, Integer>();
      loadInstMap();
      programCounter = 0;
   }
      
   public int execute(String fileName)
   {
      programCounter = loadInstructions(fileName);
      if(programCounter == -1)
         return 2;
      if(programCounter == -2)
         return 3;
      for(int PC = 0; PC<programCounter; PC++)
      {
         //STAGES 1 of the MIPS Pipeline
         fetchAndDecode(PC);
         if(instruction == 4)
            out.println(opcode + " " + op1 + " " + immediate + " " + op3);
         else
            out.println(opcode + " " + op1 + " " + op2 + " " + op3); 

         //STAGE 2 of the MIPS pipeline
         instruction = instructionMap.get(opcode);
         reg1 = registers.getValue(op1);
         reg2 = registers.getValue(op2);
         reg3 = registers.getValue(op3);

         //Stage 3 and/or 4 of the MIPS Pipeline, depending on the instruction
         switch(instruction)
         {
            case 0: 
               out.println("op1 is: " + op1);
               registers.setValue(op1, alu.add(reg2,reg3));
               break;
            case 1:
               registers.setValue(op1, alu.sub(reg2, reg3));
               break;
            case 2:
               registers.setValue(op1, alu.mul(reg2, reg3));
               break;
            case 3:
               registers.setValue(op1, alu.div(reg2, reg3));
               break;
            case 4:
               registers.setValue(op1, immediate);
               break;
            case 5:
               registers.setValue(op1, memory.load(reg2));
               break;
            case 6: 
               memory.store(registers.getValue(op2),reg1); 
               break;
            case 7: 
               out.print("Enter an integer: "); 
               registers.setValue(op1,keyboard.nextInt()); 
               break;
            case 8: 
               out.println(reg1);
               break;
            case 9:
               break;
            case 10:
               break;
            case 11: 
               PC += reg1; 
               break;
            case 12: 
               PC += alu.beq(reg1, reg2, reg3);
               break;
            default:
         }
      }
      return 0;
   }

   private void fetchAndDecode(int pc)
   {
      ArrayList<String> inst = programMemory.get(pc);
      //if(!validateInstruction(inst))  VALIDATE IN LOAD
      //   return false;
      opcode = inst.get(0);
      op1 = inst.get(1);
      if(instruction == 4)
         immediate = Integer.parseInt(inst.get(2));
      else
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
         programMemory.add(new ArrayList<String>(Arrays.asList(line.split(" "))));
         PC++;
      }
      return PC;
   }

   private boolean validateInstruction(ArrayList<String> inst)
   {
      return true;
   }

   private void loadInstMap()
   {
      instructionMap.put("add",0);
      instructionMap.put("sub",1);
      instructionMap.put("mul",2);
      instructionMap.put("div",3);
      instructionMap.put("li",4);
      instructionMap.put("ld",5);
      instructionMap.put("st",6);
      instructionMap.put("in",7);
      instructionMap.put("out",8);
      instructionMap.put("push", 9);
      instructionMap.put("pop", 10);
      instructionMap.put("j", 11);
      instructionMap.put("beq", 12);
   }

}
