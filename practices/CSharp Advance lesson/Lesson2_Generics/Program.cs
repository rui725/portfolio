using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson2_Generics
{
    class Program
    {
        static void Main(string[] args)
        {
            // ugly code
            var resultint = new ResultInt { Success = true, Data = 23 };
            var resultstr = new ResultString { Success = true, Data = "HI" };
            Console.WriteLine(resultint.Data);
            Console.WriteLine(resultint.Success);
            Console.WriteLine(resultstr.Data);
            Console.WriteLine(resultstr.Success);

            // better code
            var resultgeneric = new Result<int> { Success = true, Data = 123 };
            Console.WriteLine(resultgeneric.Success);
            Console.WriteLine(resultgeneric.Data);

            var resultgeneric2 = new Result<string> { Success = true, Data = "Generic" };
            Console.WriteLine(resultgeneric2.Success);
            Console.WriteLine(resultgeneric2.Data);

            // much much better code
            var p = new ResultPrinter();
            p.Print(resultgeneric);
            p.Print(resultgeneric2);

        }
    }
    // use to for generic types 
    class Result<T> {
        // T can be any type
        public bool Success { get; set; }
        public T Data { get; set; }
    }

    // uses generic to print generic types

    class ResultPrinter {
        // T can be any type
        // Print<T> is needed because in Result<T> it is asking where the T came from
        public void Print<T>(Result<T> result){
            Console.WriteLine(result.Success);
            Console.WriteLine(result.Data);
        }
    }

    // Ugly code because it is duplicated just because it has different types
    class ResultInt {
        public bool Success { get; set; }
        public int Data { get; set; }
    }
    class ResultString
    {
        public bool Success { get; set; }
        public string Data { get; set; }
    }
    class Resultbool
    {
        public bool Success { get; set; }
        public bool Data { get; set; }
    }
    // until here
}
