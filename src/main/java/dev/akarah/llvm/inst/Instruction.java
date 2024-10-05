package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRFormatter;
import dev.akarah.llvm.ir.IRStringConvertable;

import java.util.List;
import java.util.stream.Collectors;

public sealed interface Instruction extends IRStringConvertable {
    sealed interface Terminator extends Instruction {}

    record Ret(Type type, Value value) implements Terminator {
        @Override
        public String ir() {
            if (value == null)
                return IRFormatter.format("ret {}", type);
            return IRFormatter.format("ret {} {}", type, value);
        }
    }

    record BrIf(Value condition, Value ifTrue, Value ifFalse) implements Terminator {
        @Override
        public String ir() {
            return IRFormatter.format("br i1 {}, label {}, label {}", condition, ifTrue, ifFalse);
        }
    }

    record BrLabel(Value label) implements Terminator {
        @Override
        public String ir() {
            return IRFormatter.format("br label {}", label);
        }
    }

    sealed interface BinaryOperation extends Instruction {}

    record Add(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = add {} {}, {}", output, type, lhs, rhs);
        }
    }

    record Sub(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = sub {} {}, {}", output, type, lhs, rhs);
        }
    }

    record Mul(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = mul {} {}, {}", output, type, lhs, rhs);
        }
    }

    record SDiv(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = sdiv {} {}, {}", output, type, lhs, rhs);
        }
    }

    record UDiv(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = udiv {} {}, {}", output, type, lhs, rhs);
        }
    }

    record FDiv(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = fdiv {} {}, {}", output, type, lhs, rhs);
        }
    }


    record SRem(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = srem {} {}, {}", output, type, lhs, rhs);
        }
    }

    record URem(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = urem {} {}, {}", output, type, lhs, rhs);
        }
    }

    record FRem(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements BinaryOperation {
        @Override
        public String ir() {
            return IRFormatter.format("{} = frem {} {}, {}", output, type, lhs, rhs);
        }
    }

    sealed interface OtherOperation extends Instruction {}

    record Call(Value.LocalVariable output, Type outputType, Value.GlobalVariable name,
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

    sealed interface MemoryOperation extends Instruction {}

    record Alloca(Value.LocalVariable output, Type outputType, int amount) implements MemoryOperation {
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

    record GetElementPtr(Value.LocalVariable output, Type type, Value pointer, Type indexType, Value indexValue) implements MemoryOperation {
        @Override
        public String ir() {
            return IRFormatter.format(
                "{} = getelementptr {}, ptr {}, {} {}",
                output, type, pointer, indexType, indexValue);
        }
    }

    record Load(Value.LocalVariable output, Type type, Value pointer) implements MemoryOperation {
        @Override
        public String ir() {
            return IRFormatter.format(
                "{} = load {}, ptr {}",
                output, type, pointer);
        }
    }

    record Store(Type type, Value value, Value pointer) implements MemoryOperation {
        @Override
        public String ir() {
            return IRFormatter.format(
                "store {} {}, ptr {}",
                type, value, pointer);
        }
    }

    record Bitcast(Value.LocalVariable output, Type inputType, Value inputValue, Type outputType) implements MemoryOperation {
        @Override
        public String ir() {
            return IRFormatter.format(
                "{} = bitcast {} {} to {}",
                output, inputType, inputValue, outputType);
        }
    }
}
