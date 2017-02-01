package at.htl.photoconsole;


import com.drew.imaging.ImageProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oscaryim on 23.11.16.
 */
public class Main {
    public static void main(String[] args) throws IOException, ImageProcessingException {
        System.out.println(" _____ _       _       _____                 _     ");
        System.out.println("|  _  | |_ ___| |_ ___|     |___ ___ ___ ___| |___ ");
        System.out.println("|   __|   | . |  _| . |   --| . |   |_ -| . | | -_|");
        System.out.println("|__|  |_|_|___|_| |___|_____|___|_|_|___|___|_|___|\n");


        /*Path currentRelativePath = Paths.get("");
        String acPath = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current path is: " + acPath);*/

        String located;
        SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Type in the path where the photos are located: ");
        located = br.readLine();

        File location = new File(located);
        List<File> fileList = new LinkedList<>();
        fileList.add(location);

        File[] files = location.listFiles();
            for (File fl : files) {
                if (!fl.isDirectory()) {
                    String path = String.valueOf(fl.getPath());
                    if((path.toUpperCase().contains(".JPG"))||(path.toUpperCase().contains(".PNG"))||(path.toUpperCase().contains(".JPEG"))){
                        System.out.println("It's an image! ");
                        fileList.add(fl);
                    }
                    else
                        System.out.println("It's NOT an image! ");
                }
            }

        for(int i = 0; i <= fileList.size()-1; i++) {
            String modifiedDate = sDF.format(fileList.get(i).lastModified());

            File temp = new File(modifiedDate);
            if (!temp.exists()) {
                if (temp.mkdir()) {
                    System.out.println("\nDirectory is created! ");
                } else {
                    System.out.println("Failed to create directory! ");
                }
            }

            for (int x = 0; i <= fileList.size() - 1; x++) {
                if (modifiedDate.equals(temp.getName())) {
                    try {
                        if (fileList.get(x).renameTo(new File(temp, fileList.get(x).getName()))) {
                            System.out.println("File is moved successful!");
                        } else {
                            System.out.println("File is failed to move!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //region Comments

        /*String folderName;
        String year;
        String month;
        String day;
        String located;
        String newPath = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Type in the year: ");
        year = br.readLine();

        System.out.print("Type in the month: ");
        month = br.readLine();

        System.out.print("Type in the day: ");
        day = br.readLine();

        folderName = year + "-" + month + "-" + day;


        File file = new File(folderName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("\nDirectory is created! ");
            } else {
                System.out.println("Failed to create directory! ");
            }
        }

        newPath = file.getAbsolutePath();

        System.out.print("Type in the path where the photos are located: ");
        located = br.readLine();


        File location = new File(located);
        List<File> fileList = new LinkedList<>();
        File[] files = location.listFiles();
        for (File fl : files) {
            if (!fl.isDirectory()) {
                String path = String.valueOf(fl.getPath());
                if((path.toUpperCase().contains(".JPG"))||(path.toUpperCase().contains(".PNG"))||(path.toUpperCase().contains(".JPEG"))){
                    System.out.println("It's an image! ");
                    fileList.add(fl);
                }
                else
                    System.out.println("It's NOT an image! ");
            }
        }

        SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");

        for(int i = 0; i <= fileList.size()-1; i++){
            String modifiedDate = sDF.format(fileList.get(i).lastModified());


            if(modifiedDate.equals(folderName)){
                try {
                    if(fileList.get(i).renameTo(new File(newPath, fileList.get(i).getName()))) {
                        System.out.println("File is moved successful!");
                    } else {
                        System.out.println("File is failed to move!");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }*/
        //endregion

    }
}