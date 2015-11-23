package grammar;

import java.util.List;

public class ProcedureDeclaration extends ParseTree{

    Private ProcedureHeading ph;
    Private Block block;

    public ProcedureDeclaration(int startToken, int numTokens, boolean hasError, ProcedueHeading ph, Block block){
        super(startToken, numTokens, hasError, errorMsg);
        this.ph = ph;
        this.block = block;
    }

    public ProcedureHeading getProcedureHeading(){
        return this.ph;
    }

    public Block getBlock(){
        return this.Block;
    }

    public static ProcedureDeclaration parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Procedure Declaration";

        ProcedureHeading ph = ProcedureHeading.parse(tokens, startToken);
        if(varPart.hasError()){ //did the variable part have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in  procedure heading";
        }

        int nextToken = startToken + programHeading.getNumTokens();

        if(!tokens.get(nextToken).getName().equals(";")){
            hasErrors = true;
            errorMsg += ": parse error in symbol ; ";
        }

        nextToken++;

        Block block = block.parse(tokens, startToken);
        if(varPart.hasError()){ //did the variable part have errors?
            this.hasError = true;
            this.errorMsg += ": parse error in block";
        }

        int numTokens = programHeading.getNumTokens() + 1 + block.getNumTokens();

        return new Program(startToken, numTokens, hasErrors, errorMsg, programHeading, block);

    }
}