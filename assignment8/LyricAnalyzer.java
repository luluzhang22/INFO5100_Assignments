
import java.io.*;
import java.util.*;

public class LyricAnalyzer {
    public static void main(String[] args) throws IOException {
        LyricAnalyzer analyzer = new LyricAnalyzer();
        analyzer.read(new File("Question2_test4.txt"));
        analyzer.displayWords();
        analyzer.writeLyrics(new File("lyric.txt"));
        System.out.println("------------------------------");
        System.out.println("most frequent word: " + analyzer.mostFrequentWord());
    }
    private HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public void read(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        int count = 1;
        while (line != null){
            String[] words = line.split(" ");
            for(int i = 0; i < words.length; i++){
                if(i == words.length - 1){
                    add(words[i].toUpperCase(), -count);
                }else{
                    add(words[i].toUpperCase(), count);
                }
                count++;
            }
            line = br.readLine();
        }
        br.close();
    }

    private void add(String lyricWord, int wordPosition){
        if(!map.containsKey(lyricWord)){
            map.put(lyricWord,new ArrayList<>());
        }
        map.get(lyricWord).add(wordPosition);
    }

    public void displayWords(){
        List<String> words = new ArrayList<>(map.keySet());
        Collections.sort(words);
        for(String word : words){
            System.out.print(word + ": ");
            boolean first = true;
            for(int i : map.get(word)){
                if(first){
                    System.out.print(i);
                    first = false;
                }else {
                    System.out.print(", " + i);
                }
            }
            System.out.println();
        }
    }

    public void writeLyrics(File file) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        String[] lyric = new String[count() + 1];
        lyric[0] = "";
        Set<String> set = map.keySet();
        for(String word : set){
            for(int i : map.get(word)){
                if(i < 0) {
                    lyric[-i] = word + "\n";
                }else{
                    lyric[i] = word + " ";
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for(String s : lyric) {
            pw.print(s);
        }
        pw.close();
    }

    /**
     * ???????
     * @return return the total number of unique words in the lyric by analyzing the map.
     */
    public int count(){
        int count = 0;
        for(List<Integer> list : map.values()){
            count += list.size();
        }
        return count;
    }

    public String mostFrequentWord(){
        List<String> words = new ArrayList<>(map.keySet());
        Collections.sort(words);
        String frequent = "";
        int max = 0;
        for(String word : words){
            if(map.get(word).size() > max){
                max = map.get(word).size();
                frequent = word;
            }
        }
        return frequent;
    }
}
