package processor.pipeline;

import processor.Processor;

public class Execute {
	Processor containingProcessor;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public Execute(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}
	
	public void performEX()
	{
		if(OF_EX_Latch.isEX_enable()){
			
			Flags flags = new Flags();

			ALU alu = new ALU(OF_EX_Latch.getA(), OF_EX_Latch.getB(), OF_EX_Latch.getControl_Unit(),flags,containingProcessor);
			EX_MA_Latch.setALU_Result(alu.getALU_Result());

			if(OF_EX_Latch.getControl_Unit().isBeq && flags.getE()){
				EX_IF_Latch.setBranchTaken(true);
				EX_IF_Latch.setBranch_PC(OF_EX_Latch.getBranch_Target_17());
			}
			else if(OF_EX_Latch.getControl_Unit().isBne && flags.getNE()){
				EX_IF_Latch.setBranchTaken(true);
				EX_IF_Latch.setBranch_PC(OF_EX_Latch.getBranch_Target_17());
			}
			else if(OF_EX_Latch.getControl_Unit().isBgt && flags.getGT()){
				EX_IF_Latch.setBranchTaken(true);
				EX_IF_Latch.setBranch_PC(OF_EX_Latch.getBranch_Target_17());
			}
			else if(OF_EX_Latch.getControl_Unit().isBlt && flags.getLT()){
				EX_IF_Latch.setBranchTaken(true);
				EX_IF_Latch.setBranch_PC(OF_EX_Latch.getBranch_Target_17());
			}
			else if(OF_EX_Latch.getControl_Unit().isJmp){
				EX_IF_Latch.setBranchTaken(true);
				EX_IF_Latch.setBranch_PC(OF_EX_Latch.getBranch_Target_22());
			}

			EX_MA_Latch.setRS1(OF_EX_Latch.getRS1());
			EX_MA_Latch.setInstruction(OF_EX_Latch.getInstruction());
			EX_MA_Latch.setControl_Unit(OF_EX_Latch.getControl_Unit());

			OF_EX_Latch.setEX_enable(false);
			EX_MA_Latch.setMA_enable(true);
		}
	}

}
