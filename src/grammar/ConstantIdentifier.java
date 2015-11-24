package grammar;
import token.Token;

import java.util.List;

public class ConstantIdentifier extends ParseTree{
	private boolean hasError;

    private ConstantIdentifier(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }
    
    public boolean hasError(){
        return this.hasError;
    }

    public static ConstantIdentifier parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Constant Identifier";
        
        if(!tokens.get(starToken).getType().equals(Token.Type.IDENTIFIER)){
            hasErrors = true;
            errorMsg += ": expected a program Identifier";
        }

        int numTokens = 1;

        return new ConstantIdentifier(startToken, numTokens, hasErrors, errorMsg);
    }
    
    
}
