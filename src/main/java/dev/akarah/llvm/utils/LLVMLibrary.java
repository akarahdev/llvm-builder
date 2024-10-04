package dev.akarah.llvm.utils;

import dev.akarah.llvm.Module;
import dev.akarah.llvm.cfg.GlobalVariable;

import java.util.List;

public interface LLVMLibrary {
    void modifyModule(Module module);
}
