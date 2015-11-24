package grammar;

import token.Token;

import java.util.List;

public class UnsignedInteger extends ParseTree {
	private boolean hasError;

    private UnsignedInteger(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }
    
    public boolean hasError(){
        return this.hasError;
    }

    public static UnsignedInteger parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Unsigned Integer";
        
        if(!tokens.get(starToken).getType().equals(Token.Type.UNSIGNEDINTEGER)){
            hasErrors = true;
            errorMsg += ": expected a program UNSIGNEDINTEGER";
        }

        int numTokens = 1;

        return new UnsignedInteger(startToken, numTokens, hasErrors, errorMsg);
	
}
