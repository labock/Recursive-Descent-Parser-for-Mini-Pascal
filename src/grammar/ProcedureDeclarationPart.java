package grammar;

import token.Token;

import java.util.List;

public class ProcedureDeclarationPart extends ParseTree{

    private List<ProcedureDeclaration> pd;

    public ProcedureDeclarationPart(int startToken, int numTokens, boolean hasError, String errorMsg, List<ProcedureDeclaration> pd){
        super(startToken, numTokens, hasError, errorMsg);
        this.pd = pd;
    }

    public List<ProcedureDeclaration> getProcedureDeclaration(){
        return this.pd;
    }

    public static ProcedureDeclarationPart parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Procedure Declaration Part";

        ProcedureDeclaration pd = ProcedureDeclaration.parse(tokens, startToken);

        if(pd.hasError()){ //did the variable part have errors?
            hasError = true;
            errorMsg += ": parse error in procedure declaration";
        }

        int nextToken = startToken + pd.getNumTokens(); //move the pointer to the end of the variable tokens

        if(!tokens.get(nextToken).getName().equals(";")){
            hasErrors = true;
            errorMsg += ": parse error in symbol ; ";
        }
        int numTokens = pd.getNumTokens();

        return new ProcedureDeclarationPart(startToken, numTokens, hasErrors, errorMsg, pd);

    }
}
