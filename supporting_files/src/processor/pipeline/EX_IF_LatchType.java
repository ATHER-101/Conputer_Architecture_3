package processor.pipeline;

public class EX_IF_LatchType {
	
	boolean Branch_taken;
	int Branch_PC;
	boolean nextBranch_taken;
	int nextBranch_PC;
	
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

	public void setnextBranchTaken(boolean branchTaken){
		nextBranch_taken = branchTaken;
	}

	public boolean isnextBranchTaken(){
		return nextBranch_taken;
	}

	public void setnextBranch_PC(int branch_PC) {
		nextBranch_PC = branch_PC;
	}

	public int getnextBranch_PC(){
		return nextBranch_PC;
	}

}
