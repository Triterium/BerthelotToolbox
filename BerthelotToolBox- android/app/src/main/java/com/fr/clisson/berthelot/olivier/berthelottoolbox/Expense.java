package com.fr.clisson.berthelot.olivier.berthelottoolbox;

/**
 * Created by olivier on 13/05/2017.
 */

public class Expense {


    private double BudgetInitial;
    private double TotalEnCours;

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Expense(String name) {
        this.BudgetInitial = 0;
        this.TotalEnCours = 0;
        this.Name = name;
    }

    public Expense(String name, double budgetInitial, double totalEnCours) {
        this.BudgetInitial = budgetInitial;
        this.TotalEnCours = totalEnCours;
        this.Name = name;
    }

    public Expense() {
        this.Name = "";
        this.BudgetInitial = 0;
        this.TotalEnCours = 0;
    }

    public double getTotal() {
        return TotalEnCours;
    }

    public double getInital() {
        return BudgetInitial;
    }

    public String addToTotalREST(double somme) {
        TotalEnCours += somme;
        String s =  RestManager.POST("http://berthelottoolbox.azurewebsites.net/api/budget", this.getName(), somme);
        return s;
    }

    public void addToTotal(double somme) {
        TotalEnCours += somme;
    }
    public String getTotalInitial() {
        return TotalEnCours + "/" + BudgetInitial + "€";
    }

    @Override
    public String toString()
    {
        return this.getName() + ", budget initial : " + getInital() + ", budget en cours : " + getTotal();
    }
}
