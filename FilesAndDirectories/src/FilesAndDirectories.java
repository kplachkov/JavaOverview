import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FilesAndDirectories {

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Streams
        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
             FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            int oneByte = fileInputStream.read();
            while (oneByte >= 0) {
                fileOutputStream.write(oneByte);
                oneByte = fileInputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(new FileInputStream(inputFile));
            PrintWriter printWriter = new PrintWriter(outputFile)) {
            while (scanner.hasNext()) {
                printWriter.println(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Files and path
        Path pathIn = Paths.get(inputFile);
        Path pathOut = Paths.get(outputFile);
        try {
            List<String> lines = Files.readAllLines(pathIn);
            Collections.sort(lines);
            Files.write(pathOut, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(inputFile);  // File

        // Serialization
        String pathToSerFile = "object.ser";
        Cube cube = new Cube();
        cube.color = "black";
        cube.depth = 10;
        cube.height = 12;
        cube.width = 11;

		// Write
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(pathToSerFile))) {
            objectOutputStream.writeObject(cube);
        } catch (IOException e) {
            e.printStackTrace();
        }

		// Read
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(pathToSerFile))) {
            Cube loadedCube = (Cube) objectInputStream.readObject();
            System.out.println(loadedCube.color);
            System.out.println(loadedCube.width);
            System.out.println(loadedCube.height);
            System.out.println(loadedCube.depth);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
