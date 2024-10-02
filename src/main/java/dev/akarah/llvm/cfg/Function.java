package dev.akarah.llvm.cfg;

import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;

import java.util.*;

public class Function {
    record Parameter(Value.LocalVariable value, Type type) {}
    Value.GlobalVariable name;
    Type returnType = new Type.Void();
    List<Parameter> parameters = new ArrayList<>();
}
