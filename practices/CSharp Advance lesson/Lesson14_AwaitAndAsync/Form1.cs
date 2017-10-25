using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lesson14_AwaitAndAsync
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            // not applicable not desired because it lags the interface
            /*
             specifically using thread.sleep(2000)
            label1.Text = DoSetName("Rui");
            */

            // using Task
            /*
            Task.Factory.StartNew(() => label1.Text = DoSetName("Rui"));
            */

            // using Task with  Continue With but it's too long
            /*
            Task.Factory.StartNew(() => DoSetName("Rui")).ContinueWith(t => label1.Text = t.Result, TaskScheduler.FromCurrentSynchronizationContext());
            */

            importantMethod("Rui");
            label1.Text = "Waiting...";
        }
        // async may contain an await keyword
        // await keywords is basically wait till it is done before moving on
        private async void importantMethod(string name) {
            var result = await AsyncTask(name);
            label1.Text = result;
        }

        private Task<string> AsyncTask(String name) {
            return Task.Factory.StartNew(() => DoSetName(name));
        }

        private string DoSetName(string name)
        {
            Thread.Sleep(2000);
            return "Hello " + name;
        }
    }
}
