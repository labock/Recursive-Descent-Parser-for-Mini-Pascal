package grammar;



public class StatementPart extends ParseTree{
	private CompoundStatement compState;
	private boolean hasError;
	
    public StatementPart(int startToken, int numTokens, boolean hasError, String errorMsg, CompountStatement compState){
        super(startToken, numTokens, hasError, errorMsg);
        this.compState = compState;
    }
    
    public CompoundStatement getCompState(){
    	return this.compState;
    }
    public boolean hasError(){
        return this.hasError;
    }
    
    public static StatementPart parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "StatementPart";
        
        CompoundStatement compState = CompoundStatement.parse(tokens, startToken);
        if(compState.hasError()){ //did the compound statement have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in Compound Statement";
        }
        
        int numTokens = compState.getNumTokens();
        
        return new StatementPart(startToken, numTokens, hasErrors, errorMsg, compState);
        
    }
    
}