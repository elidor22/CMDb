package GUI;


import Utilities.fileUploader;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileChooser {
    JFileChooser chooser = new JFileChooser();
    public  void choose() {
        fileUploader upload = new fileUploader();
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            upload.upload(selectedFile.getAbsolutePath(), selectedFile.getName());
        }



    }
}
