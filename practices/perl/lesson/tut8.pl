#TIME FORMATTING
#localtime() to get current time
$time = localtime();
print "$time\n";
@time = localtime();
print "@time\n";
$seconds = @time[0];
$minutes = @time[1];
$hours = @time[2];

print "Current Time: $hours:$minutes:$seconds\n";

@months = ("Jan","Feb","Mar","Apr","May","Jun", "Jul","Aug","Sep","Oct","Nov","Dec");
@week = ("Sun","Mon","Tue","Wed","Thu","Fri", "Sat");
$dayOfMonth= @time[3];
$month = @months[@time[4]];
$year = @time[5] +1900;
$week_day = @week[@time[6]];
print "Current Date:  $week_day $month $dayOfMonth $year\n";

#MILITARY AND DIGITAL TIME
#MILITARY

print "Military Time: $hours:$minutes:$seconds\n";

#DIGITAL
$str='';
if ($hours >=12){
	if(not($hours ==12)){
	    $hours -=12;
	}
	$str = "PM";
}else{
	$str = "AM";
}
print "Digital Time: $hours:$minutes:$seconds $str\n";
