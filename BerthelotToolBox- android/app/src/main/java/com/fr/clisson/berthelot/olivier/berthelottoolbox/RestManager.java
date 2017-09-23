package com.fr.clisson.berthelot.olivier.berthelottoolbox;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by olivier on 20/05/2017.
 */

public class RestManager {
    public static String GET(String urlSite){
        JSONAsyncTaskGet jsast = new JSONAsyncTaskGet(urlSite);
        String exception = "";
        try {
            return jsast.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            exception = e.toString();
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            exception = e.toString();
        }
        return "exception dans GET : " + exception;
    }

    public static String POST(String urlSite, String name, double somme){
        Expense e = new Expense();
        e.setName(name);
        e.addToTotal(somme);
        JSONAsyncTaskPost jsast = new JSONAsyncTaskPost(urlSite, e);
        String exception = "";
        try {
            return jsast.execute().get();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            exception = e.toString();
            ex.printStackTrace();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.toString();
        }
        return "exception dans GET : " + exception;
    }
}
