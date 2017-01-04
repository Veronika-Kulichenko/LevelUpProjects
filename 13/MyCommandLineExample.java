package com.company.levelUP.homeWork.homeWork13;

import java.io.*;

/**
 * Created by java on 22.06.2016.
 */
public class MyCommandLineExample {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private static String defaultPath = "src\\com\\company\\levelup\\";

    public static void runCMD() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String path = defaultPath;
        System.out.print(path + ">");
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("exit")) break;

                if (line.contains("ls")) {
                    printAllFilesAndDirectories(path);
                } else if (line.contains("cd")) {
                    String pathName = line.replace("cd", "").trim();
                    if (pathName.contains("..")) {
                        File file = new File(path);
                        path = file.getParentFile().getPath();
                    } else {
                        File[] allDirectories = getAllFilesOrDirectoriesByFilter(path, new DirectoryFilter(pathName));
                        if (allDirectories.length == 1) {
                            path += "\\" + allDirectories[0].getName();
                        } else if (isFileWithEqualsNameExist(allDirectories, pathName)) {
                            path += "\\" + pathName;
                        } else {
                            printAllFilesOrDirectories(allDirectories);
                        }
                    }
                } else if (line.contains("mkDir")) {
                    String pathToNewFileOrDirectory = path + "\\" + line.replace("mkDir", "").trim();
                    File file = new File(pathToNewFileOrDirectory);
                    if (pathToNewFileOrDirectory.contains(".")) {
                        file.createNewFile();
                    } else {
                        file.mkdir();
                    }
                } else if (line.contains("open")) {
                    String fileName = line.replace("open", "").trim();
                    if(fileName.contains(".txt")){
                        File file = new File(path + "\\" + fileName);
                        openTextFile(file);
                    } else {
                        System.out.println("Couldn't open file with such extension: " + fileName.substring(fileName.indexOf("."), fileName.length()) + ". Allowed only txt extension.");
                    }
                }

                System.out.print(path + ">");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printAllFilesOrDirectories(File[] allDirectories) {
        for (File f : allDirectories) {
            String name = (f.isDirectory()) ? getDirectory(f.getName()) : getFile(f.getName());
            System.out.println(name);
        }
    }

    private static boolean isFileWithEqualsNameExist(File[] allDirectories, String pathName) {
        for (File f : allDirectories) {
            if (f.getName().equals(pathName)) {
                return true;
            }
        }
        return false;
    }

    private static void printAllFilesAndDirectories(String path) {
        File file = new File(path);
        for (File f : file.listFiles()) {
            String name = (f.isDirectory()) ? getDirectory(f.getName()) : getFile(f.getName());
            System.out.println(name);
        }
    }

    private static String getDirectory(String name) {
        return ANSI_GREEN + "(d) " + name + ANSI_RESET;
    }

    private static String getFile(String name) {
        return ANSI_BLACK + "(f) " + name + ANSI_RESET;
    }

    private static void openTextFile(File file) {
        String text = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            System.out.println(file.getName() + " contains text: \r\n");
            while ((text = reader.readLine()) != null){
                System.out.println(text);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File[] getAllFilesOrDirectoriesByFilter(String path, FileFilter filter) {
        File file = new File(path);
        return file.listFiles(filter);
    }
}
