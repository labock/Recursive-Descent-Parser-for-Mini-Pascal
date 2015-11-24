package grammar;
import token.Token;

import java.util.List;

public class CharacterString extends ParseTree{
	
	private boolean hasError;

    private CharacterString(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }
    
    public boolean hasError(){
        return this.hasError;
    }

    public static CharacterString parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Character String";
        
        if(!tokens.get(starToken).getType().equals(Token.Type.CHARACTERSTRING)){
            hasErrors = true;
            errorMsg += ": expected a program CharacterString";
        }

        int numTokens = 1;

        return new CharacterString(startToken, numTokens, hasErrors, errorMsg);
    }
}
