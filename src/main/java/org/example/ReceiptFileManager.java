package org.example;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {

    public static void saveReceipt(Order order) {

        try {
            File folder = new File("receipts");

            if (!folder.exists()) {
                folder.mkdir();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String fileName = LocalDateTime.now().format(formatter) + ".txt";

            FileWriter writer = new FileWriter("receipts/" + fileName);

            writer.write(order.toString());

            writer.close();

            System.out.println("\nReceipt Saved!");
            System.out.println("Location: " + fileName);
        }
        catch (Exception e) {
            System.out.println("Error saving receipt.");
        }
    }
}
