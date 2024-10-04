package dev.akarah.llvm;

import dev.akarah.llvm.cfg.Function;
import dev.akarah.llvm.cfg.GlobalVariable;
import dev.akarah.llvm.inst.Value;
import dev.akarah.llvm.ir.IRStringConvertable;
import dev.akarah.llvm.utils.LLVMLibrary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Module implements IRStringConvertable {
    public String name;
    public List<Function> functions = new ArrayList<>();
    public List<GlobalVariable> globalVariables = new ArrayList<>();

    public static Module of(String moduleName) {
        var m = new Module();
        m.name = moduleName;
        return m;
    }

    public Module newFunction(Value.GlobalVariable functionName, Consumer<Function> functionConsumer) {
        var f = Function.of(functionName);
        functionConsumer.accept(f);
        this.functions.add(f);
        return this;
    }

    public Module newGlobal(Value.GlobalVariable globalName, Consumer<GlobalVariable> globalConsumer) {
        var f = GlobalVariable.of(globalName);
        globalConsumer.accept(f);
        this.globalVariables.add(f);
        return this;
    }

    public Module withLibrary(LLVMLibrary library) {
        library.modifyModule(this);
        return this;
    }

    public void compile() {
        try {
            var buildFolder = Path.of("./build");
            if(!Files.exists(buildFolder))
                Files.createDirectory(buildFolder);

            var modulesFolder = Path.of("./build/ir");
            if(!Files.exists(modulesFolder))
                Files.createDirectory(modulesFolder);

            var newFile = Path.of("./build/ir/" + this.name + ".ll");
            if(!Files.exists(newFile)) {
                Files.createFile(newFile);
            } else {
                Files.delete(newFile);
                Files.createFile(newFile);
            }
            Files.writeString(newFile, this.ir());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String ir() {
        var sb = new StringBuilder();

        for(var global : this.globalVariables) {
            sb.append(global.ir());
            sb.append("\n");
        }

        for(var function : this.functions) {
            sb.append(function.ir());
            sb.append("\n");
        }

        return sb.toString();
    }
}
