using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson5_Delegates
{
    //acts as a class
    delegate void MyDelegate();

    //with parameters
    delegate void MyDelegate2(int k);

    delegate void Operation(int n);

    class Program
    {
        static void Main(string[] args)
        {
            // Delegates is a way to encapsulate methods

            // declaration and usage of delegates no parameters
            var del = new MyDelegate(ExampleMethod);
            MyDelegate del2 = ExampleMethod;

            // declaration with parameters
            MyDelegate2 del3 = Add;
            MyDelegate2 del4 = GiveDelegate();
            
            
            //calling function without parameters
            del.Invoke();
            del2();
            Test(del2);

            // calling function with parameters
            del3(2);
            del4(6);

            //POWER OF Delegates
            // you can append function to another function execution A.K.A chained execution
            Operation op = Add;
            // continously add operation right after Add
            op += multiply;
            op += multiply;
            op += multiply;
            op += Add;

            // removes the function to chain execution
            op -= multiply;

            //Executes code
            Execute(2, op);
        }
        static void ExampleMethod() {
            Console.WriteLine("You are in Example Method");
        }

        static void Add(int n) {
            Console.WriteLine("{0} + {0} = {1}",n , n+n );
        }

        static void multiply(int n) {
            Console.WriteLine("{0} x 3 = {1}", n , (n*3));
        }

        static void Execute(int n, Operation op) {
            op(n);
        }

        static void Test(MyDelegate del) {
            del();
        }

        static MyDelegate2 GiveDelegate() {
            return new MyDelegate2(Add);
        }
    }
}
