package processor.pipeline;

public class MA_RW_LatchType {
	
	boolean RW_enable;
	int Ld_Result;
	int ALU_Result;
	int instruction;
	Control_Unit control_Unit;
	
	public MA_RW_LatchType()
	{
		RW_enable = false;
	}

	public boolean isRW_enable() {
		return RW_enable;
	}

	public void setRW_enable(boolean rW_enable) {
		RW_enable = rW_enable;
	}

	public int getLd_Result() {
		return Ld_Result;
	}

	public void setLd_Result(int ld_Result) {
		Ld_Result = ld_Result;
	}

	public int getALU_Result() {
		return ALU_Result;
	}

	public void setALU_Result(int alu_Result) {
		ALU_Result = alu_Result;
	}

	public int getInstruction() {
		return instruction;
	}

	public void setInstruction(int _instruction) {
		instruction = _instruction;
	}

	public Control_Unit getControl_Unit() {
		return control_Unit;
	}

	public void setControl_Unit(Control_Unit control_unit) {
		control_Unit = control_unit;
	}

}
