package grammar;

import token.Token;

import java.util.List;
public class VariableIdentifier extends ParseTree{
	private boolean hasError;

    private VariableIdentifier(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }
    
    public boolean hasError(){
        return this.hasError;
    }

    public static VariableIdentifier parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Variable Identifier";
        
        if(!tokens.get(starToken).getType().equals(Token.Type.IDENTIFIER)){
            hasErrors = true;
            errorMsg += ": expected a program Identifier";
        }

        int numTokens = 1;

        return new VariableIdentifier(startToken, numTokens, hasErrors, errorMsg);
    }
}
