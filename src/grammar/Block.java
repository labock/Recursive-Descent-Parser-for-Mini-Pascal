package grammar;

import token.Token;

import java.util.List;

public class Block extends ParseTree{
    private ConstantDefinitionPart cdp;
    private VariableDeclarationPart vdp;
    private ProcedureDeclarationPart pdp;
    private StatementPart sp;

    public ConstantDefinitionPart getCdp() {
        return cdp;
    }

    public VariableDeclarationPart getVdp() {
        return vdp;
    }

    public ProcedureDeclarationPart getPdp() {
        return pdp;
    }

    public StatementPart getSp() {
        return sp;
    }

    public Block(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }

    public static Block parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Block";

        ConstantDefinitionPart cdp = cdp.parse(tokens, startToken);
        if(cdp.hasError()){
            hasErrors = true;
            errorMsg += ": error in constant definition";
        }

        int nextToken = startToken + cdp.getNumTokens();

        VariableDeclarationPart vdp = vdp.parse(tokens, nextToken);
        if(vdp.hasError()){
            hasErrors = true;
            errorMsg += ": error in variable declaration";
        }

        nextToken += vdp.getNumTokens();

        ProcedureDeclarationPart pdp = pdp.parse(tokens, nextToken);
        if(pdp.hasError()){
            hasErrors = true;
            errorMsg += ": error in procedure declaration";
        }

        nextToken += pdp.getNumTokens();

        StatementPart sp = sp.parse(tokens, nextToken);
        if(sp.hasError()){
            hasErrors = true;
            errorMsg += ": error in statement";
        }

        int numTokens = cdp.getNumTokens() + vdp.getNumTokens() + pdp.getNumTokens() + sp.getNumTokens();
        return new Block(startToken, numTokens, hasErrors, errorMsg);
    }
}