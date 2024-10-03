package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRFormatter;
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

}
