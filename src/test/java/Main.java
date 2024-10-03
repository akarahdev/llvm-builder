import dev.akarah.llvm.cfg.BasicBlock;
import dev.akarah.llvm.cfg.Function;
import dev.akarah.llvm.inst.Constant;
import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Types;
import dev.akarah.llvm.inst.Value;

public class Main {
    public static void main(String[] args) {


        var p0 = Value.LocalVariable.random();

        var bb = BasicBlock.of(Value.LocalVariable.random());
        var out = bb.add(
            Types.integer(32),
            bb.sub(
                Types.integer(32),
                Constant.constant(10),
                Constant.constant(15)
            ),
            bb.mul(
                Types.integer(32),
                p0,
                bb.sdiv(
                    Types.integer(32),
                    Constant.constant(4),
                    Constant.constant(6)
                )
            )
        );
        bb.ret(Types.integer(32), out);

        var f = Function.of(new Value.GlobalVariable("main"))
            .returns(Types.integer(32))
            .parameter(Types.integer(32), p0)
            .basicBlock(bb);

        System.out.println(f.ir());
    }
}