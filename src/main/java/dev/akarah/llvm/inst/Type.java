package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRStringConvertable;

public interface Type extends IRStringConvertable {
    public static Type VOID = new Type.Void();

    record Void() implements Type {
        @Override
        public String ir() {
            return "void";
        }
    }
}
