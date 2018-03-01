using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace MovieReservation
{
    class Reserve
    {


        string textfile = "";
        string link = "D:\\PROGS\\Visual Studio 2010\\Projects\\MovieReservation\\MovieReservation\\";

        Form window;

        TableLayoutPanel seat_table_left, seat_table_right;

        Label cinema_number, movie_name, price_label, screening_date_label, start_time_label, end_time_label, number_label;

        Label cinema_number_box, movie_name_box, price_box, screening_date_box, start_time_box, end_time_box;

        Label number_box;

        Panel upper_panel, lower_panel;

        Button buy_button, cancel_button;
        
        PictureBox ad1, ad2, ad3;
        Button [,] b = new Button[14,11];

        int price;
        int seat_ctr = 0;

       // Button[,] br = new Button[7, 11];
        public Reserve() {


            window = new Form();

            upper_panel = new Panel();
            lower_panel = new Panel();

            seat_table_left = new TableLayoutPanel();
            seat_table_right = new TableLayoutPanel();

            cinema_number = new Label();
            movie_name = new Label();
            price_label = new Label();
            screening_date_label = new Label();
            start_time_label = new Label();
            end_time_label = new Label();
            number_label = new Label();

            cinema_number_box = new Label(); //Assign here the Cinema Number from Menu.cs
            movie_name_box = new Label(); //Assign here the Movie Title from Menu.cs
            price_box = new Label();
            screening_date_box = new Label();
            start_time_box = new Label(); 
            end_time_box = new Label();
            number_box = new Label();

            buy_button = new Button();
            cancel_button = new Button();

            ad1 = new PictureBox();
            ad2 = new PictureBox();
            ad3 = new PictureBox();
        }

        public void launch() {
            Menu m = new Menu(); //Callin menu class

            string pickmovie = m.getMovie(); //getting the user choice from menu class
            string pickdate = m.getDate();
            string pickcinema = m.getCinema();

            if (pickmovie == "The Fault in our Stars" && pickdate == "October 1, 2014 02:30 PM" && pickcinema == "Cinema 1") 
            {
                textfile = "faultc1230.txt";
            }
            if (pickmovie == "The Amazing Spiderman 2" && pickdate == "October 1, 2014 05:30 PM" && pickcinema == "Cinema 1")
            {
                textfile = "spiderc1530.txt";
            }
            if (pickmovie == "The Amazing Spiderman 2" && pickdate == "October 1, 2014 02:30 PM" && pickcinema == "Cinema 2")
            {
                textfile = "spiderc2230.txt";
            }
            if (pickmovie == "Transformers: Age of Extinction" && pickdate == "October 1, 2014 05:30 PM" && pickcinema == "Cinema 2")
            {
                textfile = "transc2530.txt";
            }

            window.Text = "Reservation";
            window.BackColor = Color.FromArgb(37, 51, 113);
            window.Bounds = new Rectangle(10, 10, 800, 700);
            window.FormBorderStyle = FormBorderStyle.FixedSingle;

            upper_panel.Bounds = new Rectangle(10, 10, 765, 300);
            upper_panel.BorderStyle = BorderStyle.FixedSingle;
            upper_panel.BackgroundImage = Image.FromFile(link + "background.png"); //Change the directory in string link (See declaration above)


            cinema_number.Text = "Cinema Number: " + pickcinema;
                cinema_number.Bounds = new Rectangle(25, 25, 370, 30);
                cinema_number.Font = new Font("Tahoma", 15);
                cinema_number.BackColor = Color.Transparent;
                cinema_number.ForeColor = Color.LightGray;

                movie_name.Text = "Movie: " + pickmovie;
                movie_name.Bounds = new Rectangle(25, 60, 500, 30);
                movie_name.Font = new Font("Tahoma", 15);
                movie_name.BackColor = Color.Transparent;
                movie_name.ForeColor = Color.LightGray;

                //Insert Logic in table, When Cell is click, Add number of seat in number_box
                
                seat_table_left.BackColor = Color.LightBlue;
                seat_table_left.Bounds = new Rectangle(15, 100, 350, 160);
                seat_table_left.CellBorderStyle = TableLayoutPanelCellBorderStyle.Inset;
                seat_table_left.ColumnCount = 9;
                seat_table_left.RowCount = 5;
                seat_table_left.AutoSize = false;

                int num2 = 1;
                for (int i = 1; i <= 6; i++)
                {
                    for (int j = 1; j < 10; j++)
                    {
                        b[i,j] = new Button();
                        b[i,j].Size = new Size(31, 23);
                        b[i, j].Text ="L"+num2;
                        b[i, j].Click += buy_button_Click; 
                        seat_table_left.Controls.Add(b[i,j]);
                        num2++;
                    }
                     }

                seat_table_right.BackColor = Color.LightBlue;
                seat_table_right.Bounds = new Rectangle(379, 100, 370, 160);
                seat_table_right.CellBorderStyle = TableLayoutPanelCellBorderStyle.Inset;
                seat_table_right.ColumnCount = 9;
                seat_table_right.RowCount = 5;
                seat_table_right.AutoSize = false;

                int num = 1; //number for text in button 
              
                for (int i = 6; i < 12; i++)
                {
                    for (int j = 1; j < 10; j++)
                    {
                        b[i, j] = new Button();
                        b[i, j].Size = new Size(33, 23);
                        b[i, j].Text =  "R" + num;
                        b[i, j].Click += buy_button_Click; 
                        seat_table_right.Controls.Add(b[i, j]);
                        num++;
                    }
                }

                List<String> l = new List<String>();
                ///////////////
                if (File.Exists(textfile)) 
                {
                    StreamReader sr = new StreamReader(textfile);
                    int ctr = 0;
                    while (!sr.EndOfStream)
                    {
                        l.Add(sr.ReadLine());

                        string data = l[ctr];
                        string[] ddata = data.Split(',');

                        b[Int32.Parse(ddata[0]), Int32.Parse(ddata[1])].BackColor = Color.Red;

                        ctr++;
                    }

                    sr.Close();

                }   
            
            upper_panel.Controls.Add(cinema_number);
            upper_panel.Controls.Add(movie_name);
            upper_panel.Controls.Add(seat_table_left);
            upper_panel.Controls.Add(seat_table_right);

            //Lower Panel handles the input like buy button

            lower_panel.Bounds = new Rectangle(10, 330, 365, 320);
            lower_panel.BackColor = Color.FromArgb(42, 108, 143);

                price_label.Bounds = new Rectangle(45, 25, 100, 20);
                price_label.Font = new Font("Tahoma", 11);
                price_label.ForeColor = Color.White;
                price_label.Text = "Price";

                price_box.Bounds = new Rectangle(200, 25, 150, 30);
                if (pickmovie == "The Fault in our Stars")
                {
                    price_box.Text = "350";
                    price = int.Parse(price_box.Text);
                    price_box.Text = 0 + "";
                }
                else if(pickmovie == "The Amazing Spiderman 2"){
                price_box.Text="300";
                price = int.Parse(price_box.Text);
                price_box.Text = 0 + "";
                }
                else if(pickmovie =="Transformers: Age of Extinction" ){
                price_box.Text="320";
                price = int.Parse(price_box.Text);
                price_box.Text = 0+"";
                }

               
                screening_date_label.Bounds = new Rectangle(45, 75, 120, 20);
                screening_date_label.Font = new Font("Tahoma", 11);
                screening_date_label.ForeColor = Color.White;
                screening_date_label.Text = "Screening Date";

                screening_date_box.Bounds = new Rectangle(200, 75, 150, 30);
                screening_date_box.Text = "October 1, 2014";

                start_time_label.Bounds = new Rectangle(45, 125, 120, 20);
                start_time_label.Font = new Font("Tahoma", 11);
                start_time_label.ForeColor = Color.White;
                start_time_label.Text = "Start Time";

                start_time_box.Bounds = new Rectangle(200, 125, 150, 30);
             
                if(pickdate=="October 1, 2014 02:30 PM")
                {
                    start_time_box.Text = "02:30 PM";
                    end_time_box.Text = "03:25 PM";
                }
                else if(pickdate=="October 1, 2014 05:30 PM")
                {
                    start_time_box.Text = "05:30 PM";
                    end_time_box.Text = "07:45 PM";
                }
                end_time_label.Bounds = new Rectangle(45, 175, 120, 20);
                end_time_label.Font = new Font("Tahoma", 11);
                end_time_label.ForeColor = Color.White;
                end_time_label.Text = "End Time";

                end_time_box.Bounds = new Rectangle(200, 175, 150, 30);

                number_label.Bounds = new Rectangle(45, 225, 130, 20);
                number_label.Font = new Font("Tahoma", 11);
                number_label.ForeColor = Color.White;
                number_label.Text = "Number of Seat/s";

                number_box.Bounds = new Rectangle(200, 225, 150, 30);

                buy_button.Bounds = new Rectangle(140, 265, 100, 30);
                buy_button.BackColor = Color.White;
                buy_button.Text = "Buy";
                buy_button.Click += buy_button_Click; //Method used when buy button is clicked (See method below)

                cancel_button.Bounds = new Rectangle(250, 265, 100, 30);
                cancel_button.BackColor = Color.White;
                cancel_button.Text = "Cancel";
                cancel_button.Click += cancel_button_Click; //Method used when cancel button is clicked (See method below)

            lower_panel.Controls.Add(price_label);
            lower_panel.Controls.Add(screening_date_label);
            lower_panel.Controls.Add(start_time_label);
            lower_panel.Controls.Add(end_time_label);
            lower_panel.Controls.Add(number_label);
            lower_panel.Controls.Add(price_box);
            lower_panel.Controls.Add(screening_date_box);
            lower_panel.Controls.Add(start_time_box);
            lower_panel.Controls.Add(end_time_box);
            lower_panel.Controls.Add(number_box);
            lower_panel.Controls.Add(buy_button);
            lower_panel.Controls.Add(cancel_button);

            //Lower Panel End Here

            ad1.Bounds = new Rectangle(400, 330, 370, 70);
            ad1.BackgroundImage = Image.FromFile(link + "advertisment.jpg"); //Change the directory in string link (See declaration above)
            ad1.BackgroundImageLayout = ImageLayout.Stretch;

            ad2.Bounds = new Rectangle(400, 400, 370, 70);
            ad2.BackgroundImage = Image.FromFile(link + "advertisment2.jpg"); //Change the directory in string link (See declaration above)
            ad2.BackgroundImageLayout = ImageLayout.Stretch;

            ad3.Bounds = new Rectangle(400, 470, 370, 70);
            ad3.BackgroundImage = Image.FromFile(link + "advertisment3.jpg"); //Change the directory in string link (See declaration above)
            ad3.BackgroundImageLayout = ImageLayout.Stretch;

            window.Controls.Add(upper_panel);
            window.Controls.Add(lower_panel);
            window.Controls.Add(ad1);
            window.Controls.Add(ad2);
            window.Controls.Add(ad3);

            window.ShowDialog();
            
        }


        private void buy_button_Click(object sender, EventArgs e)
        {
            int i = 1, j = 1;
            for (i = 1; i < 6; i++)
            {
                for (j = 1; j < 10; j++)
                {
                    
                    if (sender == b[i, j])
                    {
                        if (b[i, j].BackColor == Color.LightBlue) { // if this the bg color is lightblue
                            b[i, j].BackColor = Color.Green;        // turn the color to green
                            number_box.Text += b[i, j].Text + " ";  // APPENDS TO THE NUMBER OF SEATS 
                            
                            price_box.Text = (price * (++seat_ctr)) + "";
                        }
                        else if (b[i, j].BackColor == Color.Green) { // if bg color is green
                           string temp = number_box.Text;           // gets the current txt from number_text
                           b[i, j].BackColor = Color.LightBlue;     // turn the color light blue
                           string ch = b[i, j].Text;       //GETS THE STRING  value TO BE REMOVE
                            try                                               // USED TRY AND CATCH TO BE ABLE BYPASS NULL VALUE of temp
                            {
                                if (ch.Length == 2) {
                                    number_box.Text = (temp.Replace(ch, "").ToString()); // REMOVES THE SEAT click by replacing the string with "" 
                                    temp = number_box.Text.ToString();
                                    number_box.Text = temp.Substring(0, temp.Length-1).ToString();
                                    number_box.Text = number_box.Text.TrimStart();
                                }
                                else if (ch.Length == 3) {
                                    number_box.Text = (temp.Replace(ch, "").ToString()); // REMOVES THE SEAT click by replacing the string with "" 
                                    temp = number_box.Text.ToString();
                                    number_box.Text = temp.Substring(0, temp.Length ).ToString();
                                    number_box.Text = number_box.Text.TrimStart();
                                
                                }
                              
                                price_box.Text = (price * (--seat_ctr)) + "";
                            }
                            catch (Exception left) {
                                Console.Write(left);
                            }
                        }
                         
                    }    
                }
            }

            for (i = 6; i < 14; i++)
            {
                for (j = 1; j < 10; j++)
                {
                    if (sender == b[i, j])
                    {
                         if (b[i, j].BackColor == Color.LightBlue) {
                            b[i, j].BackColor = Color.Green;
                            number_box.Text += b[i, j].Text + " ";                             
                            price_box.Text = (price * (++seat_ctr)) + "";

                        }
                         else if (b[i, j].BackColor == Color.Green)
                         {
                             string temp = number_box.Text;
                             b[i, j].BackColor = Color.LightBlue;
                             
                             string ch = b[i, j].Text;       //GETS THE STRING  value TO BE REMOVE
                             try                                               // USED TRY AND CATCH TO BE ABLE BYPASS NULL VALUE of temp
                             {
                                 if (ch.Length == 2)
                                 {
                                     number_box.Text = (temp.Replace(ch, "").ToString()); // REMOVES THE SEAT click by replacing the string with "" 
                                     temp = number_box.Text.ToString();
                                     number_box.Text = temp.Substring(0, temp.Length - 1).ToString();
                                     number_box.Text = number_box.Text.TrimStart();
                                 }
                                 else if (ch.Length == 3)
                                 {
                                     number_box.Text = (temp.Replace(ch, "").ToString()); // REMOVES THE SEAT click by replacing the string with "" 
                                     temp = number_box.Text.ToString();
                                     number_box.Text = temp.Substring(0, temp.Length).ToString();
                                     number_box.Text = number_box.Text.TrimStart();

                                 }

                                 price_box.Text = (price * (--seat_ctr)) + "";
                             }
                             catch (Exception right)
                             {
                                 Console.Write(right);
                             }
                         }
                    }
                }
            }

            if (sender == buy_button)
            {       List<string> li = new List<string>(); //later on to be used to get string
                string temp = "a";
              String []st = number_box.Text.Split(' '); // divide and gets each string

              foreach (string s in st)
              {
                  if (s == "L1") { temp = "1,1"; li.Add(temp); }    // IF the value of s in the array is equal to that  we add the string to the List
                  else if (s == "L2") { temp = "1,2"; li.Add(temp); }
                  else if (s == "L3") { temp = "1,3"; li.Add(temp); }
                  else if (s == "L4") { temp = "1,4"; li.Add(temp); }
                  else if (s == "L5") { temp = "1,5"; li.Add(temp); }
                  else if (s == "L6") { temp = "1,6"; li.Add(temp); }
                  else if (s == "L7") { temp = "1,7"; li.Add(temp); }
                  else if (s == "L8") { temp = "1,8"; li.Add(temp); }
                  else if (s == "L9") { temp = "1,9"; li.Add(temp); }
                  else if (s == "L10") { temp = "2,1"; li.Add(temp); }
                  else if (s == "L11") { temp = "2,2"; li.Add(temp); }
                  else if (s == "L12") { temp = "2,3"; li.Add(temp); }
                  else if (s == "L13") { temp = "2,4"; li.Add(temp); }
                  else if (s == "L14") { temp = "2,5"; li.Add(temp); }
                  else if (s == "L15") { temp = "2,6"; li.Add(temp); }
                  else if (s == "L16") { temp = "2,7"; li.Add(temp); }
                  else if (s == "L17") { temp = "2,8"; li.Add(temp); }
                  else if (s == "L18") { temp = "2,9"; li.Add(temp); }
                  else if (s == "L19") { temp = "3,1"; li.Add(temp); }
                  else if (s == "L20") { temp = "3,2"; li.Add(temp); }
                  else if (s == "L21") { temp = "3,3"; li.Add(temp); }
                  else if (s == "L22") { temp = "3,4"; li.Add(temp); }
                  else if (s == "L23") { temp = "3,5"; li.Add(temp); }
                  else if (s == "L24") { temp = "3,6"; li.Add(temp); }
                  else if (s == "L25") { temp = "3,7"; li.Add(temp); }
                  else if (s == "L26") { temp = "3,8"; li.Add(temp); }
                  else if (s == "L27") { temp = "3,9"; li.Add(temp); }
                  else if (s == "L28") { temp = "4,1"; li.Add(temp); }
                  else if (s == "L29") { temp = "4,2"; li.Add(temp); }
                  else if (s == "L30") { temp = "4,3"; li.Add(temp); }
                  else if (s == "L31") { temp = "4,4"; li.Add(temp); }
                  else if (s == "L32") { temp = "4,5"; li.Add(temp); }
                  else if (s == "L33") { temp = "4,6"; li.Add(temp); }
                  else if (s == "L34") { temp = "4,7"; li.Add(temp); }
                  else if (s == "L35") { temp = "4,8"; li.Add(temp); }
                  else if (s == "L36") { temp = "4,9"; li.Add(temp); }
                  else if (s == "L37") { temp = "5,1"; li.Add(temp); }
                  else if (s == "L38") { temp = "5,2"; li.Add(temp); }
                  else if (s == "L39") { temp = "5,3"; li.Add(temp); }
                  else if (s == "L40") { temp = "5,4"; li.Add(temp); }
                  else if (s == "L41") { temp = "5,5"; li.Add(temp); }
                  else if (s == "L42") { temp = "5,6"; li.Add(temp); }
                  else if (s == "L43") { temp = "5,7"; li.Add(temp); }
                  else if (s == "L44") { temp = "5,8"; li.Add(temp); }
                  else if (s == "L45") { temp = "5,9"; li.Add(temp); }
                  ////////////////////////////////For right table///////////////////////////////
                  else if (s == "R1") { temp = "6,1"; li.Add(temp); }
                  else if (s == "R2") { temp = "6,2"; li.Add(temp); }
                  else if (s == "R3") { temp = "6,3"; li.Add(temp); }
                  else if (s == "R4") { temp = "6,4"; li.Add(temp); }
                  else if (s == "R5") { temp = "6,5"; li.Add(temp); }
                  else if (s == "R6") { temp = "6,6"; li.Add(temp); }
                  else if (s == "R7") { temp = "6,7"; li.Add(temp); }
                  else if (s == "R8") { temp = "6,8"; li.Add(temp); }
                  else if (s == "R9") { temp = "6,9"; li.Add(temp); }
                  else if (s == "R10") { temp = "7,1"; li.Add(temp); }
                  else if (s == "R11") { temp = "7,2"; li.Add(temp); }
                  else if (s == "R12") { temp = "7,3"; li.Add(temp); }
                  else if (s == "R13") { temp = "7,4"; li.Add(temp); }
                  else if (s == "R14") { temp = "7,5"; li.Add(temp); }
                  else if (s == "R15") { temp = "7,6"; li.Add(temp); }
                  else if (s == "R16") { temp = "7,7"; li.Add(temp); }
                  else if (s == "R17") { temp = "7,8"; li.Add(temp); }
                  else if (s == "R18") { temp = "7,9"; li.Add(temp); }
                  else if (s == "R19") { temp = "8,1"; li.Add(temp); }
                  else if (s == "R20") { temp = "8,2"; li.Add(temp); }
                  else if (s == "R21") { temp = "8,3"; li.Add(temp); }
                  else if (s == "R22") { temp = "8,4"; li.Add(temp); }
                  else if (s == "R23") { temp = "8,5"; li.Add(temp); }
                  else if (s == "R24") { temp = "8,6"; li.Add(temp); }
                  else if (s == "R25") { temp = "8,7"; li.Add(temp); }
                  else if (s == "R26") { temp = "8,8"; li.Add(temp); }
                  else if (s == "R27") { temp = "8,9"; li.Add(temp); }
                  else if (s == "R28") { temp = "9,1"; li.Add(temp); }
                  else if (s == "R29") { temp = "9,2"; li.Add(temp); }
                  else if (s == "R30") { temp = "9,3"; li.Add(temp); }
                  else if (s == "R31") { temp = "9,4"; li.Add(temp); }
                  else if (s == "R32") { temp = "9,5"; li.Add(temp); }
                  else if (s == "R33") { temp = "9,6"; li.Add(temp); }
                  else if (s == "R34") { temp = "9,7"; li.Add(temp); }
                  else if (s == "R35") { temp = "9,8"; li.Add(temp); }
                  else if (s == "R36") { temp = "9,9"; li.Add(temp); }
                  else if (s == "R37") { temp = "10,1"; li.Add(temp); }
                  else if (s == "R38") { temp = "10,2"; li.Add(temp); }
                  else if (s == "R39") { temp = "10,3"; li.Add(temp); }
                  else if (s == "R40") { temp = "10,4"; li.Add(temp); }
                  else if (s == "R41") { temp = "10,5"; li.Add(temp); }
                  else if (s == "R42") { temp = "10,6"; li.Add(temp); }
                  else if (s == "R43") { temp = "10,7"; li.Add(temp); }
                  else if (s == "R44") { temp = "10,8"; li.Add(temp); }
                  else if (s == "R45") { temp = "10,9"; li.Add(temp); }
                  
              }




               
                StreamWriter wr = new StreamWriter(textfile, true);  
                Object []ol = li.ToArray();    // PUTS THE List in an object array 
              foreach (Object o in ol)     // Dynamically Loops the object 
	{
        
		  wr.WriteLine(o.ToString()); // WRITES dynamically the seats in the file 
	}
               
                wr.Close();
                window.Dispose();
                new Reserve().launch();
            }
        }

        private void cancel_button_Click(object sender, EventArgs e)
        {
            window.Dispose();
            new Menu().launch();
        }

    }
}
