package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record Bitcast(Value.LocalVariable output, Type inputType, Value inputValue,
                      Type outputType) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = bitcast {} {} to {}",
            output, inputType, inputValue, outputType);
    }
}
