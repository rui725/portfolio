using System;
using WindowsFormsApp1.ServiceReference1;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnCalc_Click(object sender, EventArgs e)
        {
            
            try
            {
                int n1 = int.Parse(txtNum1.Text.Trim());
                int n2 = int.Parse(txtNum2.Text.Trim());
               
                Service1Client mc = new Service1Client();
                //Console.WriteLine(cboOperation.);
                switch (cboOperation.Text.ToString().Trim()) {
                    
                    case "Add":
                        txtResult.Text = mc.Add(n1, n2).ToString();
                        break;
                    case "Subtract":
                        txtResult.Text = mc.Sub(n1, n2).ToString();
                        break;
                    case "Multiply":
                        txtResult.Text = mc.Mult(n1, n2).ToString();
                        break;
                    case "Divide":
                        txtResult.Text = mc.Div(n1, n2).ToString();
                        break;
                }
            } catch (Exception ex)
            {
                // do nothing
                Console.WriteLine(ex.ToString());
            }
            
            
        }
    }
}
