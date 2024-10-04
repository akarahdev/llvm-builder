package dev.akarah.llvm.cfg;

import dev.akarah.llvm.inst.Instruction;
import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;

import java.util.List;

public interface CodeBuilder {
    Value add(Type type, Value lhs, Value rhs);
    Value sub(Type type, Value lhs, Value rhs);
    Value mul(Type type, Value lhs, Value rhs);
    Value sdiv(Type type, Value lhs, Value rhs);
    Value udiv(Type type, Value lhs, Value rhs);
    Value call(Type returnType, Value.GlobalVariable name, List<Instruction.Call.Parameter> parameters);


    void ret(Type type, Value value);
    void ret();
}
