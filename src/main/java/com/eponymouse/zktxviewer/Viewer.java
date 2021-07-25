package com.eponymouse.zktxviewer;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class Viewer
{
	public static void main(String[] arg) throws Exception
    {
        AtomicReference<File> target = new AtomicReference<>();
	    if (arg.length == 0)
        {
            // Show a file open dialog
            SwingUtilities.invokeAndWait(() -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("KTX/ZKTX files", "ktx", "zktx"));
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    target.set(fileChooser.getSelectedFile());
                }
            });
        }
	    else
        {
            target.set(new File(arg[0]));
        }
	    
	    if (target.get() != null && target.get().exists())
        {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setWindowedMode(1200, 800);
            new Lwjgl3Application(new ViewerApp(target.get()), config);
        }
	}
}
