using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MovieReservation {
    class Menu {

        string link = "D:\\PROGS\\Visual Studio 2010\\Projects\\MovieReservation\\MovieReservation\\";

        static string cinema_hold = "", movie_hold = "", date_hold = "";

      

        Form window;

        Panel upper_panel, lower_panel;

        Label reminder;

        ComboBox cinema_list, movie_list, time_list;

        MainMenu mm;
        MenuItem[] mi;

        Button reserve_button;

        PictureBox movie_poster_1, movie_poster_2, movie_poster_3;

        public Menu() {
            window = new Form();

            reminder = new Label();

            upper_panel = new Panel();
            lower_panel = new Panel();

            cinema_list = new ComboBox();
            movie_list = new ComboBox();
            time_list = new ComboBox();

            mi = new MenuItem[3];
            mi[0] = new MenuItem("Digital");
            mi[1] = new MenuItem("FAQ");
            mi[2] = new MenuItem("Contact Us");
            mm = new MainMenu(mi);

            reserve_button = new Button();

            movie_poster_1 = new PictureBox();
            movie_poster_2 = new PictureBox();
            movie_poster_3 = new PictureBox();
        }

        

        public void launch() {
        
            window.Text = "Main Menu";
            window.FormBorderStyle = FormBorderStyle.FixedSingle;
            window.BackColor = Color.FromArgb(37, 51, 113);
            window.Bounds = new Rectangle(10, 10, 760, 590);

            upper_panel.Bounds = new Rectangle(10, 10, 725, 300);
            upper_panel.BorderStyle = BorderStyle.FixedSingle;
            upper_panel.BackgroundImage = Image.FromFile(link + "background.png"); //Change the directory in string link (See declaration above)
            
                //Upper Panel Components Start Here

                cinema_list.Bounds = new Rectangle(10, 10, 180, 30);
                cinema_list.Items.AddRange(new String[] { "Choose Cinema", "Cinema 1", "Cinema 2"});
                cinema_list.DropDownStyle = ComboBoxStyle.DropDownList; //To make the ComboBox uneditable
                cinema_list.SelectedItem = "Choose Cinema";
                cinema_list.SelectedIndexChanged += cb_SelectedIndexChanged;

                movie_list.Bounds = new Rectangle(210, 10, 180, 30);
                movie_list.DropDownStyle = ComboBoxStyle.DropDownList; //To make the ComboBox uneditable
                movie_list.Items.Add("Choose Movie");
                movie_list.SelectedItem = "Choose Movie";
                movie_list.Enabled = false;
                movie_list.SelectedIndexChanged += cb_SelectedIndexChanged;

                time_list.Bounds = new Rectangle(410, 10, 180, 30);
                time_list.DropDownStyle = ComboBoxStyle.DropDownList; //To make the ComboBox uneditable
                time_list.Items.Add("Choose Time");
                time_list.SelectedItem = "Choose Time";
                time_list.Enabled = false;
                time_list.SelectedIndexChanged += cb_SelectedIndexChanged;

                reserve_button.Text = "Reserve";
                reserve_button.Bounds = new Rectangle(610, 9, 100, 24);
                reserve_button.UseVisualStyleBackColor = true; //Activates the new interface for button
                reserve_button.Click += reserve_button_Click; //Method when button is clicked (See method below)

                movie_poster_1.Bounds = new Rectangle(10, 50, 180, 240);
                movie_poster_1.BackgroundImage = Image.FromFile(link + "tfios.jpg"); //Change the directory in string link (See declaration above)
                movie_poster_1.BackgroundImageLayout = ImageLayout.Stretch;

                movie_poster_2.Bounds = new Rectangle(210, 50, 180, 240);
                movie_poster_2.BackgroundImage = Image.FromFile(link + "transformers.jpg"); //Change the directory in string link (See declaration above)
                movie_poster_2.BackgroundImageLayout = ImageLayout.Stretch;

                movie_poster_3.Bounds = new Rectangle(410, 50, 180, 240);
                movie_poster_3.BackgroundImage = Image.FromFile(link + "spiderman.jpg"); //Change the directory in string link (See declaration above)
                movie_poster_3.BackgroundImageLayout = ImageLayout.Stretch;
                
                //Adding of all the components inside the upper_panel

            upper_panel.Controls.Add(cinema_list);
            upper_panel.Controls.Add(movie_list);
            upper_panel.Controls.Add(time_list);
            upper_panel.Controls.Add(reserve_button);
            upper_panel.Controls.Add(movie_poster_1);
            upper_panel.Controls.Add(movie_poster_2);
            upper_panel.Controls.Add(movie_poster_3);

            //Upper Panel Components End Here

            lower_panel.Bounds = new Rectangle(10, 320, 725, 200);
            lower_panel.BorderStyle = BorderStyle.FixedSingle;
            lower_panel.BackColor = Color.FromArgb(214, 217, 224);

            //Lower Panel Components Start Here

                reminder.Bounds = new Rectangle(25, 25, 500, 200);
                reminder.Font = new Font("Tahoma", 9);
                reminder.Text = "Guidelines\n\nFor our mutual protection, please present your bags and other belongings for inspection.\n\nThe following are prohibited inside the cinema:\n\n• Smoking\n• Firearms\n• Pointed, sharp and bladed objects\n• Carrying or bringing in any kind of video-recording device.";

            //Adding of all the components inside the lower_panel
            lower_panel.Controls.Add(reminder);

            //Lower Panel Components End Here

            //Adding of all the components inside the form

            window.Controls.Add(upper_panel);
            window.Controls.Add(lower_panel);
            window.Menu = mm;
            window.ShowDialog();
        }

        //Disabling Combo Box
        void cb_SelectedIndexChanged(object sender, EventArgs e)
        {
            //cinema list
            if (sender == cinema_list)
            {
                if (cinema_list.SelectedIndex == 0)
                {
                    movie_list.Enabled = false;
                    time_list.Enabled = false;
                }
                else if (cinema_list.SelectedIndex != 0 )
                {
                    movie_list.Enabled = true;
                    movie_list.Items.Clear();
                    if (cinema_list.SelectedIndex == 1)
                    {
                        movie_list.Items.AddRange(new String[] { "Choose Movie", "The Fault in our Stars", "The Amazing Spiderman 2"});
                    }
                    else if (cinema_list.SelectedIndex == 2)
                    {
                        movie_list.Items.AddRange(new String[] { "Choose Movie", "The Amazing Spiderman 2", "Transformers: Age of Extinction" });
                    }
                    movie_list.SelectedItem = "Choose Movie";
                }
                movie_list.SelectedIndex = 0;
                time_list.SelectedIndex = 0;
            }
            //movie list
            if (sender == movie_list)
            {
                if (movie_list.SelectedIndex == 0)
                {
                    time_list.Enabled = false;
                }
                else if (movie_list.SelectedIndex != 0)
                {
                    time_list.Enabled = true;
                    time_list.Items.Clear();
                    if (movie_list.SelectedIndex == 1)
                    {
                        time_list.Items.AddRange(new String[] { "Choose Time", "October 1, 2014 02:30 PM" });
                    }
                    else if (movie_list.SelectedIndex == 2)
                    {
                        time_list.Items.AddRange(new String[] { "Choose Time", "October 1, 2014 05:30 PM" });
                    }
                    time_list.SelectedItem = "Choose Time";
                }
                time_list.SelectedIndex = 0;
            }
        }

        private void reserve_button_Click(object sender, EventArgs e)
        {
            //Modify This Part Once You Already Have a Database Connection

            cinema_hold = cinema_list.Text;
            movie_hold = movie_list.Text;
            date_hold = time_list.Text;

            if (movie_hold != "Choose Movie" && cinema_hold != "Choose Cinema" && date_hold != "Choose Time")
            {
                window.Visible = false;
                new Reserve().launch();
            }
        }


        //Use this method if you want to access the data from this class to another class
        //Make sure you assign a value to cinema_hold or movie_hold or date_hold before calling this methods
        //Example: cinema_hold = cinema_list.GetItemText().toString();
        public string getCinema() {
            return cinema_hold;
        }

        public string getMovie() {
            return movie_hold;
        }

        public string getDate() {
            return date_hold;
        }


    }
}
