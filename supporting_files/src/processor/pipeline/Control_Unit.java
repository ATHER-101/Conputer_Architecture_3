package processor.pipeline;

import java.util.HashMap;

public class Control_Unit {
    boolean isSt;
    boolean isLd;
    boolean isImmediate;
    boolean isWb;
    boolean isAdd;
    boolean isSub;
    boolean isMul;
    boolean isDiv;
    boolean isAND;
    boolean isOR;
    boolean isXOR;
    boolean isSlt;
    boolean isSll;
    boolean isSrl;
    boolean isSra;
    boolean isBeq;
    boolean isBne;
    boolean isBlt;
    boolean isBgt;
    boolean isJmp;

    public Control_Unit(String OpCode) {


        HashMap<String, String> operation = new HashMap<String, String>() {{
            put("00000", "add");
            put("00010", "sub");
            put("00100", "mul");
            put("00110", "div");
            put("01000", "and");
            put("01010", "or");
            put("01100", "xor");
            put("01110", "slt");
            put("10000", "sll");
            put("10010", "srl");
            put("10100", "sra");
            put("00001", "addi");
            put("00011", "subi");
            put("00101", "muli");
            put("00111", "divi");
            put("01001", "andi");
            put("01011", "ori");
            put("01101", "xori");
            put("01111", "slti");
            put("10001", "slli");
            put("10011", "srli");
            put("10101", "srai");
            put("10110", "load");
            put("10111", "store");
            put("11001", "beq");
            put("11010", "bne");
            put("11011", "blt");
            put("11100", "bgt");
            put("11000", "jmp");
        }};
    

        switch (operation.get(OpCode)) {
            case "add": {
                isWb = true;
                isAdd = true;
                break;
            }
            case "sub": {
                isWb = true;
                isSub = true;
                break;
            }
            case "mul": {
                isWb = true;
                isMul = true;
                break;
            }
            case "div": {
                isWb = true;
                isDiv = true;
                break;
            }
            case "and":
            case "or":
            case "xor":
            case "slt":
            case "sll":
            case "srl":
            case "sra": {
                
                break;
            }

            // R2I type
            case "addi": {
                isImmediate = true;
                isWb = true;
                isAdd = true;
                break;
            }
            case "subi": {
                isImmediate = true;
                isWb = true;
                isSub = true;
                break;
            }
            case "muli": {
                isImmediate = true;
                isWb = true;
                isMul = true;
                break;
            }
            case "divi": {
                isImmediate = true;
                isWb = true;
                isDiv = true;
                break;
            }
            case "andi":
            case "ori":
            case "xori":
            case "slti":
            case "slli":
            case "srli":
            case "srai": {
                
                break;
            }

            case "load": {
                isLd = true;
                isImmediate = true;
                isWb = true;
                isAdd = true;
                break;
            }
            case "store": {
                isSt = true;
                isImmediate = true;
                isAdd = true;
                break;

            }

            case "beq": {
                isBeq = true;
                break;
            }
            case "bne": {
                isBne = true;
                break;
            }
            case "blt": {
                isBlt = true;
                break;
            }
            case "bgt": {
                isBgt = true;
                break;
            }

            // RI type :
            case "jmp": {
                isJmp = true;
                break;
            }

        }
    }
}
