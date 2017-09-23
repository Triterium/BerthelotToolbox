using BerthelotToolbox.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Xml;

namespace BerthelotToolbox.RessourcesManager
{
    public class RessourcesManager
    {
        private static void updateXML()
        {
            XmlDocument doc = new XmlDocument();
            doc.Load(Path.Combine(HttpRuntime.AppDomainAppPath, "RessourcesManager/Budget.xml"));

            XmlDocument docFile = new XmlDocument();
            docFile.Load(Path.Combine(HttpRuntime.AppDomainAppPath, "RessourcesManager/BudgetFile.xml"));


            foreach (XmlNode node in doc.DocumentElement.ChildNodes)
            {
                bool estPresent = false;
                foreach (XmlNode nodeFile in docFile.DocumentElement.ChildNodes)
                {
                    if (nodeFile.SelectSingleNode("Nom").InnerText == node.SelectSingleNode("Nom").InnerText)
                    {
                        estPresent = true;
                        break;
                    }
                }
                if(!estPresent)
                {
                    var nodeTmp = node.Clone();
                    nodeTmp.AppendChild(doc.CreateNode("text", "TotalEnCours", ""));
                    docFile.DocumentElement.AppendChild(nodeTmp);
                }
            }
            docFile.Save(Path.Combine(HttpRuntime.AppDomainAppPath, "RessourcesManager/BudgetFile.xml"));
        }
        public static List<Expense> LoadXML()
        {
            var list = new List<Expense>();
            updateXML();
            XmlDocument doc = new XmlDocument();
            doc.Load(Path.Combine(HttpRuntime.AppDomainAppPath ,  "RessourcesManager/BudgetFile.xml"));
            foreach(XmlNode node in doc.DocumentElement.ChildNodes)
            {
                Expense e = new Expense();
                e.Name = node.FirstChild.InnerText;
                e.BudgetInitial = Convert.ToDouble(node.SelectSingleNode("BudgetInitial").InnerText);
                e.TotalEnCours = Convert.ToDouble(node.SelectSingleNode("BudgetInitial").InnerText);
                e.TotalEnCours = Convert.ToDouble(node.SelectSingleNode("TotalEnCours").InnerText);
                list.Add(e);
            }
            return list;
        }

        public static void addToXML(string name, double somme)
        {
            try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(Path.Combine(HttpRuntime.AppDomainAppPath, "RessourcesManager/BudgetFile.xml"));
                // doc.DocumentElement.ChildNodes.SelectSingleNode(name);
                foreach (XmlNode node in doc.DocumentElement.ChildNodes)
                {
                    if (node.SelectSingleNode("Nom").InnerText == name)
                    {
                        // on est dans le bon noeud
                        node.SelectSingleNode("TotalEnCours").InnerText = (Convert.ToDouble(node.SelectSingleNode("TotalEnCours").InnerText) + somme).ToString();
                        break;
                    }
                }
                doc.Save(Path.Combine(HttpRuntime.AppDomainAppPath, "RessourcesManager/BudgetFile.xml"));
            }
            catch(Exception e)
            {

            }
           
        }
    }
}