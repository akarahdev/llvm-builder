package dev.akarah.llvm.inst;

public class Constant {
    public static Value.IntegerConstant constant(long amount) {
        return new Value.IntegerConstant(String.valueOf(amount));
    }
    public static Value.FloatingPointConstant constant(double amount) {
        return new Value.FloatingPointConstant(String.valueOf(amount));
    }
}
