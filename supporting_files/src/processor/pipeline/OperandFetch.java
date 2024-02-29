package processor.pipeline;

import processor.Processor;

public class OperandFetch {
	Processor containingProcessor;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;

	public OperandFetch(Processor containingProcessor, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch) {
		this.containingProcessor = containingProcessor;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
	}

	public void performOF() {
		if (IF_OF_Latch.isOF_enable()) {
			// TODO
			int instruction = IF_OF_Latch.getInstruction();

			String Instruction_Binary = String.format("%32s", Integer.toBinaryString(instruction)).replace(' ', '0');
			String opCode = Instruction_Binary.substring(0, 5);

			Control_Unit control_Unit = new Control_Unit(opCode);

			int immx = Integer.parseInt(Instruction_Binary.substring(15, 32), 2);

			int branch_Target_17 = containingProcessor.getRegisterFile().getProgramCounter() + Integer.parseInt(Instruction_Binary.substring(15, 32), 2) - 1;
			int branch_Target_22 = containingProcessor.getRegisterFile().getProgramCounter() + Integer.parseInt(Instruction_Binary.substring(10, 32), 2) - 1;

			int rs1 = Integer.parseInt(Instruction_Binary.substring(5, 10), 2);
			int rs2 = Integer.parseInt(Instruction_Binary.substring(10, 15), 2);
			// int rd = Integer.parseInt(Instruction_Binary.substring(15, 20), 2);

			// if(!control_Unit.isImmediate){
			// 	System.out.println("Instruction:"+Instruction_Binary+"  OpCode:"+opCode+"  isImmediate:"+control_Unit.isImmediate+"  Immediate:"+immx+"  branch_Target_17:"+branch_Target_17+"  branch_Target_22:"+branch_Target_22+"  rs1:"+rs1+"  rs2:"+rs2+"  rd:"+rd);
			// }
			// System.out.println();

			OF_EX_Latch.setControl_Unit(control_Unit);
			OF_EX_Latch.setBranch_Target_17(branch_Target_17);
			OF_EX_Latch.setBranch_Target_22(branch_Target_22);
			OF_EX_Latch.setInstruction(instruction);

			int op1 = containingProcessor.getRegisterFile().getValue(rs1);
			int op2 = containingProcessor.getRegisterFile().getValue(rs2);
			// if(control_Unit.isSt || control_Unit.isBeq || control_Unit.isBne || control_Unit.isBgt || control_Unit.isBlt){
			// 	op2 = containingProcessor.getRegisterFile().getValue(rd);
			// }else{
			// 	op2 = containingProcessor.getRegisterFile().getValue(rs2);
			// }

			if(control_Unit.isSt){
				OF_EX_Latch.setA(op2);
			}else{
				OF_EX_Latch.setA(op1);
			}

			OF_EX_Latch.setRS1(op1);
			
			if(control_Unit.isImmediate){
				OF_EX_Latch.setB(immx);
			}else{
				OF_EX_Latch.setB(op2);
			}

			// //test
			// System.out.println("Instruction:"+OF_EX_Latch.getInstruction()+"  branch_Target_17:"+OF_EX_Latch.getBranch_Target_17()+"  branch_Target_22:"+OF_EX_Latch.getBranch_Target_22()+"  RS1:"+OF_EX_Latch.getRS1()+"  A:"+OF_EX_Latch.getA()+"  B:"+OF_EX_Latch.getB());
			// //test
			// System.out.println("isSub:"+OF_EX_Latch.getControl_Unit().isSub+"  isWb:"+OF_EX_Latch.getControl_Unit().isWb);
			
			IF_OF_Latch.setOF_enable(false);
			OF_EX_Latch.setEX_enable(true);
		}
	}

}
