package grammar;

import token.Token;

import java.util.List;
public class ProcedureIdentifier extends ParseTree {
	private boolean hasError;

    private ProcedureIdentifier(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }
    
    public boolean hasError(){
        return this.hasError;
    }

    public static ProcedureIdentifier parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Procedure Identifier";
        
        if(!tokens.get(starToken).getType().equals(Token.Type.IDENTIFIER)){
            hasErrors = true;
            errorMsg += ": expected a program Identifier";
        }

        int numTokens = 1;

        return new ProcedureIdentifier(startToken, numTokens, hasErrors, errorMsg);
    }
}
