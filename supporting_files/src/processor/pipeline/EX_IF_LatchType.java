package processor.pipeline;

public class EX_IF_LatchType {
	
	boolean Branch_taken;
	int Branch_PC;
	
	public EX_IF_LatchType()
	{
		
	}

	public void setBranchTaken(boolean branchTaken){
		Branch_taken = branchTaken;
	}

	public boolean isBranchTaken(){
		return Branch_taken;
	}

	public void setBranch_PC(int branch_PC) {
		Branch_PC = branch_PC;
	}

	public int getBranch_PC(){
		return Branch_PC;
	}

}
