package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record Store(Type type, Value value, Value pointer) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "store {} {}, ptr {}",
            type, value, pointer);
    }
}
