package dev.akarah.llvm.inst.terminator;

import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record BrIf(Value condition, Value ifTrue, Value ifFalse) implements Terminator {
    @Override
    public String ir() {
        return IRFormatter.format("br i1 {}, label {}, label {}", condition, ifTrue, ifFalse);
    }
}
