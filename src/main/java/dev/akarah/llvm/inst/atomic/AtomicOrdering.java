package dev.akarah.llvm.inst.atomic;

import dev.akarah.llvm.ir.IRStringConvertable;

public enum AtomicOrdering implements IRStringConvertable {
    UNORDERED("unordered"),
    MONOTONIC("monotonic"),
    ACQUIRE("acquire"),
    RELEASE("release"),
    ACQUIRE_RELEASE("acq_rel"),
    SEQUENTIALLY_CONSISTENT("seq_cst");

    final String irRepr;

    AtomicOrdering(String ir) {
        this.irRepr = ir;
    }

    @Override
    public String ir() {
        return this.irRepr;
    }
}
