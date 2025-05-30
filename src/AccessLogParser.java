<<<<<<< HEAD
=======

>>>>>>> 0805fd1 (Добавлено извлечение User-Agent и подсчет запросов от Googlebot и YandexBot)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class AccessLogParser {

    public static void main(String[] args) {
        String path = "C:\\Users\\gulya\\Documents\\JAVA_t1\\Exceptions\\src\\access.log";
<<<<<<< HEAD

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

=======
                File file = new File(path);
                if (!file.exists() || !file.isFile()) {
                    System.out.println("Указанный путь не существует или не является файлом.");
                    return;
                }

                int lineCount = 0;
                int googlebotCount = 0;
                int yandexbotCount = 0;

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lineCount++;
                        int length = line.length();

                        if (length > 1024) {
                            throw new LineTooLongException("Строка превышает 1024 символа: " + length);
                        }

                        String userAgent = extractUserAgent(line);
                        if (userAgent != null) {
                            if (userAgent.contains("Googlebot")) {
                                googlebotCount++;
                            } else if (userAgent.contains("YandexBot")) {
                                yandexbotCount++;
                            }
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
                System.out.printf("Доля запросов от Googlebot: %.2f%%\n", (googlebotCount / (double) lineCount) * 100);
                System.out.printf("Доля запросов от YandexBot: %.2f%%\n", (yandexbotCount / (double) lineCount) * 100);
            }

            private static String extractUserAgent(String line) {
                int startIndex = line.indexOf('[');
                int endIndex = line.indexOf(']');
                if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
                    String userAgentPart = line.substring(startIndex + 1, endIndex);
                    String[] parts = userAgentPart.split(";");
                    if (parts.length >= 2) {
                        String fragment = parts[1].trim();
                        return fragment.split("/")[0].trim();
                    }
                }
                return null;
            }
        }

>>>>>>> 0805fd1 (Добавлено извлечение User-Agent и подсчет запросов от Googlebot и YandexBot)
