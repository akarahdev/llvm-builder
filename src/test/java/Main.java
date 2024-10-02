import dev.akarah.llvm.cfg.BasicBlock;
import dev.akarah.llvm.inst.Type;
import dev.akarah.llvm.inst.Value;

public class Main {
    public static void main(String[] args) {
        var bb = BasicBlock.of(Value.LocalVariable.random());
        var out = bb.add(
                new Type.Void(),
                bb.sub(
                        Type.VOID,
                        bb.constant(10),
                        bb.constant(15)
                ),
                bb.mul(
                        Type.VOID,
                        bb.constant(12),
                        bb.div(
                                Type.VOID,
                                bb.constant(4),
                                bb.constant(6)
                        )
                )
        );
        bb.ret(Type.VOID, out);
        System.out.println(bb);
        System.out.println(bb.ir());
    }
}