package dev.akarah.llvm.ir;

public class IRFormatter {
    public static String format(
            String base,
            IRStringConvertable... conversions
    ) {
        var o = base;
        for(var c : conversions) {
            o = o.replaceFirst("\\{}", c.ir());
        }
        return o;
    }
}
