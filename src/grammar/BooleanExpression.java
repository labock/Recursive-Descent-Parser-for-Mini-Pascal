package grammar;

import token.Token;

import java.util.List;

public class BooleanExpression extends ParseTree{
	private Expression exp;
    private boolean hasError;

    private BooleanExpression(int startToken, int numTokens, boolean hasError, String errorMsg, Expression exp){
        super(startToken, numTokens, hasError, errorMsg);
        this.exp = exp;
    }

    public Expression getExp(){
        return this.exp;
    }
    public boolean hasError(){
        return this.hasError;
    }

    public static BooleanExpression parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "BooleanExpression";

        Expression exp = Variable.parse(tokens, startToken);
        if(exp.hasError()){ //did the variable part have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in Boolean Expression ";
        }

        int numTokens = exp.getNumTokens();

        return new BooleanExpression(startToken, numTokens, hasErrors, errorMsg, exp);
    }
	
}
}
