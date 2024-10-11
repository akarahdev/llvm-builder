package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record PtrToInt(Value.LocalVariable output, Type type, Value pointer,
                       Type outputType) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = ptrtoint {} {} to {}",
            output, type, pointer, outputType);
    }
}
