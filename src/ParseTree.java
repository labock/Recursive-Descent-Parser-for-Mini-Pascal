import java.util.ArrayList;

abstract public class ParseTree {
    public static ParseTree parse(ArrayList<Token> tokens){
        return ProgramParseTree.parse(tokens);
    }
}
