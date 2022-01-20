package com.zavyalov;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String GAMES_FOLDERS = "src, res, savegames, temp";
    private static final String GAMES_DIRECTORY = "D://Games";
    private static final String SRC_FOLDERS = "main, test";
    private static final String SRC_DIRECTORY = GAMES_DIRECTORY + "/src";
    private static final String RES_FOLDERS = "drawables, vectors, icons";
    private static final String RES_DIRECTORY = GAMES_DIRECTORY + "/res";
    private static final String MAIN_FILES = "Main.java, Utils.java";
    private static final String MAIN_DIRECTORY = SRC_DIRECTORY + "/main";
    private static final String TEMP_FILES = "temp.txt";
    private static final String TEMP_DIRECTORY = GAMES_DIRECTORY + "/temp";
    private static List<String> log = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        createNewFolders(GAMES_DIRECTORY, GAMES_FOLDERS);
        createNewFolders(SRC_DIRECTORY, SRC_FOLDERS);
        createNewFolders(RES_DIRECTORY, RES_FOLDERS);
        createNewFiles(MAIN_DIRECTORY, MAIN_FILES);
        createNewFiles(TEMP_DIRECTORY, TEMP_FILES);
        writeFile(TEMP_DIRECTORY + "/temp.txt", log);
    }

    private static void createNewFiles(String directory, String fileNamesList) throws IOException {
        String[] fileNames = fileNamesList.split(", ");
        for (String fileName : fileNames) {
            try {
                File myFile = new File(directory + "/" + fileName);
                if (myFile.createNewFile() == true) {
                    setupInfo("Файл создан:  " + dateAndTime() + "  " + directory + "/" + fileName);
                } else {
                    setupInfo("!!! Файл не был создан создан:  " + dateAndTime() + "  " + directory + "/" + fileName);
                }
            } catch (IOException ex) {
                setupInfo("!!! " + ex.getMessage() + ":  " + dateAndTime() + "  " + directory + "/" + fileName);
            }
        }
    }

    private static void createNewFolders(String directory, String folderNameList) {
        String[] folderNames = folderNameList.split(", ");
        for (String folderName : folderNames) {
            File dir = new File(directory, folderName);
            if (dir.mkdir()) {
                setupInfo("Папка создана:  " + dateAndTime() + "  " + directory + "/" + folderName);
            } else {
                setupInfo("!!! Папка не была создана:  " + dateAndTime() + "  " + directory + "/" + folderName);
            }
        }
    }

    private static String dateAndTime() {
        return ("Дата: " + LocalDateTime.now().getYear() + "/" + LocalDateTime.now().getMonthValue() + "/"
                + LocalDateTime.now().getDayOfMonth() + "    Время: " + LocalDateTime.now().getHour() + ":"
                + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond() + ":"
                + LocalDateTime.now().getNano() + "    Директория:");
    }

    private static void writeFile(String directory, List<String> data) throws IOException {
        try (FileWriter writer = new FileWriter(directory, true)) {
            for (String text : data) {
                writer.write(text);
                writer.write("\n");
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println("!!! " + ex.getMessage() + ":  " + dateAndTime() + "  " + directory);
        }
    }

    private static void setupInfo(String message) {
        System.out.println(message);
        log.add(message);
    }
}
