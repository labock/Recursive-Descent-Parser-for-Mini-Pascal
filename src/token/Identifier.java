package token;

public class Identifier extends Token {

    public Identifier(String name) {
        super(name, Token.Type.IDENTIFIER);
    }

}
