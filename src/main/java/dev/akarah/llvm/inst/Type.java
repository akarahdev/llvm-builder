package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRStringConvertable;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface Type extends IRStringConvertable {
    record Void() implements Type {
        @Override
        public String ir() {
            return "void";
        }
    }

    record Integer(int width) implements Type {
        @Override
        public String ir() {
            return "i" + width;
        }
    }

    record Function(Type returnType, Type[] parameters) implements Type {
        @Override
        public String ir() {
            return returnType
                + "(" +
                Arrays.stream(parameters)
                    .map(IRStringConvertable::ir)
                    .collect(Collectors.joining(", "))
                + ")";
        }
    }

    record Half() implements Type {
        @Override
        public String ir() {
            return "half";
        }
    }

    record Float() implements Type {
        @Override
        public String ir() {
            return "float";
        }
    }

    record Double() implements Type {
        @Override
        public String ir() {
            return "double";
        }
    }

    record FP128() implements Type {
        @Override
        public String ir() {
            return "fp128";
        }
    }

    record Ptr(Type subtype) implements Type {
        @Override
        public String ir() {
            return "ptr";
        }
    }

    record Label() implements Type {
        @Override
        public String ir() {
            return "label";
        }
    }

    record Token() implements Type {
        @Override
        public String ir() {
            return "token";
        }
    }

    record Metadata() implements Type {
        @Override
        public String ir() {
            return "metadata";
        }
    }

    record Array(int length, Type subtype) implements Type {
        @Override
        public String ir() {
            return "[" + length + " x " + subtype.ir() + "]";
        }
    }

    record Structure(Type[] parameters) implements Type {
        @Override
        public String ir() {
            return "{" +
                Arrays.stream(parameters)
                    .map(IRStringConvertable::ir)
                    .collect(Collectors.joining(", "))
                + "}";
        }
    }

    record Vector(int length, Type subtype) implements Type {
        @Override
        public String ir() {
            return "<" + length + " x " + subtype.ir() + ">";
        }
    }
}
