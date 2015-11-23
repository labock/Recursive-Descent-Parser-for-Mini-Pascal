package grammar;
import token.Token;

import java.util.List;

public class VariableDeclaration extends ParseTree{
	private IdentifierList iList;
    private Type type;
    private boolean hasError;

    private VariableDeclaration(int startToken, int numTokens, boolean hasError, String errorMsg, IdentifierList iList, Type type){
        super(startToken, numTokens, hasError, errorMsg);
        this.iList = iList;
        this.Type = type;
    }

    public IdentifierList getIList(){
        return this.iList;
    }
    public Type getType(){
        return this.type;
    }
    public boolean hasError(){
        return this.hasError;
    }

    public static VariableDeclaration parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Assignment Statement";

        IdentifierList iList = IdentifierList.parse(tokens, startToken);
        if(iList.hasError()){ //did the variable part have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in variable ";
        }

        int nextToken = startToken + iList.getNumTokens(); //move the pointer to the end of the variable tokens

        if(!tokens.get(nextToken).getName().equals(":")){ //if the next token's value isn't := we have issues
            this.hasError = true;
            errorMsg += ": parse error in symbol := ";
        }

        nextToken++; //move the pointer to the next token

        Type type = Type.parse(tokens, nextToken);
        if(type.hasError()){ //did the expression part have errors?
            this.hasError = true;
            errorMsg += ": parse error in expression";
        }

        int numTokens = iList.getNumTokens() + 1 + type.getNumTokens();

        return new VariableDeclaration(startToken, numTokens, hasErrors, errorMsg, iList, type);
    }