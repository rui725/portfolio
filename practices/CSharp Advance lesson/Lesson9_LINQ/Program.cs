using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson9_LINQ
{
    class Program
    {
        static void Main(string[] args)
        {
            // LINQ = Language INtegrated Query
            // works with Collections

            //sample
            var sample = "I enjoy writing uber-software in C#";

            // query
            var results = from c in sample.ToLower()
                          where c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                          group c by c;
            var results2 = from c in sample.ToLower()
                          where c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                          orderby c 
                          select c;

            foreach (var item in results)
            {
                Console.WriteLine("{0} - {1}", item.Key, item.Count());
            }

            foreach (var item in results2)
            {
                Console.WriteLine(item);
            }


            //other use
            var persons = new List<Person>
            {
                new Person{ Fname="Rui", Lname="Rosillas",age = 25},
                new Person{ Fname="Judy", Lname="Gonzalez", age =21},
                new Person{ Fname="Christine Joy", Lname="Llave", age=21}

            };
            // using select
            var res = from p in persons
                      where p.age < 25
                      orderby p.Fname
                      select p;

            foreach (var item in res)
            {
                Console.WriteLine("Name: {0} {1} your age is {2}",item.Fname, item.Lname,item.age);
            }

            // using group by
            var res2 = from p in persons
                       where p.age < 25
                       orderby p.Fname
                       group p by p.age;

            foreach (var item2 in res2)
            {
                Console.WriteLine(item2.Key);
                foreach (var item in item2)
                {
                    Console.WriteLine("Name: {0} {1} your age is {2}", item.Fname, item.Lname, item.age);
                }
            }
        }
    }

    public class Person {
        public string Fname { get; set; }
        public string Lname { get; set; }
        public int age { get; set; }
    }
}
