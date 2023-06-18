import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        folders();
        writePhrasesToFile();
        checkingFolders("src/resources");
    }
    private static void folders() {
        for (int i = 1; i <= 8; i++) {
            String folderName = "src/resources/folder" + i;
            Path folderPath = Paths.get(folderName);
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                System.out.println("Ошибка"+ e.getMessage());
            }
        }
    }
    private static void writePhrasesToFile() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 2; i <= 8; i += 2) {
            String folderName = "src/resources/folder" + i;
            File folder = new File(folderName);
            if (folder.exists()) {
                System.out.print("Введите имя файла " + i + ": ");
                String fileName = scanner.nextLine();
                System.out.print("Введите фразу в файле " + i + ": ");
                String phrase = scanner.nextLine();
                try{
                    String filePath = folderName + "/" + fileName;
                    Files.write(Paths.get(filePath), phrase.getBytes());
                }catch (IOException e){
                    System.out.println("Ошибка"+ e.getMessage());
                }
            }
        }
        scanner.close();
    }
    private static void checkingFolders(String directoryPath){
        File directory = new File(directoryPath);
        if(directory.isDirectory()){
            System.out.println("Список файлов в папке " + directory.getName() + ":");
            File[] files = directory.listFiles();
            if(files != null){
                for(File file : files){
                    if(file.isDirectory()){
                        checkingFolders(file.getPath());
                    }else {
                        System.out.println("Название файла: " + file.getName());
                        try {
                            Scanner fileScanner = new Scanner(file);
                            StringBuffer content = new StringBuffer();
                            while (fileScanner.hasNextLine()) {
                                content.append(fileScanner.nextLine());
                                if (fileScanner.hasNextLine()) {
                                    content.append("\n");
                                }
                            }
                            System.out.println("Содержимое файла " + file.getName() + ":");
                            System.out.println(content.toString());
                            fileScanner.close();
                        } catch (IOException e) {
                            System.out.println("Ошибка" + file.getName() + ":" + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
