using BerthelotToolbox.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace BerthelotToolbox.Controllers
{
    public class BudgetController : ApiController
    {
        // GET: api/Budget
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }



        [HttpGet]
        [Route("api/Budget/NombreBudget")]
        public int NombreBudget()
        {
            return Budget.Expenses.Count;
        }
        // GET: api/Budget/5
        public string Get(int id)
        {
            if(id > Budget.Expenses.Count -1)
            {
                return "null";
            }
            else
            {
                return JsonConvert.SerializeObject(Budget.get(id));
            }
        }

        // POST: api/Budget
        // la valeur est censée être formatée comme ceci : nomDepense|sommeAAjouter
        public void Post([FromBody]Expense e)
        {
            var tmp = "";


            RessourcesManager.RessourcesManager.addToXML(e.Name, e.TotalEnCours);
        }

        // PUT: api/Budget/5
        public void Put(int id, [FromUri]string value)
        {
        }

        // DELETE: api/Budget/5
        public void Delete(int id)
        {
        }
    }
}
