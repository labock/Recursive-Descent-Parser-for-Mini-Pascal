package grammar;

import token.Token;

import java.util.List;
public class BoundIdentifier extends ParseTree {
	private boolean hasError;

    private BoundIdentifier(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }
    
    public boolean hasError(){
        return this.hasError;
    }

    public static BoundIdentifier parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = " Bound Identifier";
        
        if(!tokens.get(starToken).getType().equals(Token.Type.IDENTIFIER)){
            hasErrors = true;
            errorMsg += ": expected a program Identifier";
        }

        int numTokens = 1;

        return new BoundIdentifier(startToken, numTokens, hasErrors, errorMsg);
    }
}
