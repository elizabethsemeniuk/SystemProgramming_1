package com.company;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Comparator;


class WorkingWithFiles {


     public File choice() {
         System.out.println("Please, enter path to your file");
         try
         {
             Scanner in = new Scanner(System.in);
             String path = in.nextLine();
             File newFile = new File(path); //opening new file

             // C:\\Users\\lizzn\\IdeaProjects\\system programming (lab 1)\\hello.txt

             boolean chosen = newFile.isFile();
             if(chosen)
                 System.out.println("File exists");
             else
                 throw new SecurityException("File doesn`t exist: " + path);

             return newFile;
         }
         catch(SecurityException ex){
             System.out.println(ex.getMessage());
         }
         catch (Exception ex){
            System.out.println("Wow - " + ex.getMessage());
         }
         return null;
     }

     public  List<String> read(File newFile) throws IOException {
         System.out.println("Original text:");
        if(newFile == null){
            return null;
        }
        String file_name = newFile.getName();
        List<String> words = Files.readAllLines(Paths.get(file_name), StandardCharsets.UTF_8); //считка//
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
        //System.out.println(all_words);
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
        }
        return all_words;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = null;
        WorkingWithFiles random_text = new WorkingWithFiles();
        Formatting formatting = new Formatting();
        while(lines == null){
            lines = random_text.read(random_text.choice());
        }

        ArrayList<String> filteredLines = formatting.correct_length(formatting.filter(lines));
        filteredLines = formatting.delete_dublicates(formatting.sort(filteredLines));

        System.out.println("Formatted and sorted by length:");
        System.out.println(filteredLines);
    }
}
