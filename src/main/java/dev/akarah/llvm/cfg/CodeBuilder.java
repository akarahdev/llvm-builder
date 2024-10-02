package dev.akarah.llvm.cfg;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;

public interface CodeBuilder {
    Value add(Type type, Value lhs, Value rhs);
    Value sub(Type type, Value lhs, Value rhs);
    Value mul(Type type, Value lhs, Value rhs);
    Value div(Type type, Value lhs, Value rhs);

    default Value.IntegerConstant constant(long amount) {
        return new Value.IntegerConstant(String.valueOf(amount));
    }
    default Value.FloatingPointConstant constant(double amount) {
        return new Value.FloatingPointConstant(String.valueOf(amount));
    }

    void ret(Type type, Value value);
}
