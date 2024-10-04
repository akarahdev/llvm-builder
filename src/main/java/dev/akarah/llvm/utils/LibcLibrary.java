package dev.akarah.llvm.utils;

import dev.akarah.llvm.Module;
import dev.akarah.llvm.cfg.GlobalVariable;
import dev.akarah.llvm.inst.Types;
import dev.akarah.llvm.inst.Value;

import java.util.List;

public interface LibcLibrary extends LLVMLibrary {
    public static LibcLibrary IO = new LibcLibrary() {
        @Override
        public void modifyModule(Module module) {
            module
                .newFunction(new Value.GlobalVariable("puts"), function -> {
                    function
                        .parameter(Types.pointerTo(Types.integer(8)), new Value.NoCapture())
                        .returns(Types.integer(32));
                });
        }
    };
}
