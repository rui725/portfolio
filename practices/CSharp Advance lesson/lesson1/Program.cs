using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lesson1
{
    class Program
    {
        static void Main(string[] args)
        {
            // learning implicit types
            byte num = 20;  // small
            int num2 = num; // big = small 
                            // but small != big 

            // learning explicit types
            num = (byte)num2; //casting 

            // learning convert types
            num = Convert.ToByte(num2);

            //overflow example 
            num2 = 256;   // int type has value of 256
            num = Convert.ToByte(num2); // converted 256 to a byte(max value of byte = 255) which is why it is an overflow 
                                        // not ideal to do

            //uses of var
            // note var needs to be initialized always and once initialized the type is always needed to be the same until the end of the program or scope. 
            var ok = "String me";
            // can't have ok = 123;

            Console.WriteLine(ok);

        }
    }
}
