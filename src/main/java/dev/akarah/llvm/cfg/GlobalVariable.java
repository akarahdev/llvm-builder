package dev.akarah.llvm.cfg;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRFormatter;
import dev.akarah.llvm.ir.IRStringConvertable;

public class GlobalVariable implements IRStringConvertable {
    Value.GlobalVariable name;
    Type type;
    Value value;

    private GlobalVariable() {

    }

    public static GlobalVariable of(Value.GlobalVariable name) {
        var gv = new GlobalVariable();
        gv.name = name;
        return gv;
    }

    public GlobalVariable withType(Type type) {
        this.type = type;
        return this;
    }

    public GlobalVariable withValue(Value value) {
        this.value = value;
        return this;
    }

    @Override
    public String ir() {
        return IRFormatter.format(
            "{} = global {} {}",
            name,
            type,
            value
        );
    }
}
