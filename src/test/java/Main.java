import dev.akarah.llvm.Module;
import dev.akarah.llvm.cfg.BasicBlock;
import dev.akarah.llvm.cfg.Function;
import dev.akarah.llvm.cfg.GlobalVariable;
import dev.akarah.llvm.inst.*;
import dev.akarah.llvm.utils.LibcLibrary;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var module = Module.of("test-module");
        var gv = Value.GlobalVariable.random();
        module.newGlobal(gv, globalVariable -> {
            globalVariable
                .withType(Types.array(14, Types.integer(8)))
                .withValue(new Value.CStringConstant("Hello, world!\\\\00"));
        });
        module.withLibrary(LibcLibrary.IO);
        module.newFunction(new Value.GlobalVariable("main"), function -> {
                var bb = BasicBlock.of(Value.LocalVariable.random());
                bb.add(
                        Types.integer(32),
                        new Value.IntegerConstant("10"),
                        new Value.IntegerConstant("20"));
                bb.call(Types.integer(32), new Value.GlobalVariable("puts"), List.of(
                   new Instruction.Call.Parameter(
                       Types.pointerTo(Types.array(13, Types.integer(8))),
                       gv
                   )
                ));
                bb.ret();

                function.returns(Types.VOID)
                    .basicBlock(bb);
            });
        module.compile();
    }
}