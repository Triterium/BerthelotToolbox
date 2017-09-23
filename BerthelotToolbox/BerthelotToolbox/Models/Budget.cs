using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Web;

namespace BerthelotToolbox.Models
{
    public class Budget
    {
        public static List<Expense> Expenses { get; set; } = getExpenses() ?? new List<Expense>();

        public string Self
        {
            get
            {
                return string.Format(CultureInfo.CurrentCulture,
               "api/contacts/{0}", Expenses[0].Name);
            }
            set { }
        }

        private static List<Expense> getExpenses()
        {
            return RessourcesManager.RessourcesManager.LoadXML();
        }

        public static Expense get(int index )
        {
            Expenses = RessourcesManager.RessourcesManager.LoadXML();
            return Expenses[index];
        }

    }
}