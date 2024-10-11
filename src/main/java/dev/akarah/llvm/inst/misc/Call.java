package dev.akarah.llvm.inst.misc;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;

import java.util.List;
import java.util.stream.Collectors;

public record Call(Value.LocalVariable output, Type outputType, Value.GlobalVariable name,
                   List<Parameter> parameters) implements OtherOperation {
    public record Parameter(Type type, Value value) {
    }

    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = call {} {}(params)"
                .replace("params",
                    parameters.stream()
                        .map(it -> it.type().ir() + " " + it.value().ir())
                        .collect(Collectors.joining(", "))),
            output, outputType, name);
    }
}
