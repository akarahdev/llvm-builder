package dev.akarah.llvm.inst.memory;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

public record GetElementPtr4(Value.LocalVariable output, Type type, Value pointer,
                             Type indexType1, Value indexValue1, Type indexType2, Value indexValue2,
                             Type indexType3, Value indexValue3, Type indexType4, Value indexValue4) implements MemoryOperation {
    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = getelementptr {}, ptr {}, {} {}, {} {}, {} {}, {} {}",
            output, type, pointer, indexType1, indexValue1, indexType2, indexValue2, indexType3, indexValue3,
            indexType4, indexValue4);
    }
}
