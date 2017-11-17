
import java.io.IOException;
import java.util.Scanner;

public class FileCounter {
    private int characterCount, wordCount, lineCount;

    public FileCounter(){

    }

    /**
     * processes an input source and adds its character, word, and line counts to the respective variables.
     * @param in the scanner to process
     * @throws IOException
     */
    public void read(Scanner in) throws IOException{
        while(in.hasNext()){
            String line = in.nextLine();
            lineCount++;
            for(int i = 0;i < line.length(); i++){
                if(line.charAt(i) != ' ') {
                    characterCount++;
                    if(i == 0 || line.charAt(i-1) == ' ')
                        wordCount++;
                }
                else{
                    while (i < line.length() - 1 && line.charAt(++i) == ' ');
                    if(i != line.length()-1)
                        i--;
                }
            }
        }
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getLineCount() {
        return lineCount;
    }
}
