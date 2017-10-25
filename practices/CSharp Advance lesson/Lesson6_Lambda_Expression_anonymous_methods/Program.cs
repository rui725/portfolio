using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson6_Lambda_Expression_anonymous_methods
{
    delegate void Operation(int i);
    class Program
    {
        static void Main(string[] args)
        {
            //anonymous function using delegates
            Operation op = delegate(int n)
            {
                Console.WriteLine("{0} * 2 = {1}", n, n*2);
                Console.WriteLine("{0} * 3 = {1}", n, n * 3);
                Console.WriteLine("{0} * 4 = {1}", n, n * 4);
            };
            op(3);
            // lambda expression
            // to use that we use "=>" lambda operator
            Operation op2 = n =>
            {
                Console.WriteLine("{0} * 2 = {1}", n, n * 2);
                Console.WriteLine("{0} * 3 = {1}", n, n * 3);
                Console.WriteLine("{0} * 4 = {1}", n, n * 4);
            };
            op2(6);

            // generic delegate
            // Action <param type> doesn't have return value
            // param is the type for the parameter example is Action<int> op = n =>{} where int is the param type for n
           
            Action <int> op3 = ns=> { Console.WriteLine("{0} + {0} = {1}",ns, ns+ns); };
            // Func <in,out> does have return value
            // in is the param type and the last parameter is always the return type NOTE: IT MUST HAVE A RETURN Value
            Func<int, int> op4 = n => { return n*n; };

        }
    }
}
