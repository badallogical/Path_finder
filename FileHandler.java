package source.util;



import source.*;

import java.io.*;
import javax.swing.*;
import source.*;
import source.Path;
import source.Path_finder;
import source.RoadMap;


import java.util.*;
import javax.swing.filechooser.FileSystemView;

public class FileHandler {

    private File my_file;

    public static String openFile(){
        JFileChooser open = new JFileChooser();
        int ifchoose = open.showOpenDialog(null);

        try {
            // if selection occurs
            if (ifchoose == JFileChooser.APPROVE_OPTION) {
                System.out.println(open.getSelectedFile().getAbsolutePath());

                // load the state of file
                File openFile = new File(open.getSelectedFile().getAbsolutePath());
                FileInputStream fis = new FileInputStream(openFile.getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);
                RoadMap readed = (RoadMap)ois.readObject();

                // update map
                Path_finder.map.reset();
                Path_finder.map.setNodes( readed.getNodes());
                Path_finder.map.setGraph( readed.getGraph());
                Path_finder.map.reload();

                // close
                ois.close();
                fis.close();
            }
        }
        catch(IOException|ClassNotFoundException e){
            System.err.println(e.toString());
        }


        return " ";
    }

    public static String saveFile(){

        try {
            JFileChooser save = new JFileChooser();
            int ifchoose = save.showSaveDialog(null);

            // if selection occurs
            if (ifchoose == JFileChooser.APPROVE_OPTION) {
                System.out.println(save.getSelectedFile().getAbsolutePath());

                // load the state of program
                File saveFile = new File(save.getSelectedFile().getAbsolutePath());

                FileOutputStream fos = new FileOutputStream(saveFile.getAbsolutePath());
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(Path_finder.map);

                oos.close();
                fos.close();
            }
        }
        catch(IOException e){
            System.err.println("exception " + e.toString());
        }

        return " ";
    }

}
