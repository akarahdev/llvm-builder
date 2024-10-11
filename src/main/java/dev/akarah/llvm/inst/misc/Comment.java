package dev.akarah.llvm.inst.misc;

public record Comment(String contents) implements OtherOperation {
    @Override
    public String ir() {
        return "; {}".replace("{}", contents);
    }
}
