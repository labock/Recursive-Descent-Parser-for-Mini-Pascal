package grammar;

public class Program extends ParseTree{

    private ProgramHeading programHeading;
    private Block block;
    private boolean hasError;

    public Program(int startToken, int numTokens, boolean hasError, String errorMsg, ProgramHeading programHeading, Block block){
        super(startToken, numTokens, hasError, errorMsg);
        this.programHeading = programHeading;
        this.block = block;
    }

    public ProgramHeading getProgramHeading(){
        return this.programHeading;
    }
    public Expression getExprPart(){
        return this.exprPart;
    }
    public boolean hasError(){
        return this.hasError;
    }

    public static Program parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Program";

        ProgramHeader programHeader = programHeader.parse(tokens, startToken);
        if(programHeader.hasErrors()){
            this.hasError = true;
            this.errorMsg += ": parse error in program header ";
        }

        int nextToken = startToken + varPart.getNumTokens();

        if(!tokens.get(nextToken).getName().equals(";")){
            this.hasError = true;
            this.errorMsg += ": parse error in symbol ; ";
        }

        nextToken++;

        Block block = block.parse(tokens, nextToken);
        if(block.hasError()){
            this.hasError = true;
            errorMsg += ": parse error in block";
        }

        int numTokens = varPart.getNumTokens() + 1 + exprPart.getNumTokens();

        return new Program(startToken, numTokens, hasErrors, errorMsg, programHeader, block);
    }
}