package processor.pipeline;

import generic.Simulator;
import processor.Processor;

public class RegisterWrite {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	
	public RegisterWrite(Processor containingProcessor,EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	
	public void performRW()
	{
		if(MA_RW_Latch.isRW_enable())
		{
			System.out.println(MA_RW_Latch.getInstruction());
			int data;
			if(MA_RW_Latch.getControl_Unit().isLd){
				data = MA_RW_Latch.getLd_Result();
			}else{
				data = MA_RW_Latch.getALU_Result();
			}

			String Instruction_Binary = String.format("%32s", Integer.toBinaryString(MA_RW_Latch.getInstruction())).replace(' ', '0');

			int rd;
			if(MA_RW_Latch.getControl_Unit().isImmediate){
				rd = Integer.parseInt(Instruction_Binary.substring(10, 15), 2);
			}else{
				rd = Integer.parseInt(Instruction_Binary.substring(15, 20), 2);
			}
			

			if(MA_RW_Latch.getControl_Unit().isWb){
				containingProcessor.getRegisterFile().setValue(rd, data);
			}

			if(MA_RW_Latch.Interlock!=-1){
				if(MA_RW_Latch.Interlock>=0){
					containingProcessor.setDataInterlock(MA_RW_Latch.Interlock, false);
				}else{
					containingProcessor.setDataInterlock(MA_RW_Latch.Interlock+33, false);
					containingProcessor.setDataInterlock(31, false);
				}
				// containingProcessor.setDataInterlock(MA_RW_Latch.Interlock, false);
				// if(MA_RW_Latch.getControl_Unit().isDiv)
				// containingProcessor.setDataInterlock(31, false);
				MA_RW_Latch.Interlock=-1;
			}
			if(MA_RW_Latch.getControl_Unit().isWb){
				if(MA_RW_Latch.getControl_Unit().isDiv){
					MA_RW_Latch.Interlock=rd-33;
				}else{
					MA_RW_Latch.Interlock=rd;
				}
				
				// MA_RW_Latch.Interlock=rd;
			}
			// System.out.println(MA_RW_Latch.Interlock);

			if(MA_RW_Latch.getControl_Unit().isEnd){
				EX_MA_Latch.setMA_enable(false);
				Simulator.setSimulationComplete(true);
			}
			// MA_RW_Latch.setRW_enable(false);
			// IF_EnableLatch.setIF_enable(true);
		}
	}

}
