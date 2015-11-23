import java.util.ArrayList;

abstract public class ParseTree {

    protected int startToken, numTokens;
    protected boolean hasError;
    protected String errorMessage;

    public static ParseTree parse(ArrayList<Token> tokens) {
        return ProgramParseTree.parse(tokens);
    }

    public int getStartToken() {
        return startToken;
    }

    public int getNumTokens() {
        return numTokens;
    }

    public boolean isHasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
