import dev.akarah.llvm.cfg.BasicBlock;
import dev.akarah.llvm.inst.Constant;
import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Types;
import dev.akarah.llvm.inst.Value;

public class Main {
    public static void main(String[] args) {
        var bb = BasicBlock.of(Value.LocalVariable.random());
        var out = bb.add(
                new Type.Void(),
                bb.sub(
                        Types.VOID,
                        Constant.constant(10),
                        Constant.constant(15)
                ),
                bb.mul(
                        Types.VOID,
                        Constant.constant(12),
                        bb.div(
                                Types.VOID,
                                Constant.constant(4),
                                Constant.constant(6)
                        )
                )
        );
        bb.ret(Types.VOID, out);
        System.out.println(bb);
        System.out.println(bb.ir());
    }
}