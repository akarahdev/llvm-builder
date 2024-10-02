package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRFormatter;
import dev.akarah.llvm.ir.IRStringConvertable;

public interface Instruction extends IRStringConvertable {
    record Ret(Type type, Value value) implements Instruction {
        @Override
        public String ir() {
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

    record Div(Value.LocalVariable output, Type type, Value lhs, Value rhs) implements Instruction {
        @Override
        public String ir() {
            return IRFormatter.format("{} = div {} {}, {}", output, type, lhs, rhs);
        }
    }
}
