package dev.akarah.llvm.inst.atomic;

import dev.akarah.llvm.ir.IRStringConvertable;

public enum RMWOperation implements IRStringConvertable {
    XCHG("xchg"),
    ADD("add"),
    SUB("sub"),
    AND("and"),
    NAND("nand"),
    OR("or"),
    XOR("xor"),
    MAX("max"),
    MIN("min"),
    UMAX("umax"),
    UMIN("umin"),
    FADD("fadd"),
    FSUB("fsub"),
    FMAX("fmax"),
    FMIN("fmin");

    final String ir;
    RMWOperation(String ir) {
        this.ir = ir;
    }
    @Override
    public String ir() {
        return this.ir;
    }
}
