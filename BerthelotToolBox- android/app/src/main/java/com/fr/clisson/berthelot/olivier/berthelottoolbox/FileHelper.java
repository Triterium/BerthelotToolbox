package com.fr.clisson.berthelot.olivier.berthelottoolbox;

import com.google.common.io.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by olivier on 23/09/2017.
 */

public class FileHelper {

    public static void Write(String path, String data)
    {
        File f = new File(BudgetMain.dataDirectory + "/" +path);
        if (!f.exists())
        {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(f));
            outputStreamWriter.write(data);
            outputStreamWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String ReadAllLines(String path)
    {
        String result = "";
        try
        {
            File f = new File(BudgetMain.dataDirectory + "/" + path);
            List<String> lines = null;
            try {
                lines = Files.readLines(f, Charset.defaultCharset());
            } catch (IOException e) {
                result = e.toString();
            }

            for(String s : lines)
            {
               result += s + "\n";
            }
        }
        catch (Exception ex)
        {
            result = ex.toString();
        }

        return result;
    }
}
