using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace BerthelotToolbox.Models
{
    public class Expense
    {
        public string Name { get; set; }

        public double BudgetInitial { get; set; }

        public double TotalEnCours { get; set; }
    }
}