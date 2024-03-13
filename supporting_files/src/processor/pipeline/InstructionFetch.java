package processor.pipeline;

import processor.Processor;

public class InstructionFetch {
	
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch, IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}
	
	public void performIF()
	{
		if(IF_EnableLatch.isIF_enable())
		{
			int currentPC;
			if(EX_IF_Latch.isBranchTaken()){
				currentPC = EX_IF_Latch.getBranch_PC();
			}else{
				currentPC = containingProcessor.getRegisterFile().getProgramCounter();
			}
			
			System.out.println(currentPC);

			EX_IF_Latch.setBranchTaken(false);
			
			int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
			IF_OF_Latch.setInstruction(newInstruction);
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);

			EX_IF_Latch.setBranchTaken(EX_IF_Latch.isnextBranchTaken());
			EX_IF_Latch.setBranch_PC(EX_IF_Latch.getnextBranch_PC());
			EX_IF_Latch.setnextBranchTaken(false);
			
			System.out.println(IF_OF_Latch.getInstruction());
			for(int i=0;i<32;i++){
				System.out.print(i+":"+containingProcessor.getDataInterlock(i)+"   ");
			}
			System.out.println();
			System.out.println();
			// IF_EnableLatch.setIF_enable(false);
			IF_OF_Latch.setOF_enable(true);
		}
	}

}
