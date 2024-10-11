package dev.akarah.llvm.inst.terminator;

import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record BrLabel(Value label) implements Terminator {
    @Override
    public String ir() {
        return IRFormatter.format("br label {}", label);
    }
}
