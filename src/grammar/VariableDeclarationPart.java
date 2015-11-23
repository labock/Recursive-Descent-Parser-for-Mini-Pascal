package grammar;

import token.Token;

import java.util.Iterator;
import java.util.List;

public class ConstantDefinitionPart extends Block{

    private static VariableDeclaration vd = null;
    private static List<VariableDeclaration> vda = null;
    private static int mult = 0; //counter for how many constant definitions there were

    public ConstantDefinitionPart(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }

    public ConstantDefinitionPart(int startToken, int numTokens, boolean hasError, String errorMsg, VariableDeclaration vd, List<VariableDeclaration> vda){
        super(startToken, numTokens, hasError, errorMsg);
        this.vd = vd;
        this.vda = vda;
    }

    public static ConstantDefinitionPart parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Variable declaration part";

        int nextToken = startToken;

        if(!tokens.get(nextToken).getName().equals("var")){
            hasErrors = true;
            errorMsg += ": parse error in symbol var ";
        }

        nextToken = startToken++;

        if(!vd.equals(null)){
            VariableDeclaration vd = vd.parse(tokens, startToken);
            if(vd.hasError()){
                hasErrors = true;
                errorMsg += ": parse error in constant definition ";
            }
        }

        nextToken += vd.getNumTokens();

        if(!vda.equals(null)){
            Iterator<ConstantDefinition> vdait = vda.iterator();
            while(vdait.hasNext()){
                VariableDeclaration currvd = vdait.next();
                if(currvd.hasError()){
                    hasErrors = true;
                    errorMsg += ": parse error in constant definition ";
                }

                nextToken += currvd.getNumTokens();

                if(!tokens.get(nextToken).getName().equals(";")){
                    hasErrors = true;
                    errorMsg += ": parse error in symbol ; ";
                }
                mult++;
            }
        }

        int numTokens = vd.getNumTokens() + mult;

        return new ConstantDefinitionPart (startToken, numTokens, hasErrors, errorMsg, vd, vda);
    }

}