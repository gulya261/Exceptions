import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class AccessLogParser {

    public static void main(String[] args) {
        String path = "C:\\Users\\gulya\\Documents\\JAVA_t1\\Exceptions\\src\\access.log";

        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Указанный путь не существует или не является файлом.");
            return;
        }

        int lineCount = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                int length = line.length();

                if (length > 1024) {
                    throw new LineTooLongException("Строка превышает 1024 символа: " + length);
                }
                if (length > maxLength) {
                    maxLength = length;
                }
                if (length < minLength) {
                    minLength = length;
                }
            }
        } catch (LineTooLongException e) {
            System.err.println(e.getMessage());
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Общее количество строк: " + lineCount);
        System.out.println("Длина самой длинной строки: " + maxLength);
        System.out.println("Длина самой короткой строки: " + (minLength == Integer.MAX_VALUE ? 0 : minLength));
    }
}

