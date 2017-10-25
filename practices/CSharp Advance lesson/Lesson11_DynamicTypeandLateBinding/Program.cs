using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IronPython;
using IronPython.Hosting;
using System.Dynamic;

namespace Lesson11_DynamicTypeandLateBinding
{
    class Program
    {
        static void Main(string[] args)
        {
            dynamic variable = "ok";
            //dynamic types can change types...
            // use dynamic less because it will make your code more complicated 
            Console.WriteLine("Types: {0} Value: {1}",variable.GetType(),variable);
            variable = 123;
            Console.WriteLine("Types: {0} Value: {1}", variable.GetType(), variable);

            // var types is immutable types

            //RUN PYTHON FILE
            var p = Python.CreateRuntime();
            // useful for complex stuff but less checking since there is no intellisense 
            dynamic pythonfile = p.ExecuteFile("Test.py");
            pythonfile.sayHi();

            // another usage ExpandoObject bypass everything
            dynamic stuff = new ExpandoObject();
            // bypass like use properties that hasn't not been made or created useful in ASP
            stuff.Name = "Rui";
            stuff.Age = 25;
            Console.WriteLine("Name: {0} Age: {1}",stuff.Name, stuff.Age);
        }
    }
}
