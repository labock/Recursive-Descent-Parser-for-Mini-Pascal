package grammar;

public class ProcedureHeading extends ParseTree{

    private FormalParameterList fpl;

    public ProcedureHeading(int startToken, int numTokens, boolean hasError, FormalParameterList fpl){
        super(startToken, numTokens, hasError, errorMsg);
        this.fpl = fpl;
    }

    public static ProcedureHeading parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Procedure Heading";

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