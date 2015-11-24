package grammar;

import token.Token;

import java.util.List;

public class Variable extends ParseTree{
	private VariableIdentifier var;
    private boolean hasError;

    private Variable(int startToken, int numTokens, boolean hasError, String errorMsg, VariableIdentifier var){
        super(startToken, numTokens, hasError, errorMsg);
        this.var = var;
    }

    public VariableIdentifier getVar(){
        return this.var;
    }
    public boolean hasError(){
        return this.hasError;
    }

    public static Variable parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Variable";

        VariableIdentifier bar = Variable.parse(tokens, startToken);
        if(var.hasError()){ //did the variable part have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in Variable Identifier ";
        }

        int numTokens = var.getNumTokens();

        return new Variable(startToken, numTokens, hasErrors, errorMsg, var);
    }
	
}
