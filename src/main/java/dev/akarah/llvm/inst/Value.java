package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRFormatter;
import dev.akarah.llvm.ir.IRStringConvertable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public sealed interface Value extends IRStringConvertable {
    record IntegerConstant(String value) implements Value {
        @Override
        public String ir() {
            return value;
        }
    }

    record CStringConstant(String value) implements Value {
        @Override
        public String ir() {
            return "c\"" +
                value
                + '"';
        }
    }

    record FloatingPointConstant(String value) implements Value {
        @Override
        public String ir() {
            return value;
        }
    }

    record ZeroInitializer() implements Value {
        @Override
        public String ir() {
            return "zeroinitializer";
        }
    }

    record LocalVariable(String name) implements Value {
        @Override
        public String ir() {
            return "%" + name;
        }

        public static String toBase26(long number) {
            StringBuilder result = new StringBuilder();
            while (number > 0) {
                result.insert(0, (char) ('a' + (--number % 26)));
                number /= 26;
            }
            return result.toString();
        }

        public static long counter = 0;

        public static LocalVariable random() {
            return new LocalVariable(toBase26(++counter));
        }
    }

    record NoCapture() implements Value {
        @Override
        public String ir() {
            return "nocapture";
        }
    }

    record GlobalVariable(String name) implements Value {
        @Override
        public String ir() {
            return "@" + name;
        }

        public static GlobalVariable random() {
            return new GlobalVariable(
                "." + LocalVariable.toBase26(++LocalVariable.counter)
            );
        }
    }

    record Poison() implements Value {
        @Override
        public String ir() {
            return "poison";
        }
    }

    record Undef() implements Value {
        @Override
        public String ir() {
            return "undef";
        }
    }

    record BlockAddress(GlobalVariable function, LocalVariable block) implements Value {
        @Override
        public String ir() {
            return IRFormatter.format("blockaddress({}, {})", function, block);
        }
    }

    record VectorConstant(Type type, List<Value> parameters) implements Value {
        @Override
        public String ir() {
            return "< " + parameters.stream().map(Value::ir).map(it -> type.ir() + " " + it).collect(Collectors.joining(", ")) + " >";
        }
    }

    record ArrayConstant(Type type, List<Value> parameters) implements Value {
        @Override
        public String ir() {
            return "[ " + parameters.stream().map(Value::ir).map(it -> type.ir() + " " + it).collect(Collectors.joining(", ")) + " ]";
        }
    }

    record StructureConstant(List<Element> parameters) implements Value {
        @Override
        public String ir() {
            return "{ " + parameters.stream().map(Element::toString).collect(Collectors.joining(", ")) + " }";
        }

        public record Element(Value value, Type type) {
            @Override
            public String toString() {
                return this.type.ir() + " " + this.value.ir();
            }
        }
    }

}
