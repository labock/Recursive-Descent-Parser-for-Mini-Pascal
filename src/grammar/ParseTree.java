package grammar;

import token.ProgramParseTree;
import token.Token;

import java.util.ArrayList;

public abstract class ParseTree {

    protected static int startToken, numTokens;
    protected static boolean hasError;
    protected static String errorMsg;

    public ParseTree(int startToken, int numTokens, boolean hasError, String errorMsg){
        this.startToken = startToken;
        this.numTokens = numTokens;
        this.hasError = hasError;
        this.errorMsg = errorMsg;
    }

    public static ParseTree parse(ArrayList<Token> tokens) {
        ProgramParseTree ppt = new ProgramParseTree();
        return ppt.parse(tokens);
    }

    public int getStartToken() {
        return startToken;
    }

    public int getNumTokens() {
        return numTokens;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMsg;
    }
}
