using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MovieReservation
{
    class Login {

        Form window;

        Panel inner_panel, outer_panel;

        Label user_label, pass_label, separator, separator_footer, login_msg, footer_msg;

        TextBox user_box, pass_box;

        Button login_button;

        public Login() {
            window = new Form();

            inner_panel = new Panel();
            outer_panel = new Panel();

            login_msg = new Label();
            user_label = new Label();
            pass_label = new Label();
            separator = new Label();
            separator_footer = new Label();
            footer_msg = new Label();

            user_box = new TextBox();
            pass_box = new TextBox();

            login_button = new Button();
        }
        
        public void launch() {
            window.Bounds = new Rectangle(10, 10, 600, 500);
            window.BackColor = Color.FromArgb(37, 51, 113);
            window.FormBorderStyle = FormBorderStyle.Fixed3D;
            window.Text = "Login";

            outer_panel.Bounds = new Rectangle(135, 120, 322, 220);
            outer_panel.BorderStyle = BorderStyle.FixedSingle;
            outer_panel.BackColor = Color.FromArgb(214, 217, 224);

            inner_panel.Bounds = new Rectangle(10, 10, 300, 200);
            inner_panel.BorderStyle = BorderStyle.FixedSingle;
            inner_panel.BackColor = Color.FromArgb(214, 217, 224);

                //Inner Panel Components Start Here    

                login_msg.Text = "Login";
                login_msg.Font = new Font("Tahoma", 14);
                login_msg.Bounds = new Rectangle(20, 10, 100, 30);

                user_label.Text = "Username:";
                user_label.Font = new Font("Tahoma", 11);
                user_label.Bounds = new Rectangle(25, 70, 80, 30);

                user_box.Bounds = new Rectangle(110, 70, 150, 30);

                pass_label.Text = "Password:";
                pass_label.Font = new Font("Tahoma", 11);
                pass_label.Bounds = new Rectangle(25, 100, 80, 30);

                pass_box.Bounds = new Rectangle(110, 100, 150, 30);
                pass_box.PasswordChar = '*';

                login_button.Text = "Login";
                login_button.Bounds = new Rectangle(160, 130, 100, 30);
                login_button.UseVisualStyleBackColor = true; //Activates the new interface for button
                login_button.Click += login_button_Click; //Method to be used when button is clicked (See method below)


                separator.AutoSize = false; //Separator is the line between Login and User-Pass Forms
                separator.Height = 2;
                separator.Width = 300;
                separator.Location = new Point(0,40);
                separator.BorderStyle = BorderStyle.Fixed3D;

                //Adding of all the components inside the inner_panel    

            inner_panel.Controls.Add(login_msg);
            inner_panel.Controls.Add(separator);
            inner_panel.Controls.Add(user_label);
            inner_panel.Controls.Add(user_box);
            inner_panel.Controls.Add(pass_label);
            inner_panel.Controls.Add(pass_box);
            inner_panel.Controls.Add(login_button);

            //Inner Panel Component End Here

            separator_footer.AutoSize = false; //Separator is the line above the footer message
            separator_footer.Height = 1;
            separator_footer.Width = 600;
            separator_footer.Location = new Point(0, 430);
            separator_footer.BorderStyle = BorderStyle.Fixed3D;

            footer_msg.Text = "All Rights Reserved 2014";
            footer_msg.Font = new Font("Tahoma", 9);
            footer_msg.ForeColor = Color.White;
            footer_msg.Bounds = new Rectangle(215, 440, 140, 20);

            //Adding of all the components inside the form

            outer_panel.Controls.Add(inner_panel);

            window.Controls.Add(outer_panel);
            window.Controls.Add(separator_footer);
            window.Controls.Add(footer_msg);
            window.ShowDialog();
        }

        private void login_button_Click(object sender, EventArgs e) {
            //Modify This Part Once You Already Have a Database Connection
            if (user_box.Text.ToUpper() == "ADMIN" && pass_box.Text.ToUpper() == "PASSWORD")
            {
                window.Dispose();
                new Menu().launch();

            }
            else {
                MessageBox.Show("Wrong account information!!", "WA R N I N G ! ! !");
                user_box.Text = "";
                pass_box.Text = "";
            }

            

            
        }

        private void database_method() { 
            //Insert Your Database Connection and Query Here
        }

    }
}
