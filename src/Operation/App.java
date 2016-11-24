package Operation;
import Operation.Model.Text;

import java.io.*;
import java.util.*;

public class App {

    private Map<String, String> options;
    private BufferedReader mReader;

    public App(){
        mReader = new BufferedReader(new InputStreamReader(System.in));
        options = new HashMap<>();
        options.put("1", "Create New Text");
        options.put("2", "Load the text");
        options.put("3", "Exit");
    }


    private Text createNewText() throws IOException{
        System.out.print("Your text: ");
        String text = mReader.readLine();
        Text newText = new Text(text);
        return newText;
    }

    private String userOptions() throws IOException {
        System.out.println("Your options are: \n");
        for (Map.Entry<String, String> option : options.entrySet()) {
            System.out.println(option.getKey() + " - - " + option.getValue());
        }

        System.out.print("\nYour choice: ");
        String choice = mReader.readLine();
        return choice;
    }

    public void run() throws IOException{
        String choice = "";
        do{
            try{
                choice = userOptions();
                switch (choice){
                    case "1" :
                        Text text =  createNewText();
                        System.out.print("Title: ");
                        String name = mReader.readLine();
                        save(text, name);
                        break;
                    case "2" :
                        System.out.println("Your files:");
                        List<File> files = displayTexts();
                        for(File file : files){
                            System.out.println(files.indexOf(file) + " -- " + file.getName());
                        }
                        try {
                            System.out.print("Pick a number:  ");
                            int no = Integer.parseInt(mReader.readLine());
                            String filename = files.get(no).toString();
                            load(filename);
                        } catch(Exception ioe){
                            ioe.getMessage();
                        }
                        break;
                    case "3" :
                        System.out.println("Thank you !");
                        break;
                    default :
                        System.out.println("Your option is not recognised, please try again !");
                }
            }
            catch(IOException ioe){
                System.out.println("System cannot handle your options \n");
            }
        }
        while(!choice.equalsIgnoreCase("3"));
    }


    private static void save(Text text, String name) throws IOException {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("text/" + name + ".txt"));
        ){
            writer.write(String.valueOf(text.toString()));
            writer.close();
        }
        catch(IOException ioe){
            System.out.println("Error to write file \n");
        }
    }


    private static void load(String filename){
        try{
            FileReader reader = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(reader);
            String line;
            String paragraph = "";
            while((line = bfr.readLine()) != null){
                paragraph += line + "\n";
            }
            reader.close();
            System.out.println("\n" + paragraph);
        }
        catch(IOException ioe){
            System.out.println("Error, could not find your file");
        }
    }

    private static List<File> displayTexts(){
        File folder = new File("text");
        File[] files = folder.listFiles();
        List<File> texts = Arrays.asList(files);
        return texts;
    }

}
