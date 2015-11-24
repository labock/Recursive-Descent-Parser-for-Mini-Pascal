package grammar;

import token.Token;

import java.util.List;

public class AssignmentStatement extends ParseTree{
    private Variable varPart;
    private Expression exprPart;
    private boolean hasError;

    private AssignmentStatement(int startToken, int numTokens, boolean hasError, String errorMsg, Variable varPart, Expression exprPart){
        super(startToken, numTokens, hasError, errorMsg);
        this.varPart = varPart;
        this.exprPart = exprPart;
    }

    public Variable getVarPart(){
        return this.varPart;
    }
    public Expression getExprPart(){
        return this.exprPart;
    }
    public boolean hasError(){
        return this.hasError;
    }

    public static AssignmentStatement parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Assignment Statement";

        Variable varPart = Variable.parse(tokens, startToken);
        if(varPart.hasError()){ //did the variable part have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in variable ";
        }

        int nextToken = startToken + varPart.getNumTokens(); //move the pointer to the end of the variable tokens

        if(!tokens.get(nextToken).getName().equals(":=")){ //if the next token's value isn't := we have issues
            this.hasError = true;
            errorMsg += ": parse error in symbol := ";
        }

        nextToken++; //move the pointer to the next token

        Expression exprPart = Expression.parse(tokens, nextToken);
        if(exprPart.hasError()){ //did the expression part have errors?
            this.hasError = true;
            errorMsg += ": parse error in expression";
        }

        int numTokens = varPart.getNumTokens() + 1 + exprPart.getNumTokens();

        return new AssignmentStatement(startToken, numTokens, hasErrors, errorMsg, varPart, exprPart);
    }
}
