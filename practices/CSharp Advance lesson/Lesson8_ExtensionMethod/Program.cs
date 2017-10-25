using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson8_ExtensionMethod
{
    class Program
    {
        static void Main(string[] args)
        {
            var p = new Person { name="Rui", age=25};
            var p2 = new Person { name = "Judy", age = 21 };
            // calls the method
            p.PrintMe(p2);
            p2.PrintMe(p);
        }
    }
    public static class Extensions {
        // this Person p = is actually calling the current class that is calling it
        public static void PrintMe(this Person p, Person p2) {
            Console.WriteLine("Your name is: {0} and the other name is {1}", p.name, p2.name);
        }
    }
}
