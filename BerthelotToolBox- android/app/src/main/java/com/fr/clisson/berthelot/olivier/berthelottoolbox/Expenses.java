package com.fr.clisson.berthelot.olivier.berthelottoolbox;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by olivier on 13/05/2017.
 */

public class Expenses {


    public static List<Expense> expenses = null;



    public static List<String> getExpenses() {
        if(expenses == null)
        {
            InitializeExpenses();
        }
        List<String> ls = new ArrayList<>();
        for (Expense e : expenses) {
            ls.add(e.getName());
        }
        return ls;
    }

    public static List<Expense> getExpensesList() {
        if(expenses == null)
        {
            InitializeExpenses();
        }

        return expenses;
    }

    public static Expense getExpense(String name) {

            InitializeExpenses();

        for (Expense e : expenses) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return new Expense ("No expense of this name", 0, 0);
    }

    private static void InitializeExpenses()
    {
         expenses = new ArrayList<>();
        //Récupération du nombre de budget
        try {
            Gson gson=new GsonBuilder().create();
            String nombreBudget = RestManager.GET("http://berthelottoolbox.azurewebsites.net/api/budget/NombreBudget");
            int nombreBudgets = Integer.parseInt(nombreBudget);

            for(int i = 0; i< nombreBudgets; i++)
            {
                String s = RestManager.GET("http://berthelottoolbox.azurewebsites.net/api/budget/"+i);
                JSONObject jsonObject = new JSONObject(s);
                Expense e =gson.fromJson(jsonObject.toString(),Expense.class);
                e.setName(e.getName().replace(" ", ""));
                expenses.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
