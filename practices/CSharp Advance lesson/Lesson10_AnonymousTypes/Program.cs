using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson10_AnonymousTypes
{
    class Program
    {
        static void Main(string[] args)
        {
            var persons = new List<Person>
            {
                new Person{ Fname="Rui", Lname="Rosillas",age = 25},
                new Person{ Fname="Judy", Lname="Gonzalez", age =21},
                new Person{ Fname="Christine Joy", Lname="Llave", age=21},
                new Person{ Fname="Mrs", Lname="Rosillas", age = 0}

            };

            //Anonymous Type Read only cannot be change or in other words immutable
            var result = from p in persons
                         where p.Lname == "Rosillas"
                         select new { FName = p.Fname, LName = p.Lname };
            foreach (var item in result)
            {
                Console.WriteLine("Name: {0} {1}", item.FName,item.LName);
            }
        }
    }
    public class Person {
        public string Fname { get; set; }
        public string Lname { get; set; }
        public int  age { get; set; }
    }
}
