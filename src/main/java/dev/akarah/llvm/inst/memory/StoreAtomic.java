package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.inst.atomic.AtomicOrdering;
import dev.akarah.llvm.ir.IRFormatter;

public record StoreAtomic(Type type, Value value, Value pointer, AtomicOrdering ordering) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "store atomic {} {}, ptr {} {}",
            type, value, pointer, ordering);
    }
}
