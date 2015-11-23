import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LexicalScanner {
    public static void main(String args[]) {
        ArrayList<Token> tokens = readAndCreateTokens(new File("input.txt"));
        ParseTree pt = parser(tokens);
        writeToFile(tokens, new File("output.txt"));
    }

    public static ArrayList<Token> readAndCreateTokens(File file) {
        Scanner scanner;
        ArrayList<Token> results = new ArrayList<>();
        String currentLine, tempString, tempWord;
        Character currentCharacter;
        StringBuilder tempStringBuilder;

        String[] symbolsArray = {"+", "-", "*", "=", "<", ">", "<=", ">=", "<>", ".", ",", ":", ";", ":=", "(", ")", "{", "}"};
        List<String> symbols = Arrays.asList(symbolsArray);

        String[] possibleCommentArray = {"(", "{"};
        List<String> possibleComment = Arrays.asList(possibleCommentArray);

        String[] possibleDoubleArray = {"<", ">", ":"};
        List<String> possibleDouble = Arrays.asList(possibleDoubleArray);

        String[] keywordsArray = {"const", "procedure", "program", "var", "begin", "end", "if", "then", "else", "do", "while", "and", "not", "or", "div", "mod"};
        List<String> keywords = Arrays.asList(keywordsArray);


        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                currentLine = scanner.nextLine();

                for (int currentLineIndex = 0; currentLineIndex < currentLine.length(); currentLineIndex++) {
                    currentCharacter = currentLine.charAt(currentLineIndex);

                    // State 1
                    // Alphabetic characters
                    if (Character.isAlphabetic(currentCharacter)) {
                        // Generate the temporary string
                        tempString = currentLine.substring(currentLineIndex).toLowerCase();

                        // Scan each character in tempString
                        for (int currentCharacterIndex = 0; currentCharacterIndex < tempString.length(); currentCharacterIndex++) {
                            // Check to see if the current character is neither alphabetic nor numeric or is the last character in the string
                            if ((!(Character.isAlphabetic(tempString.charAt(currentCharacterIndex))) && !(Character.isDigit(tempString.charAt(currentCharacterIndex)))) || currentCharacterIndex == tempString.length() - 1) {
                                // Grab the substring up to the previous character
                                tempWord = tempString.substring(0, currentCharacterIndex);
                                // Accounts for tempString ending with a whitespace
                                tempWord = tempWord.trim();

                                // See if tempWord exists in keywords
                                if (keywords.contains(tempWord)) {
                                    // Create and add a keyword
                                    System.out.println("keyword added - " + tempWord);
                                    results.add(new Keyword(tempWord));
                                } else {
                                    // Create and add an identifier
                                    System.out.println("identifier added - " + tempWord);
                                    results.add(new Identifier(tempWord));
                                }
                                // Update currentLineIndex
                                currentLineIndex += (currentCharacterIndex - 1);
                                break;
                            }

                        }

                    }

                    // State 2
                    // Numeric characters
                    else if (Character.isDigit(currentCharacter)) {
                        // Generate the temporary string
                        tempString = currentLine.substring(currentLineIndex);

                        // Scan each character in tempString
                        for (int currentCharacterIndex = 0; currentCharacterIndex < tempString.length(); currentCharacterIndex++) {
                            // Check to see if the current character is not numeric or is the last character in the string
                            if (!Character.isDigit(tempString.charAt(currentCharacterIndex)) || currentCharacterIndex == tempString.length() - 1) {
                                // Grab the substring up to the previous character
                                tempWord = tempString.substring(0, currentCharacterIndex + 1);
                                // Accounts for tempString ending with a whitespace
                                tempWord = tempWord.trim();

                                // Create and add an unsigned integer
                                System.out.println("unsigned integer added - " + tempWord);
                                results.add(new UnsignedInteger(tempWord));

                                // Update currentLineIndex
                                currentLineIndex += currentCharacterIndex;
                                break;
                            }

                        }
                    }

                    // State 3
                    // Apostrophes
                    else if (currentCharacter == '\'') {
                        // Generate the temporary string
                        tempString = currentLine.substring(currentLineIndex);

                        // Generate the temporary String Builder string
                        tempStringBuilder = new StringBuilder();

                        // Scan each character in tempString
                        for (int currentCharacterIndex = 0; currentCharacterIndex < tempString.length(); currentCharacterIndex++) {

                            // Check for the end of the line
                            if (currentCharacterIndex == tempString.length() - 1) {

                                // Check for a non-terminated string
                                if (tempString.charAt(currentCharacterIndex) != '\'') {
                                    // Create and add an error character string
                                    System.out.println("error added - NON-TERMINATED STRING");
                                    results.add(new Error("ERROR: NON-TERMINATED STRING"));
                                } else {
                                    // Add the character string to the resulting tokens
                                    System.out.println("character string added - " + tempStringBuilder.toString());
                                    results.add(new CharacterString(tempStringBuilder.toString()));
                                    // Ignore the next apostrophe
                                    currentCharacterIndex++;
                                }


                                // Update currentLineIndex
                                currentLineIndex += currentCharacterIndex;
                                break;
                            }

                            // State 4
                            // Check to see if the current character is an apostrophe
                            if (currentCharacterIndex == 0 && tempString.charAt(currentCharacterIndex + 1) != '\'') {
                                currentCharacterIndex = 1;
                            }
                            if (tempString.charAt(currentCharacterIndex) == '\'') {
                                // Check for another immediate following apostrophe
                                if (tempString.charAt(currentCharacterIndex + 1) == '\'') {
                                    tempStringBuilder.append('\'');
                                    // Ignore the next apostrophe
                                    currentCharacterIndex++;
                                } else {
                                    // Create and add a character string
                                    System.out.println("character string added - " + tempStringBuilder.toString());
                                    results.add(new CharacterString(tempStringBuilder.toString()));
                                    // Ignore the next apostrophe
                                    currentCharacterIndex++;

                                    // Update currentLineIndex
                                    currentLineIndex += currentCharacterIndex;
                                    break;
                                }
                            } else {
                                tempStringBuilder.append(tempString.charAt(currentCharacterIndex));
                            }

                        }
                    }


                    // State 5
                    // Symbols
                    else if ((symbols.contains(currentCharacter.toString()))) {
                        // Check to see if symbol is not a possible double symbol or a comment
                        if (!(possibleComment.contains(currentCharacter.toString())) && !(possibleDouble.contains(currentCharacter.toString()))) {
                            // Create and add a symbol
                            System.out.println("symbol added - " + currentCharacter);
                            results.add(new Symbol(currentCharacter.toString()));
                        } else {
                            // State 7
                            // Comments
                            if (currentCharacter == '{' || (currentCharacter == '(' && currentLine.charAt(currentLineIndex + 1) == '*')) {
                                // Find the end of the comment
                                // Generate the temporary string
                                tempString = currentLine.substring(currentLineIndex);
                                // Temporary closing character
                                char closingCharacter = ' ';

                                switch (currentCharacter) {
                                    case '{':
                                        closingCharacter = '}';
                                        break;
                                    case '(':
                                        closingCharacter = '*';
                                        break;
                                }

                                // Scan each character in tempString
                                for (int currentCharacterIndex = 0; currentCharacterIndex < tempString.length(); currentCharacterIndex++) {
                                    if (tempString.charAt(currentCharacterIndex) == closingCharacter) {
                                        // Check for two character closing symbol
                                        if (closingCharacter == '*') {

                                            if (currentCharacterIndex != tempString.length() - 1) {
                                                if (tempString.charAt(currentCharacterIndex + 1) == ')') {
                                                    // Ignore the next character
                                                    currentCharacterIndex++;

                                                    // Update currentLineIndex
                                                    currentLineIndex += currentCharacterIndex;
                                                    break;
                                                }
                                            } else {
                                                // Update currentLineIndex
                                                currentLineIndex += currentCharacterIndex;
                                                break;
                                            }


                                        } else {
                                            // Update currentLineIndex
                                            currentLineIndex += currentCharacterIndex;
                                            break;
                                        }
                                    }

                                    // Check for the end of tempString and move on to the next line
                                    if (currentCharacterIndex + 1 == tempString.length()) {
                                        if (scanner.hasNext()) {
                                            currentLine = scanner.nextLine();
                                            tempString = currentLine;
                                            currentCharacterIndex = 0;
                                            currentLineIndex = 0;
                                        }
                                        // End of the file
                                        else {
                                            System.out.println("error added - NO CLOSING COMMENT DELIMITER");
                                            results.add(new Error("ERROR: NO CLOSING COMMENT DELIMITER"));
                                            currentLineIndex = currentLine.length() - 1;
                                            break;
                                        }
                                    }
                                }
                            } else if (possibleDouble.contains(currentCharacter.toString())) {
                                StringBuilder doubleChar = new StringBuilder();
                                if (currentLineIndex != currentLine.length() - 1) {
                                    switch (currentCharacter) {
                                        case '<':
                                            if (currentLine.charAt(currentLineIndex + 1) == '=' || currentLine.charAt(currentLineIndex + 1) == '>') {
                                                // Create and add a symbol
                                                doubleChar.append(currentCharacter);
                                                doubleChar.append(currentLine.charAt(currentLineIndex + 1));
                                                System.out.println("symbol added - " + doubleChar);
                                                results.add(new Symbol(doubleChar.toString()));
                                                // Ignore the next character
                                                currentLineIndex++;
                                            }
                                            break;
                                        case '>':
                                        case ':':
                                            if (currentLine.charAt(currentLineIndex + 1) == '=') {
                                                // Create and add a symbol
                                                doubleChar.append(currentCharacter);
                                                doubleChar.append(currentLine.charAt(currentLineIndex + 1));
                                                System.out.println("symbol added - " + doubleChar);
                                                results.add(new Symbol(doubleChar.toString()));
                                                // Ignore the next character
                                                currentLineIndex++;
                                            }
                                            break;

                                    }
                                } else {
                                    // Create and add a symbol
                                    System.out.println("symbol added - " + currentCharacter);
                                    results.add(new Symbol(currentCharacter.toString()));
                                }
                            } else {
                                // Create and add a symbol
                                System.out.println("symbol added - " + currentCharacter);
                                results.add(new Symbol(currentCharacter.toString()));
                            }
                        }
                    }
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static ProgramParseTree parser (ArrayList<Token> tokens){
        return new ProgramParseTree();
    }

    public static void writeToFile(ArrayList<Token> tokens, File file) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);

            for (Token token : tokens) {
                pw.print(token + "\n");
            }

            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
