package dev.akarah.llvm.inst;

import dev.akarah.llvm.cfg.GlobalVariable;
import dev.akarah.llvm.ir.IRFormatter;
import dev.akarah.llvm.ir.IRStringConvertable;

import java.util.List;
import java.util.stream.Collectors;

public interface Instruction extends IRStringConvertable {
    record Ret(Type type, Value value) implements Instruction {
        @Override
        public String ir() {
            if(value == null)
                return IRFormatter.format("ret {}", type);
            return IRFormatter.format("ret {} {}", type, value);
        }
    }

    record Add(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements Instruction {
        @Override
        public String ir() {
            return IRFormatter.format("{} = add {} {}, {}", output, type, lhs, rhs);
        }
    }

    record Sub(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements Instruction {
        @Override
        public String ir() {
            return IRFormatter.format("{} = sub {} {}, {}", output, type, lhs, rhs);
        }
    }

    record Mul(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements Instruction {
        @Override
        public String ir() {
            return IRFormatter.format("{} = mul {} {}, {}", output, type, lhs, rhs);
        }
    }

    record SDiv(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements Instruction {
        @Override
        public String ir() {
            return IRFormatter.format("{} = sdiv {} {}, {}", output, type, lhs, rhs);
        }
    }
    record UDiv(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements Instruction {
        @Override
        public String ir() {
            return IRFormatter.format("{} = udiv {} {}, {}", output, type, lhs, rhs);
        }
    }

    record Call(Value.LocalVariable output, Type outputType, Value.GlobalVariable name, List<Parameter> parameters) implements Instruction {
        public record Parameter(Type type, Value value) {}

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
}
