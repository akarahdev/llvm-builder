package dev.akarah.llvm.cfg;

import dev.akarah.llvm.inst.Types;
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
    public Value sdiv(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.SDiv(output, type, lhs, rhs));
        return output;
    }

    @Override
    public Value udiv(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.UDiv(output, type, lhs, rhs));
        return output;
    }

    @Override
    public Value call(Type returnType, Value.GlobalVariable name, List<Instruction.Call.Parameter> parameters) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Call(
            output,
            returnType,
            name,
            parameters
        ));
        return output;
    }

    @Override
    public void ret(Type type, Value value) {
        instructions.add(new Instruction.Ret(type, value));
    }

    @Override
    public void ret() {
        instructions.add(new Instruction.Ret(Types.VOID, null));
    }

    @Override
    public String ir() {
        return "  " + this.name.name() + ":\n"
                + this.instructions.stream().map(it -> "    " + it.ir()).collect(Collectors.joining("\n"));
    }
}
