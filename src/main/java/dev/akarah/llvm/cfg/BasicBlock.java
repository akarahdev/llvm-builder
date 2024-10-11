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
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BasicBlock implements IRStringConvertable {
    Value.LocalVariable name;
    List<Instruction> instructions = new ArrayList<>();
    Map<Value.LocalVariable, Integer> localNames = new HashMap<>();
    List<BasicBlock> childBlocks = new ArrayList<>();

    private BasicBlock() {}

    protected void addBasicBlocks(Function function) {
        for(var child : childBlocks)
            if(!function.basicBlocks.contains(child))
                function.withBasicBlock(child);
    }

    public Value.LocalVariable name() {
        return this.name;
    }

    public void childBlock(BasicBlock child) {
        this.childBlocks.add(child);
    }

    public static BasicBlock of(Value.LocalVariable name) {
        var bb = new BasicBlock();
        bb.name = name;
        return bb;
    }

    public BasicBlock ifThenElse(
        Value condition,
        Consumer<BasicBlock> ifTrue,
        Consumer<BasicBlock> ifFalse
    ) {
        var ifTrueBlock = BasicBlock.of(Value.LocalVariable.random());
        var ifFalseBlock = BasicBlock.of(Value.LocalVariable.random());

        ifTrue.accept(ifTrueBlock);
        ifFalse.accept(ifFalseBlock);

        this.childBlock(ifTrueBlock);
        this.childBlock(ifFalseBlock);

        this.br(condition, ifTrueBlock.name(), ifFalseBlock.name());

        return this;
    }

    public Value add(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Add(output, type, lhs, rhs));
        return output;
    }

    public Value sub(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Sub(output, type, lhs, rhs));
        return output;
    }

    public Value mul(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Mul(output, type, lhs, rhs));
        return output;
    }

    public Value sdiv(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.SDiv(output, type, lhs, rhs));
        return output;
    }

    public Value udiv(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.UDiv(output, type, lhs, rhs));
        return output;
    }

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

    public Value alloca(Type type, int amount) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Alloca(
            output,
            type,
            amount
        ));
        return output;
    }

    public Value alloca(Type type) {
        return alloca(type, 1);
    }

    public Value load(Type type, Value pointer) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.Load(
            output,
            type,
            pointer
        ));
        return output;
    }

    public void store(Type type, Value value, Value pointer) {
        instructions.add(new Instruction.Store(
            type,
            value,
            pointer
        ));
    }

    public Value bitcast(Type inputType, Value inputValue, Type outputType) {
        var out = Value.LocalVariable.random();
        instructions.add(new Instruction.Bitcast(
            out,
            inputType,
            inputValue,
            outputType
        ));
        return out;
    }

    public Value getElementPtr(Type type, Value pointer, Type indexType, Value indexValue) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.GetElementPtr(
            output,
            type,
            pointer,
            indexType,
            indexValue
        ));
        return output;
    }

    public Value extractValue(Type type, Value value, Value index) {
        var output = Value.LocalVariable.random();
        instructions.add(new Instruction.ExtractValue(
            output,
            type,
            value,
            index
        ));
        return output;
    }



    public void ret(Type type, Value value) {
        instructions.add(new Instruction.Ret(type, value));
    }

    public void ret() {
        instructions.add(new Instruction.Ret(Types.VOID, null));
    }

    public void br(Value.LocalVariable label) {
        this.instructions.add(new Instruction.BrLabel(label));
    }

    public void br(Value condition, Value.LocalVariable ifTrue, Value.LocalVariable ifFalse) {
        this.instructions.add(new Instruction.BrIf(condition, ifTrue, ifFalse));
    }

    public void comment(String contents) {
        this.instructions.add(new Instruction.Comment(contents));
    }

    public String ir() {
        return "  " + this.name.name() + ":\n"
                + this.instructions.stream().map(it -> "    " + it.ir()).collect(Collectors.joining("\n"));
    }
}
