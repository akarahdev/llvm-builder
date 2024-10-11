package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record Alloca(Value.LocalVariable output, Type outputType, int amount) implements MemoryOperation {
    @Override
    public String ir() {
        if (amount == 1) {
            return IRFormatter.format(
                "{} = alloca {}",
                output, outputType);
        }
        return IRFormatter.format(
            "{} = alloca {}, {} amount"
                .replace("amount", String.valueOf(amount)),
            output, outputType, outputType);
    }
}
