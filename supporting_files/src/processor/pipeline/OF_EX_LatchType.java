package processor.pipeline;

public class OF_EX_LatchType {
	
	boolean EX_enable;
	int branch_Target_17;
	int branch_Target_22;
	int A;
	int B;
	int RS1;
	int instruction;
	Control_Unit control_Unit;
	
	public OF_EX_LatchType()
	{
		EX_enable = false;
	}

	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}

	public int getBranch_Target_17() {
		return branch_Target_17;
	}

	public void setBranch_Target_17(int _branch_Target_17) {
		branch_Target_17 = _branch_Target_17;
	}

	public int getBranch_Target_22() {
		return branch_Target_22;
	}

	public void setBranch_Target_22(int _branch_Target_22) {
		branch_Target_22 = _branch_Target_22;
	}

	public int getA() {
		return A;
	}

	public void setA(int a) {
		A = a;
	}

	public int getB() {
		return B;
	}

	public void setB(int b) {
		B = b;
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
