package dev.akarah.llvm.cfg;

import dev.akarah.llvm.inst.Instruction;
import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Types;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.inst.atomic.AtomicOrdering;
import dev.akarah.llvm.inst.atomic.AtomicRMW;
import dev.akarah.llvm.inst.atomic.RMWOperation;
import dev.akarah.llvm.inst.memory.*;
import dev.akarah.llvm.inst.misc.Call;
import dev.akarah.llvm.inst.misc.Comment;
import dev.akarah.llvm.inst.ops.*;
import dev.akarah.llvm.inst.terminator.BrIf;
import dev.akarah.llvm.inst.terminator.BrLabel;
import dev.akarah.llvm.inst.terminator.Ret;
import dev.akarah.llvm.ir.IRStringConvertable;

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

    private BasicBlock() {
    }

    protected void addBasicBlocks(Function function) {
        for (var child : childBlocks)
            if (!function.basicBlocks.contains(child))
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

    public Value fadd(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new FAdd(output, type, lhs, rhs));
        return output;
    }

    public Value fsub(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new FSub(output, type, lhs, rhs));
        return output;
    }

    public Value fmul(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new FMul(output, type, lhs, rhs));
        return output;
    }

    public Value add(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Add(output, type, lhs, rhs));
        return output;
    }

    public Value sub(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Sub(output, type, lhs, rhs));
        return output;
    }

    public Value mul(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Mul(output, type, lhs, rhs));
        return output;
    }

    public Value sdiv(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new SDiv(output, type, lhs, rhs));
        return output;
    }

    public Value udiv(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new UDiv(output, type, lhs, rhs));
        return output;
    }

    public Value shl(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new Shl(output, type, lhs, rhs));
        return output;
    }

    public Value ashr(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new AShr(output, type, lhs, rhs));
        return output;
    }

    public Value lshr(Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new LShr(output, type, lhs, rhs));
        return output;
    }

    public Value atomicrmw(
        RMWOperation operation,
        Type type,
        Value pointer,
        Value argument,
        AtomicOrdering ordering
    ) {
        var output = Value.LocalVariable.random();
        instructions.add(new AtomicRMW(output, operation, type, pointer, argument, ordering));
        return output;
    }

    public Value icmp(ComparisonOperation operation, Type type, Value lhs, Value rhs) {
        var output = Value.LocalVariable.random();
        instructions.add(new ICmp(output, operation, type, lhs, rhs));
        return output;
    }

    public Value call(Type returnType, Value.GlobalVariable name, List<Call.Parameter> parameters) {
        var output = Value.LocalVariable.random();
        instructions.add(new Call(
            output,
            returnType,
            name,
            parameters
        ));
        return output;
    }

    public Value alloca(Type type, int amount) {
        var output = Value.LocalVariable.random();
        instructions.add(new Alloca(
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
        instructions.add(new Load(
            output,
            type,
            pointer
        ));
        return output;
    }

    public Value loadAtomic(Type type, Value pointer, AtomicOrdering ordering) {
        var output = Value.LocalVariable.random();
        instructions.add(new LoadAtomic(
            output,
            type,
            pointer,
            ordering
        ));
        return output;
    }

    public void store(Type type, Value value, Value pointer) {
        instructions.add(new Store(
            type,
            value,
            pointer
        ));
    }

    public void storeAtomic(Type type, Value value, Value pointer, AtomicOrdering ordering) {
        instructions.add(new StoreAtomic(
            type,
            value,
            pointer,
            ordering
        ));
    }

    public Value bitcast(Type inputType, Value inputValue, Type outputType) {
        var out = Value.LocalVariable.random();
        instructions.add(new Bitcast(
            out,
            inputType,
            inputValue,
            outputType
        ));
        return out;
    }

    public Value getElementPtr(Type type, Value pointer, Type indexType, Value indexValue) {
        var output = Value.LocalVariable.random();
        instructions.add(new GetElementPtr(
            output,
            type,
            pointer,
            indexType,
            indexValue
        ));
        return output;
    }

    public Value getElementPtr(Type type, Value pointer,
                               Type indexType1, Value indexValue1,
                               Type indexType2, Value indexValue2) {
        var output = Value.LocalVariable.random();
        instructions.add(new GetElementPtr2(
            output,
            type,
            pointer,
            indexType1,
            indexValue1,
            indexType2,
            indexValue2
        ));
        return output;
    }

    public Value getElementPtr(Type type, Value pointer,
                               Type indexType1, Value indexValue1,
                               Type indexType2, Value indexValue2,
                               Type indexType3, Value indexValue3) {
        var output = Value.LocalVariable.random();
        instructions.add(new GetElementPtr3(
            output,
            type,
            pointer,
            indexType1,
            indexValue1,
            indexType2,
            indexValue2,
            indexType3,
            indexValue3
        ));
        return output;
    }

    public Value extractValue(Type type, Value value, Value index) {
        var output = Value.LocalVariable.random();
        instructions.add(new ExtractValue(
            output,
            type,
            value,
            index
        ));
        return output;
    }

    public Value ptrToInt(Type type, Value value, Type outputType) {
        var output = Value.LocalVariable.random();
        instructions.add(new PtrToInt(
            output,
            type,
            value,
            outputType
        ));
        return output;
    }

    public Value intToPtr(Type type, Value value, Type outputType) {
        var output = Value.LocalVariable.random();
        instructions.add(new IntToPtr(
            output,
            type,
            value,
            outputType
        ));
        return output;
    }

    public void perform(Instruction instruction) {
        this.instructions.add(instruction);
    }


    public void ret(Type type, Value value) {
        instructions.add(new Ret(type, value));
    }

    public void ret() {
        instructions.add(new Ret(Types.VOID, null));
    }

    public void br(Value.LocalVariable label) {
        this.instructions.add(new BrLabel(label));
    }

    public void br(Value condition, Value.LocalVariable ifTrue, Value.LocalVariable ifFalse) {
        this.instructions.add(new BrIf(condition, ifTrue, ifFalse));
    }

    public void comment(String contents) {
        this.instructions.add(new Comment(contents));
    }

    public String ir() {
        return "  " + this.name.name() + ":\n"
            + this.instructions.stream().map(it -> "    " + it.ir()).collect(Collectors.joining("\n"));
    }
}
