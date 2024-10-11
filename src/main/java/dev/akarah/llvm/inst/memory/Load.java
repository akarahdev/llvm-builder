package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record Load(Value.LocalVariable output, Type type, Value pointer) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = load {}, ptr {}",
            output, type, pointer);
    }
}
