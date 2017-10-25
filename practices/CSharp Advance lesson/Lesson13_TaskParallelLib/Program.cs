using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Lesson13_TaskParallelLib
{
    class Program
    {
        static void Main(string[] args)
        {
            //Parallel and Async 
            // task sample
            /*
            var t1 = new Task(() => {
                Console.WriteLine("Task 1 is beginning");
                Thread.Sleep(2000);
                Console.WriteLine("Task 1 Ending");
            });
            t1.Start();
            */
            // Task using Methods needs call task.Start() to start
            var t1 = new Task(() => TaskRun(1,1500));
            t1.Start();
            var t2 = new Task(() => TaskRun(2, 3000));
            t2.Start();
            var t3 = new Task(() => TaskRun(3, 1000));
            t3.Start();

            var TList = new List<Task> { t1, t2, t3 };

            // waits all the task defined in the list before proceeding
            Task.WaitAll(TList.ToArray());

            // Task using Methods with built in start = Task.Factory
            // ContinueWith kind of like a chain execution whenever the task is done it will prompt to the next task
            var t4 = Task.Factory.StartNew(() => TaskRun(4, 5000)).ContinueWith((prevTask) => OtherTask(4,5000));

            // Parallel is a built in wait function
            var nList = new List<int>{ 2,4,5,6,6,10,2,5,6,56,4,6,84,5,12,8,6};
            Parallel.ForEach(nList, i => Console.WriteLine(i));

            // wait till the any of task finishes
            Task.WaitAny(t4);

            // CancellationTokens is for stoping task 
            var source = new CancellationTokenSource();
            try
            {
                var t5 = Task.Factory.StartNew(() => TaskCancel(5, 1000, source.Token));
                source.Cancel();
            }
            catch (Exception ex) {
                Console.WriteLine(ex.GetType());
            }
            Console.WriteLine("Press Anything to quit");
            Console.ReadKey();

        }
        static void TaskRun(int id, int sleepTime) {
            Console.WriteLine("Task {0} is beginning",id);
            Thread.Sleep(sleepTime);
            Console.WriteLine("Task {0} Ending",id);
        }
        static void OtherTask(int id, int sleepTime) {
            Console.WriteLine("Task {0} is beginning other task", id);
            Thread.Sleep(sleepTime);
            Console.WriteLine("Task {0} Ending other task", id);

        }
        static void TaskCancel(int id, int sleepTime, CancellationToken c) {
            if (c.IsCancellationRequested) {
                Console.WriteLine("Cancelled Task {0}",id);
                c.ThrowIfCancellationRequested();
            }
            Console.WriteLine("Task {0} is beginning with cancel", id);
            Thread.Sleep(sleepTime);
            Console.WriteLine("Task {0} Ending other with cancel", id);
        }
    }
}
