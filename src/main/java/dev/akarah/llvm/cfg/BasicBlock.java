package dev.akarah.llvm.cfg;

import dev.akarah.llvm.ir.IRStringConvertable;
import dev.akarah.llvm.inst.Instruction;
import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicBlock implements CodeBuilder, IRStringConvertable {
    Value.LocalVariable name;
    List<Instruction> instructions = new ArrayList<>();
    Map<Value.LocalVariable, Integer> localNames = new HashMap<>();

    private BasicBlock() {}

    public static BasicBlock of(Value.LocalVariable name) {
        var bb = new BasicBlock();
        bb.name = name;
        return bb;
    }

    @Override
    public Value add(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Add(output, type, lhs, rhs));
        return output;
    }

    @Override
    public Value sub(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Sub(output, type, lhs, rhs));
        return output;
    }

    @Override
    public Value mul(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Mul(output, type, lhs, rhs));
        return output;
    }

    @Override
    public Value div(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Div(output, type, lhs, rhs));
        return output;
    }

    @Override
    public void ret(Type type, Value value) {
        instructions.add(new Instruction.Ret(type, value));
    }

    @Override
    public String ir() {
        return "  " + this.name.ir() + ":\n"
                + this.instructions.stream().map(it -> "    " + it.ir()).collect(Collectors.joining("\n"));
    }
}
