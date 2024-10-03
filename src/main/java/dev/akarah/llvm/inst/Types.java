package dev.akarah.llvm.inst;

public final class Types {
    public static Type VOID = new Type.Void();

    public static Type integer(int width) {
        return new Type.Integer(width);
    }

    public static Type float32() {
        return new Type.Float();
    }

    public static Type float64() {
        return new Type.Double();
    }

    public static Type float128() {
        return new Type.FP128();
    }

    public static Type array(int length, Type subtype) {
        return new Type.Array(length, subtype);
    }

    public static Type struct(Type[] subtype) {
        return new Type.Structure(subtype);
    }
}
