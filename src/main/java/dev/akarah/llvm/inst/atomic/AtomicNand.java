package dev.akarah.llvm.inst.atomic;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.inst.ops.BinaryOperation;
import dev.akarah.llvm.ir.IRFormatter;

public record AtomicNand(Value.LocalVariable output, Type type, Value pointer, Value argument, AtomicOrdering ordering) implements BinaryOperation {
    @Override
    public String ir() {
        return IRFormatter.format("{} = atomicrmw nand ptr {}, {} {} {}", output, pointer, type, argument, ordering);
    }
}
