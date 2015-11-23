package token;

public class Error extends Token {

    public Error(String name) {
        super(name, Token.Type.ERROR);
    }

}
