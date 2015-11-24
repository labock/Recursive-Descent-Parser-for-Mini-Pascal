package grammar;


public class Type extends ParseTree{
	private TypeIdentifier type;
    private boolean hasError;

    private Type(int startToken, int numTokens, boolean hasError, String errorMsg, TypeIdentifier type){
        super(startToken, numTokens, hasError, errorMsg);
        this.type = type;
    }

    public TypeIdentifier getTypeI(){
        return this.type;
    }
    public boolean hasError(){
        return this.hasError;
    }

    public static Type parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Type";

        TypeIdentifier type = Type.parse(tokens, startToken);
        if(varPart.hasError()){ //did the Type part have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in Type Identifier";
        }

        int numTokens = type.getNumTokens();

        return new Type(startToken, numTokens, hasErrors, errorMsg, type);
    }
	
}