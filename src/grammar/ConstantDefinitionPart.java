package grammar;

import token.Token;

import java.util.Iterator;
import java.util.List;

public class ConstantDefinitionPart extends Block{

    private static ConstantDefinition cd = null;
    private static List<ConstantDefinition> cda = null;
    private static int mult = 0; //counter for how many constant definitions there were

    public ConstantDefinitionPart(int startToken, int numTokens, boolean hasError, String errorMsg){
        super(startToken, numTokens, hasError, errorMsg);
    }

    public ConstantDefinitionPart(int startToken, int numTokens, boolean hasError, String errorMsg, ConstantDefinition cd, List<ConstantDefinition> cda){
        super(startToken, numTokens, hasError, errorMsg);
        this.cd = cd;
        this.cda = cda;
    }

    public static ConstantDefinitionPart parse(List<Token> tokens, int startToken){
        boolean hasErrors = false;
        String errorMsg = "Constant definition part";

        int nextToken = startToken;

        if(!tokens.get(nextToken).getName().equals("const")){
            hasErrors = true;
            errorMsg += ": parse error in symbol const ";
        }

        nextToken = startToken++;

        if(!cd.equals(null)){
            ConstantDefinition cd = cd.parse(tokens, startToken);
            if(cd.hasError()){
                hasErrors = true;
                errorMsg += ": parse error in constant definition ";
            }
        }

        nextToken += cd.getNumTokens();

        if(!cda.equals(null)){
            Iterator<ConstantDefinition> cdait = cda.iterator();
            while(cdait.hasNext()){
                ConstantDefinition currcd = cdait.next();
                if(currcd.hasError()){
                    hasErrors = true;
                    errorMsg += ": parse error in constant definition ";
                }

                nextToken += currcd.getNumTokens();

                if(!tokens.get(nextToken).getName().equals(";")){
                    hasErrors = true;
                    errorMsg += ": parse error in symbol ; ";
                }
                mult++;
            }
        }

        int numTokens = cd.getNumTokens() + mult;

        return new ConstantDefinitionPart (startToken, numTokens, hasErrors, errorMsg, cd, cda);
    }

}