package com.company.levelUP.homeWork.homeWork13;

import java.io.File;
import java.io.FileFilter;

public class DirectoryFilter implements FileFilter {
    
    private String directoryName = "";

    public DirectoryFilter(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() && containsIgnoreCase(pathname, directoryName);
    }

    private boolean containsIgnoreCase(File pathname, String directoryName) {
        return pathname.getName().toLowerCase().contains(directoryName.toLowerCase());
    }
}
