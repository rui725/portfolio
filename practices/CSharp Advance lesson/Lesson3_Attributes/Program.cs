using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Lesson3_Attributes
{
    class Program
    {
        static void Main(string[] args)
        {
            var types = from t in Assembly.GetExecutingAssembly().GetTypes()
                        where t.GetCustomAttributes<SampleAttribute>().Count() > 0
                        select t;
            foreach(var t in types){
                Console.WriteLine(t.Name);
                foreach( var p in t.GetProperties()){
                    Console.WriteLine(p.Name);
                }
                foreach (var m in t.GetMethods()) {
                    Console.WriteLine(m.Name);
                }
            }
        }
    }
    [AttributeUsage(AttributeTargets.Class)]
    class SampleAttribute : Attribute {
        public string Name { get; set; }
        public int Version { get; set; }
        
    }

    [Sample(Name="Rui", Version = 1)]
    class Test {
        public int intValue { get; set; }
        public void Method() { }
    }

    [Sample]
    class Test2 {

    }

    class NoAttribute {

    }
}
