package dev.akarah.llvm.inst.ops;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record Mul(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
    @Override
    public String ir() {
        return IRFormatter.format("{} = mul {} {}, {}", output, type, lhs, rhs);
    }
}
