package grammar;

import token.Token;
import java.util.List;

public class ProgramHeading extends ParseTree{

    public ProgramHeading(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }

    public static ProgramHeading parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Program";

        int nextToken = startToken;

        if(!tokens.get(nextToken).getName().equals("program")){
            hasErrors = true;
            errorMsg += ": expected 'program' ";
        }

        nextToken++;

        if(!tokens.get(nextToken).getType().equals(Token.Type.IDENTIFIER)){
            hasErrors = true;
            errorMsg += ": expected a program Identifier";
        }

        int numTokens = 2;

        return new ProgramHeading(startToken, numTokens, hasErrors, errorMsg);
    }
}