using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lesson7_Events
{
    class Program
    {
        static void Main(string[] args)
        {
            var t = new ClockTower();
            var p = new Person("Rui",t);

            var p2 = new Person("Judy", t);
            t.chime5PM();
            t.chime6AM();
        }
    }

    class Person {

        private string _name;
        private ClockTower _tower;
        public Person(string name, ClockTower tower) {
            _name = name;
            _tower = tower;
            // no parameters when void for delegate
            _tower.Chime += (object sender, ClockTowerEventArgs e) => {
                Console.WriteLine("{0} heard clock chime.", _name);
                switch (e.Time) {
                    case 6: Console.WriteLine("{0} wakes up !!",_name);
                        break;
                    case 17: Console.WriteLine("{0} goes home from work",_name);
                        break;
                }
            };
        }
    }

    public class ClockTowerEventArgs : EventArgs {
        public int Time { get; set; } 
    }

    delegate void chimeEventHandler(object sender, ClockTowerEventArgs args);

    class ClockTower {
        // event handler with delegate
        public event chimeEventHandler Chime;

        public void chime5PM() {
            Chime(this, new ClockTowerEventArgs() { Time=17});
        }

        public void chime6AM() {
            Chime(this, new ClockTowerEventArgs() { Time=6});
        }

    }
}
