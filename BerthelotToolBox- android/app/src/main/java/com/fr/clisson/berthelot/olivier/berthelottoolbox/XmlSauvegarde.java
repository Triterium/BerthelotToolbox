package com.fr.clisson.berthelot.olivier.berthelottoolbox;
import android.app.Application;
import android.os.Environment;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by olivier on 19/08/2017.
 */

public class XmlSauvegarde {

    public static final String path = "/save.xml";
    public static void saveXML(String name, double prix) throws IOException {
        File file = new File(Environment.getDataDirectory().getPath() + path);
        if(!file.exists())
        {
            file.createNewFile();
            String s = "<?xml version='1.0' encoding='UTF-8'?>" +
                    "<expenses>";
            for(Expense expense : Expenses.getExpensesList())
            {
                s += "<expense>";
                s += "<Name>" + expense.getName() + "</Name>";
                s += "<ASauvegarder>" + "0" + "</ASauvegarder>";
                s += "</expense>";
            }


            s += "</expenses>";
            Files.write(s, file, Charset.defaultCharset());
        }


    }
}
