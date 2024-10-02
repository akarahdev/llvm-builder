package dev.akarah.llvm.inst;

import dev.akarah.llvm.ir.IRStringConvertable;

public interface Type extends IRStringConvertable {


    record Void() implements Type {
        @Override
        public String ir() {
            return "void";
        }
    }
}
