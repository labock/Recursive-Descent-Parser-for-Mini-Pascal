package grammar;

import token.Token;

import java.util.List;

public class ConstantDefinition extends ParseTree{
    private Constant con;
    private boolean hasError;


    public ConstantDefinition()(int startToken, int numTokens, boolean hasError, String errorMsg, Constant con){
        super(startToken, numTokens, hasError, errorMsg);
        this.con = con;
    }

    public Constant getConstant(){
        return this.con;
    }
    public boolean hasError(){
        return this.hasError;
    }

    public static ConstantDefinition parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Program";

        int nextToken = startToken;

        if(!tokens.get(nextToken).getType().equals(Token.Type.IDENTIFIER)){
            hasErrors = true;
            errorMsg += ": expected a program Identifier";
        }

        nextToken++;

        if(!tokens.get(nextToken).getName().equals("=")){ //if the next token's value isn't = we have issues
            this.hasError = true;
            errorMsg += ": parse error in symbol := ";
        }

        nextToken++;

        Constant con = Constant.parse(tokens, nextToken);
        if(con.hasError()){ //did the expression part have errors?
            hasErrors = true;
            errorMsg += ": parse error in expression";
        }

        int numTokens = 2 + con.getNumTokens();

        return new ConstantDefinition(startToken, numTokens, hasErrors, errorMsg, con);

    }

}