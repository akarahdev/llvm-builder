package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRStringConvertable;

import java.util.UUID;

public sealed interface Value extends IRStringConvertable {
    record IntegerConstant(String value) implements Value {
        @Override
        public String ir() {
            return value;
        }
    }
    record FloatingPointConstant(String value) implements Value {
        @Override
        public String ir() {
            return value;
        }
    }
    record LocalVariable(String name) implements Value {
        @Override
        public String ir() {
            return "%" + name;
        }

        public static long counter = 0;

        public static LocalVariable random() {
            return new LocalVariable(
                    String.valueOf(++counter)
            );
        }
    }
    record GlobalVariable(String name) implements Value {
        @Override
        public String ir() {
            return "@" + name;
        }
    }

}
