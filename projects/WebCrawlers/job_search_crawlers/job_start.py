from job_launcher import Launcher
from apscheduler.schedulers.blocking import BlockingScheduler

def initiator():
    begin = Launcher()


#scheduler = BlockingScheduler()
#scheduler.add_job(initiator, 'interval', hours=5)
#scheduler.start()
initiator()