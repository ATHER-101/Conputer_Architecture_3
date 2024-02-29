package processor.pipeline;

public class EX_MA_LatchType {
	
	boolean MA_enable;
	int ALU_Result;
	int RS1;
	int instruction;
	Control_Unit control_Unit;
	
	public EX_MA_LatchType()
	{
		MA_enable = false;
	}

	public boolean isMA_enable() {
		return MA_enable;
	}

	public void setMA_enable(boolean mA_enable) {
		MA_enable = mA_enable;
	}

	public int getALU_Result() {
		return ALU_Result;
	}

	public void setALU_Result(int alu_Result) {
		ALU_Result = alu_Result;
	}

	public int getRS1() {
		return RS1;
	}

	public void setRS1(int rs1) {
		RS1 = rs1;
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
