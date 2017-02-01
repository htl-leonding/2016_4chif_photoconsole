package at.htl.photoconsole;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oscaryim on 23.11.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(" _____ _       _       _____                 _     ");
        System.out.println("|  _  | |_ ___| |_ ___|     |___ ___ ___ ___| |___ ");
        System.out.println("|   __|   | . |  _| . |   --| . |   |_ -| . | | -_|");
        System.out.println("|__|  |_|_|___|_| |___|_____|___|_|_|___|___|_|___|\n");


        Path currentRelativePath = Paths.get("");
        String acPath = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current path is: " + acPath);

        String located = acPath;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Type in the path where the photos are located: ");
        located = br.readLine();

        File location = new File(located);
        List<File> fileList = new LinkedList<>();
        File[] files = location.listFiles();
        for (File fl : files) {
            if (!fl.isDirectory()) {
                String path = String.valueOf(fl.getPath());
                if ((path.toUpperCase().contains(".JPG")) || (path.toUpperCase().contains(".PNG")) || (path.toUpperCase().contains(".JPEG"))) {
                    fileList.add(fl);
                }
            }
        }

        SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i <= fileList.size() - 1; i++) {
            String modifiedDate = sDF.format(fileList.get(i).lastModified());

            String newPath = fileList.get(i).getParent() + File.separator + modifiedDate;
            File file = new File(newPath);

            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created! ");
                } else {
                    System.out.println("Failed to create directory! ");
                }
            }

            if(modifiedDate.equals(file.getName())){
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

        }




    }

}



