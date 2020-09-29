package com.company;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;


class WorkingWithFiles {

     public File create() {
         File newFile = new File("C:\\Users\\lizzn\\IdeaProjects\\test\\words.txt"); //creating new file
         try
             {
                 boolean created = newFile.createNewFile();
                 if(created)
                     System.out.println("File has been created");
             }
             catch(IOException ex){
                 System.out.println(ex.getMessage());
             }
         return newFile;
     }

     public  List<String> read(File newFile) throws IOException {
        //Scanner input = new Scanner(new File ("words.txt"));
        //FileReader read = new FileReader("words.txt");
        List<String> words = Files.readAllLines(Paths.get("words.txt"), StandardCharsets.UTF_8); //считка
        System.out.println(words);
        words.size();
        return words;
    }
}

class Formatting {

    public ArrayList<String> filter(List<String> lines) {
        ArrayList<String> all_words = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++) {
            String[] words = lines.get(i).split("[^a-zA-ZА-Яа-яЮюЄєІіЇї]", 0);
            for (int j = 0; j < words.length; j++) {
                if (words[j] != "") {
                    all_words.add(words[j].toLowerCase());
                }
            }
        }
        System.out.println(all_words);
        return all_words;
    }

    public ArrayList<String> correct_length(ArrayList<String> all_words) {

        for (int i = 0; i < all_words.size(); i++) {
            if (all_words.get(i).length() > 30) {
                all_words.set(i, all_words.get(i).substring(0, 29));
                // System.out.println(all_words.get(i));
            }
        }
        return all_words;
    }

    public ArrayList<String> sort(ArrayList<String> all_words){
        all_words.sort((Comparator<String>) (o1, o2) -> {
            int x = Integer.compare(o1.length(), o2.length());
            if(x == 0){
                return o1.compareTo(o2);
            }
            else{
                return x;
            }
        }
        );

        return all_words;
    }

    public ArrayList<String> delete_dublicates(ArrayList<String> all_words){
        ArrayList<String> all_words_new = new ArrayList<String>();
        for (int i = 0; i < all_words.size() - 1; i++){
            while (all_words.get(i).equals(all_words.get(i + 1))){
                all_words.remove(i);
            }

            /*while (!all_words.get(i).equals(all_words.get(i + 1))){
                all_words_new.add(all_words.get(i));
            }*/
        }
        return all_words;
    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        WorkingWithFiles random_text = new WorkingWithFiles();
        Formatting formatting = new Formatting();

        List<String> lines = random_text.read(random_text.create());

        ArrayList<String> filteredLines = formatting.correct_length(formatting.filter(lines));
        filteredLines = formatting.delete_dublicates(formatting.sort(filteredLines));

        System.out.println(filteredLines);
    }
}
