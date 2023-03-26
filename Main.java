import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinaryTree<String> englishSpanishTree = new BinaryTree<>();
        BinaryTree<String> frenchEnglishTree = new BinaryTree<>();
        
        // Leer el archivo "diccionario.txt" y construir los árboles binarios de búsqueda
        try {
            Scanner scanner = new Scanner(new File("diccionario.txt"));
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                String english = tokens[0].trim().toLowerCase();
                String spanish = tokens[1].trim().toLowerCase();
                String french = tokens[2].trim().toLowerCase();

                englishSpanishTree.insert(new Association<>(english, spanish, french));
                frenchEnglishTree.insert(new Association<>(french, english, spanish));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: diccionario.txt no encontrado");
            return;
        }
    
        // Leer el archivo "texto.txt" y traducir cada palabra
        try {
            Scanner scanner = new Scanner(new File("texto.txt"));
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                String translation = translate(word, englishSpanishTree, frenchEnglishTree);
                System.out.print(translation + " ");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: texto.txt no encontrado");
            return;
        }
    }
    
    private static String translate(String word, BinaryTree<String> englishSpanishTree, BinaryTree<String> frenchEnglishTree) {
        if (englishSpanishTree.contains(new Association<>(word, null, null))) {
            Association<String, String> association = (Association<String, String>) englishSpanishTree.find(new Association<>(word, null, null));
            return association.getValue1();
        } else if (frenchEnglishTree.contains(new Association<>(word, null, null))) {
            Association<String, String> association = (Association<String, String>) frenchEnglishTree.find(new Association<>(word, null, null));
            return association.getValue2();
        } else {
            return "*" + word + "*";
        }
    }
}
