package com.me.courses_selection.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * June
 */
public class Lexer {

    public static final List<Character> numbers=new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
    public static final List<String> keys=new ArrayList<>(Arrays.asList("if","else","for","while","do","return","break","continue"));
    public static final List<Character> borders=new ArrayList<>(Arrays.asList(',',';','{','}','(',')'));
    public static final List<Character> computes=new ArrayList<>(Arrays.asList('+','-','*','/','%','='));
    public static final List<String> relations=new ArrayList<>(Arrays.asList(">","<",">=","<=","==","<>"));

    public static final String filename="D:\\桌面\\text.txt";

    private static String readFile() throws IOException {
        String fileContent = "";
        String line;
        File file=new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"gbk");

        BufferedReader reader=new BufferedReader(read);
        if((line=reader.readLine())!=null){
            fileContent+=line;
        }
        read.close();
        return fileContent;
    }

    public static void main(String[] args) throws IOException {
        String key="";
//        Lexer lexer=new Lexer();
        String fileText=readFile();
        for(int i=0;i<fileText.length();i++){
            if(numbers.contains(fileText.charAt(i))) {
                if (key.length() != 0) {
                    System.out.println(key + "  key");
                }
                System.out.println(i + "numbers");
            }else if(borders.contains(fileText.charAt(i))){
                if(key.length()!=0){
                    System.out.println(key+"  key");
                }
                System.out.println(i+"borders");
            }else if(computes.contains(fileText.charAt(i))){
                if(key.length()!=0){
                    System.out.println(key+"  key");
                }
                System.out.println(i+"computes");
            }else if(relations.contains(fileText.charAt(i))){
                if(key.length()!=0){
                    System.out.println(key+"  key");
                }
                System.out.println(i+"relations");
            }else{
                key+=fileText.charAt(i);
                System.out.println(i + "keys");
            }

        }
        if(key.length()!=0){
            System.out.println(key+"  key");
        }
    }


}
