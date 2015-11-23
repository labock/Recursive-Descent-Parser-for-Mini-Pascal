package token;

public class Token {
    private String name;
    private Type type;

    public Token(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public enum Type {
        IDENTIFIER, KEYWORD, SYMBOL, UNSIGNEDINTEGER, CHARACTERSTRING, ERROR
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.type + ", " + this.name;
    }
}
