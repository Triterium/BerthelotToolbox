package com.fr.clisson.berthelot.olivier.berthelottoolbox;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetMain extends AppCompatActivity {

    public static String dataDirectory = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("info", getApplicationInfo().dataDir);
        dataDirectory = getApplicationInfo().dataDir;

        XMLHelper.CreateXML("test.xml");
        Log.d("info", FileHelper.ReadAllLines("test.xml"));
        setContentView(R.layout.activity_budget_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Expenses.getExpenses());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        TextView alimentation = (TextView) findViewById(R.id.TextviewSommeAlimentation);
        TextView sante = (TextView) findViewById(R.id.TextviewSommeSante);
        TextView loisirs = (TextView) findViewById(R.id.TextviewSommeLoisirs);
        TextView essence = (TextView) findViewById(R.id.TextviewSommeEssence);

        alimentation.setText(Expenses.getExpense("Alimentation").getTotalInitial());
        sante.setText(Expenses.getExpense("Santé").getTotalInitial());
        loisirs.setText(Expenses.getExpense("Loisirs").getTotalInitial());
        essence.setText(Expenses.getExpense("Essence").getTotalInitial());
        updateBackgroundColor();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void ajouterDepenses(View v)
    {
        TextView alimentation = (TextView) findViewById(R.id.TextviewSommeAlimentation);
        TextView sante = (TextView) findViewById(R.id.TextviewSommeSante);
        TextView loisirs = (TextView) findViewById(R.id.TextviewSommeLoisirs);
        TextView essence = (TextView) findViewById(R.id.TextviewSommeEssence);

        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        EditText sommeAEnregistrer = (EditText)findViewById(R.id.editText);

        if(sommeAEnregistrer.getText().toString() == null)    return;
        if(sommeAEnregistrer.getText().toString().equals(""))     return;
        String selected = sItems.getSelectedItem().toString();

        Toast.makeText(this, Expenses.getExpense(selected).addToTotalREST(Double.parseDouble(sommeAEnregistrer.getText().toString())), Toast.LENGTH_LONG );
        String totalInitial = Expenses.getExpense(selected).getTotalInitial();

        if (selected.equals("Alimentation")) {
            alimentation.setText(totalInitial);
        }

        if (selected.equals("Santé")) {
            sante.setText(totalInitial);
        }

        if (selected.equals("Loisirs")) {
            loisirs.setText(totalInitial);
        }

        if (selected.equals("Essence")) {
            essence.setText(totalInitial);
        }
        updateBackgroundColor();
    }

    private void updateBackgroundColor()
    {
        TextView alimentation = (TextView) findViewById(R.id.TextviewSommeAlimentation);
        TextView sante = (TextView) findViewById(R.id.TextviewSommeSante);
        TextView loisirs = (TextView) findViewById(R.id.TextviewSommeLoisirs);
        TextView essence = (TextView) findViewById(R.id.TextviewSommeEssence);

        double budget, enCours;
        budget = Expenses.getExpense("Alimentation").getInital();
        enCours = Expenses.getExpense("Alimentation").getTotal();
        if(enCours/budget > 1){
            alimentation.setBackgroundColor(Color.rgb(255, 0, 0));
        }
        else if (enCours/budget > 0.8){
            alimentation.setBackgroundColor(Color.rgb(250, 137, 0));
        }
        else
        {
            alimentation.setBackgroundColor(Color.rgb(0, 255, 0));
        }

        budget = Expenses.getExpense("Santé").getInital();
        enCours = Expenses.getExpense("Santé").getTotal();
        if(enCours / budget > 1)
        {
            sante.setBackgroundColor(Color.rgb(255, 0, 0));
        }
        else if(enCours/budget > 0.8){
            sante.setBackgroundColor(Color.rgb(250, 137, 0));
        }
        else
        {
            sante.setBackgroundColor(Color.rgb(0, 255, 0));
        }

        budget = Expenses.getExpense("Loisirs").getInital();
        enCours = Expenses.getExpense("Loisirs").getTotal();
        if(enCours / budget > 1)
        {
            loisirs.setBackgroundColor(Color.rgb(255, 0, 0));
        }
        else if(enCours/budget > 0.8){
            loisirs.setBackgroundColor(Color.rgb(250, 137, 0));
        }
        else
        {
            loisirs.setBackgroundColor(Color.rgb(0, 255, 0));
        }
        budget = Expenses.getExpense("Essence").getInital();
        enCours = Expenses.getExpense("Essence").getTotal();
        if(enCours / budget > 1)
        {
            essence.setBackgroundColor(Color.rgb(255, 0, 0));
        }
        else if(enCours/budget > 0.8){
            essence.setBackgroundColor(Color.rgb(250, 137, 0));
        }
        else
        {
            essence.setBackgroundColor(Color.rgb(0, 255, 0));
        }


    }

    public void GetFromServeur(View v)
    {
        Toast.makeText(this, RestManager.GET("http://berthelottoolbox.azurewebsites.net/api/budget/0"),
                Toast.LENGTH_LONG).show();
    }

    public void Synchroniser(View v)
    {
        TextView alimentation = (TextView) findViewById(R.id.TextviewSommeAlimentation);
        TextView sante = (TextView) findViewById(R.id.TextviewSommeSante);
        TextView loisirs = (TextView) findViewById(R.id.TextviewSommeLoisirs);
        TextView essence = (TextView) findViewById(R.id.TextviewSommeEssence);

        alimentation.setText(Expenses.getExpense("Alimentation").getTotalInitial());
        sante.setText(Expenses.getExpense("Santé").getTotalInitial());
        loisirs.setText(Expenses.getExpense("Loisirs").getTotalInitial());
        essence.setText(Expenses.getExpense("Essence").getTotalInitial());
    }

}
