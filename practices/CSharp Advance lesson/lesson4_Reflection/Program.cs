using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace lesson4_Reflection
{
    class Program
    {
        static void Main(string[] args)
        {
            //Reflection is a away to look at the existing codes and define it's attributes
            //get executable content
            var assembly = Assembly.GetExecutingAssembly();
            //gets the running namespace, version assembly, etc
            Console.WriteLine(assembly.FullName);

            var types = assembly.GetTypes();
            
            foreach (var type in types)
            {
                Console.WriteLine("Types: "+type.Name);
                var p = type.GetProperties();
                foreach (var prop in p)
                {
                    Console.WriteLine("\tProperties: "+prop.Name + " PropertyType: "+ prop.PropertyType);
                }
                var f = type.GetFields();
                foreach (var field in f)
                {
                    Console.WriteLine("\tFields: "+field.Name);
                }
                var m = type.GetMethods();
                foreach (var method in m)
                {
                    Console.WriteLine("\tMethod: "+method.Name);
                }
                Console.WriteLine("\n");
            }

            var sample = new Sample { Name="Rui", Age = 23 };
            // needs to be run first to be recognized
            var sampleType = sample.GetType();
            // compiled not needed to be run first to be recognized
            sampleType = typeof(Sample);
            // gets property
            var nameProperty = sampleType.GetProperty("Name");
            // prints the value of the property
            Console.WriteLine(nameProperty.GetValue(sample));

            // this is important way to alternatively call methods without calling an instance of object
            var myMethod = sampleType.GetMethod("samplefunction");
            // runs the method
            myMethod.Invoke(sample, null);
            Console.WriteLine("\n\n");
            // Using Reflection and Attributes
            var tyattributes = assembly.GetTypes().Where(t => t.GetCustomAttributes<MyClass>().Count() > 0);
            foreach (var type in tyattributes)
            {
                Console.WriteLine(type.Name);
                var ms = type.GetMethods().Where(t => t.GetCustomAttributes<MyMethod>().Count() > 0);
                foreach (var m in ms)
                {
                    Console.WriteLine(m.Name);
                }
            }
        }
    }
    
    [MyClass]
    class Sample {
        public string Name { get; set; }
        public int Age;
        [MyMethod]
        public void samplefunction() {
            Console.WriteLine("Your are in Samplefunction");
        }

        public void noMethodAttribute() { }
    }

    class NoClassAttribute { }

    [AttributeUsage(AttributeTargets.Class)]
    public class MyClass : Attribute {

    }

    [AttributeUsage(AttributeTargets.Method)]
    public class MyMethod : Attribute { }
}
