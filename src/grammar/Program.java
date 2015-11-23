package grammar;

import token.Token;
import java.util.List;

public class Program extends ParseTree{

    private ProgramHeading programHeading;
    private Block block;

    public Program(int startToken, int numTokens, boolean hasError, String errorMsg, ProgramHeading programHeading, Block block){
        super(startToken, numTokens, hasError, errorMsg);
        this.programHeading = programHeading;
        this.block = block;
    }

    public ProgramHeading getProgramHeading(){
        return this.programHeading;
    }
    public Block getBlock(){
        return block;
    }

    public static Program parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Program";

        ProgramHeading programHeading = programHeading.parse(tokens, startToken);
        if(programHeading.hasError()){
            hasErrors = true;
            errorMsg += ": parse error in program header ";
        }

        int nextToken = startToken + programHeading.getNumTokens();

        if(!tokens.get(nextToken).getName().equals(";")){
            hasErrors = true;
            errorMsg += ": parse error in symbol ; ";
        }

        nextToken++;

        Block block = block.parse(tokens, nextToken);
        if(block.hasError()){
            hasErrors = true;
            errorMsg += ": parse error in block";
        }

        int numTokens = programHeading.getNumTokens() + 1 + block.getNumTokens();

        return new Program(startToken, numTokens, hasErrors, errorMsg, programHeading, block);
    }
}