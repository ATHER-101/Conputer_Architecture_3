package processor.pipeline;

import processor.Processor;

public class MemoryAccess {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	
	public MemoryAccess(Processor containingProcessor, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
	}
	
	public void performMA()
	{
		if(EX_MA_Latch.MA_enable){

			int mar = EX_MA_Latch.getALU_Result();
			int mdr = EX_MA_Latch.getRS1();

			if(EX_MA_Latch.getControl_Unit().isSt){
				containingProcessor.getMainMemory().setWord(mar, mdr);
			}else if(EX_MA_Latch.getControl_Unit().isLd){
				MA_RW_Latch.setLd_Result(containingProcessor.getMainMemory().getWord(mar));
			}

			MA_RW_Latch.setALU_Result(EX_MA_Latch.getALU_Result());
			MA_RW_Latch.setInstruction(EX_MA_Latch.getInstruction());
			MA_RW_Latch.setControl_Unit(EX_MA_Latch.getControl_Unit());

			EX_MA_Latch.setMA_enable(false);
			MA_RW_Latch.setRW_enable(true);
		}
	}

}
