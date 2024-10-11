package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.inst.atomic.AtomicOrdering;
import dev.akarah.llvm.ir.IRFormatter;

public record LoadAtomic(Value.LocalVariable output, Type type, Value pointer,
                         AtomicOrdering ordering) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = load atomic {}, ptr {} {}",
            output, type, pointer, ordering);
    }
}
