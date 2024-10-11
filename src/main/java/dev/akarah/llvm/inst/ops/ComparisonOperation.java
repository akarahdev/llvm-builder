package dev.akarah.llvm.inst.ops;

import dev.akarah.llvm.ir.IRStringConvertable;

public enum ComparisonOperation implements IRStringConvertable {
    EQUAL("eq"),
    NOT_EQUAL("ne"),
    UNSIGNED_GREATER_THAN("ugt"),
    UNSIGNED_LESS_THAN("ult"),
    UNSIGNED_GREATER_THAN_OR_EQUAL("uge"),
    UNSIGNED_LESS_THAN_OR_EQUAL("ule"),
    SIGNED_GREATER_THAN("sgt"),
    SIGNED_LESS_THAN("slt"),
    SIGNED_GREATER_THAN_OR_EQUAL("sge"),
    SIGNED_LESS_THAN_OR_EQUAL("sle");

    final String ir;
    ComparisonOperation(String ir) {
        this.ir = ir;
    }
    @Override
    public String ir() {
        return this.ir;
    }
}
