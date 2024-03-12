package processor.pipeline;

import processor.Processor;

public class OperandFetch {
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;

	public OperandFetch(Processor containingProcessor,IF_EnableLatchType iF_EnableLatch, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch) {
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
	}

	public void performOF() {

		if (IF_OF_Latch.isOF_enable()) {
			System.out.println(IF_OF_Latch.getInstruction());
			// TODO
			int instruction = IF_OF_Latch.getInstruction();

			String Instruction_Binary = String.format("%32s", Integer.toBinaryString(instruction)).replace(' ', '0');
			String opCode = Instruction_Binary.substring(0, 5);

			Control_Unit control_Unit = new Control_Unit(opCode);

			int immx = binaryStringToSignedInt(Instruction_Binary.substring(15, 32));

			int branch_Target_17 = containingProcessor.getRegisterFile().getProgramCounter() + binaryStringToSignedInt(Instruction_Binary.substring(15, 32)) - 1;
			int branch_Target_22 = containingProcessor.getRegisterFile().getProgramCounter() + binaryStringToSignedInt(Instruction_Binary.substring(10, 32)) - 1;

			int rs1 = Integer.parseInt(Instruction_Binary.substring(5, 10), 2);
			int rs2 = Integer.parseInt(Instruction_Binary.substring(10, 15), 2);

			OF_EX_Latch.setControl_Unit(control_Unit);
			OF_EX_Latch.setBranch_Target_17(branch_Target_17);
			OF_EX_Latch.setBranch_Target_22(branch_Target_22);
			OF_EX_Latch.setInstruction(instruction);

			int op1 = containingProcessor.getRegisterFile().getValue(rs1);
			int op2 = containingProcessor.getRegisterFile().getValue(rs2);

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
			
			if(control_Unit.isEnd){
				IF_EnableLatch.setIF_enable(false);
			}
			// IF_OF_Latch.setOF_enable(false);
			OF_EX_Latch.setEX_enable(true);
		}
	}

	public int binaryStringToSignedInt(String binaryString) {
        // Check if the binary string is representing a negative number
        boolean isNegative = binaryString.charAt(0) == '1';
        
        // Convert binary string to integer
        int unsignedInt = Integer.parseInt(binaryString, 2);
        
        // If it's a negative number, convert it to its two's complement representation
        if (isNegative) {
            // Calculate the two's complement
            int numBits = binaryString.length();
            int mask = (1 << numBits) - 1; // Create a mask with all 1s of the same length as the binary string
            unsignedInt = unsignedInt - mask - 1;
        }
        
        return unsignedInt;
    }

}
