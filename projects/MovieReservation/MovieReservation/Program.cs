using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MovieReservation
{
    class Program
    {
        static void Main(string[] args)
        {
            //To Change the Look And Feel of the application
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(true);
            new Login().launch();
            //new Menu().launch();
        }
    }
}
