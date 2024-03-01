package processor.pipeline;

import processor.Processor;

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
    Processor containingProcessor;

    public ALU(int a,int b,Control_Unit control_unit,Flags _flags,
    Processor containing_processor){
        this.A = a;
        this.B = b;
        this.control_Unit = control_unit;
        this.flags = _flags;
        this.containingProcessor = containing_processor;
    }

    public int getALU_Result(){

        if(control_Unit.isAdd){
            long sum = A+B;
            // int first_32bits = (int) (sum >>> 32);
            // containingProcessor.getRegisterFile().setValue(31, first_32bits);
            return (int)sum;
        }else if(control_Unit.isSub){
            long sub = A-B;
            // int first_32bits = (int) (sub >>> 32);
            // containingProcessor.getRegisterFile().setValue(31, first_32bits);
            return (int)sub;
        }else if(control_Unit.isMul){
            long mul = A*B;
            // int first_32bits = (int) (mul >>> 32);
            // containingProcessor.getRegisterFile().setValue(31, first_32bits);
            return (int)mul;
        }else if(control_Unit.isDiv){
            containingProcessor.getRegisterFile().setValue(31, A%B);
            return A/B;
        }else if(control_Unit.isAND){
            return A&B;
        }else if(control_Unit.isOR){
            return A|B;
        }else if(control_Unit.isXOR){
            return A^B;
        }else if(control_Unit.isSlt){
            if(A<B){
                return 1;
            }else{
                return 0;
            }
        }else if(control_Unit.isSll){
            // int shifted_bits = A>>>(32-B);
            // containingProcessor.getRegisterFile().setValue(31, shifted_bits);
            return A<<B;
        }else if(control_Unit.isSrl){
            // int shifted_bits = (A<<(32-B))>>>(32-B);
            // containingProcessor.getRegisterFile().setValue(31, shifted_bits);
            return A>>>B;
        }else if(control_Unit.isSra){
            // int shifted_bits = (A<<(32-B))>>>(32-B);
            // containingProcessor.getRegisterFile().setValue(31, shifted_bits);
            return A>>B;
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
