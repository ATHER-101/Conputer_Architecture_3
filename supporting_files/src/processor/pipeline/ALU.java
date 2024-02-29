package processor.pipeline;

class Flags {
    boolean E;
    boolean NE;
    boolean GT;
    boolean LT;

    public void setE(boolean e){
        E = e;
    }
    public boolean getE(){
        return E;
    }

    public void setNE(boolean ne){
        NE = ne;
    }
    public boolean getNE(){
        return NE;
    }

    public void setGT(boolean gt){
        GT = gt;
    }
    public boolean getGT(){
        return GT;
    }

    public void setLT(boolean lt){
        LT = lt;
    }
    public boolean getLT(){
        return LT;
    }
}

public class ALU {
    int A;
    int B;
    Control_Unit control_Unit;
    Flags flags;

    public ALU(int a,int b,Control_Unit control_unit,Flags _flags){
        this.A = a;
        this.B = b;
        this.control_Unit = control_unit;
        this.flags = _flags;
    }

    public int getALU_Result(){

        if(control_Unit.isAdd){
            return A+B;
        }else if(control_Unit.isSub){
            return A-B;
        }else if(control_Unit.isMul){
            return A*B;
        }else if(control_Unit.isDiv){
            return A/B; //have to deal with remainder
        }

        if(control_Unit.isBeq && A==B){
            flags.setE(true);
        }else if(control_Unit.isBne && A!=B){
            flags.setNE(true);
        }else if(control_Unit.isBgt && A>B){
            flags.setGT(true);
        }else if(control_Unit.isBlt && A<B){
            flags.setLT(true);
        }

        return 0;
    }
}
