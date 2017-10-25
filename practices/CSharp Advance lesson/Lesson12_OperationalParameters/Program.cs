using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson12_OperationalParameters
{
    class Program
    {
        static void Main(string[] args)
        {
            printSomething();
            printSomething("Rui", "Rosillas");
            printSomething(age: 25);
            printSomething(Lname: "Rosillas", age: 25);

        }
        static void printSomething(string Fname = null, string Lname=null, int age=0) {
            Console.WriteLine("Name: {0} {1} age is {2}",Fname,Lname,age);
        }
    }
}
