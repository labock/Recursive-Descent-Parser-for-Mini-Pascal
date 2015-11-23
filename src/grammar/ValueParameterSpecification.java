package grammar;

import token.Token;

import java.util.List;

public class ValueParameterSpecification extends ParseTree{
	
	public class ValueParameterSpecification extends ParseTree{
		private IdentifierList iList;
	    private TypeIdentifier tIdent;
	    private boolean hasError;

	    private ValueParameterSpecification(int startToken, int numTokens, boolean hasError, String errorMsg, IdentifierList iList, TypeIdentifier tIdent){
	        super(startToken, numTokens, hasError, errorMsg);
	        this.iList = iList;
	        this.TypeIdentifier = tIdent;
	    }

	    public IdentifierList getIList(){
	        return this.iList;
	    }
	    public TypeIdentifier getTIdent(){
	        return this.tIdent;
	    }
	    public boolean hasError(){
	        return this.hasError;
	    }

	    public static ValueParameterSpecification parse(List<Token> tokens, int startToken){
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

	        TypeIdentifier tIdent = TypeIdentifier.parse(tokens, nextToken);
	        if(tIdent.hasError()){ //did the expression part have errors?
	            this.hasError = true;
	            errorMsg += ": parse error in expression";
	        }

	        int numTokens = iList.getNumTokens() + 1 + tIdent.getNumTokens();

	        return new ValueParameterSpecification(startToken, numTokens, hasErrors, errorMsg, iList, tIdent);
	    }