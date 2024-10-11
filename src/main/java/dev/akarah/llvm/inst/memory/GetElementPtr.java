package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record GetElementPtr(Value.LocalVariable output, Type type, Value pointer, Type indexType,
                            Value indexValue) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = getelementptr {}, ptr {}, {} {}",
            output, type, pointer, indexType, indexValue);
    }
}
