package dev.akarah.llvm.inst.ops;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record SDiv(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
    @Override
    public String ir() {
        return IRFormatter.format("{} = sdiv {} {}, {}", output, type, lhs, rhs);
    }
}
