package dev.akarah.llvm.cfg;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;
import dev.akarah.llvm.ir.IRStringConvertable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Function implements IRStringConvertable {
    private Function() {
    }

    public static Function of(Value.GlobalVariable name) {
        var f = new Function();
        f.name = name;
        return f;
    }

    @Override
    public String ir() {
        return IRFormatter.format(
            """
                define {} {}(params)code
                """
                .replace(
                    "params",
                    this
                        .parameters
                        .stream()
                        .map(Parameter::toString)
                        .collect(Collectors.joining(", ")))
                .replace("code",
                    this.basicBlocks == null
                        ? ""
                        : " {\n" + this.basicBlocks.stream().map(BasicBlock::ir).collect(Collectors.joining("\n"))) + "}",

            returnType, name
        );
    }

    record Parameter(Value.LocalVariable value, Type type) {
        @Override
        public String toString() {
            return IRFormatter.format("{} {}", type, value);
        }
    }

    Value.GlobalVariable name;
    Type returnType = new Type.Void();
    List<Parameter> parameters = new ArrayList<>();
    List<BasicBlock> basicBlocks = null;

    public Function returns(Type returnType) {
        this.returnType = returnType;
        return this;
    }


    public Function parameter(Type type, Value.LocalVariable variable) {
        this.parameters.add(new Parameter(variable, type));
        return this;
    }

    public Function basicBlock(BasicBlock basicBlock) {
        if (this.basicBlocks == null)
            this.basicBlocks = new ArrayList<>();
        this.basicBlocks.add(basicBlock);
        return this;
    }


}
