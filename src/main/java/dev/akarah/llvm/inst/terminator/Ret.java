package dev.akarah.llvm.inst.terminator;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record Ret(Type type, Value value) implements Terminator {
    @Override
    public String ir() {
        if (value == null)
            return IRFormatter.format("ret {}", type);
        return IRFormatter.format("ret {} {}", type, value);
    }
}
