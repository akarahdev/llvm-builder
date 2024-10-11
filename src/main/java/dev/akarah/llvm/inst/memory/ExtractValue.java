package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record ExtractValue(Value.LocalVariable output, Type type, Value pointer,
                           Value indexConstant) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = extractvalue {} {}, {}",
            output, type, pointer, indexConstant);
    }
}
