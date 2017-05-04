#Loops in PERL


#While Loop
$num =1;
while($num<=10){
    print $num++." ";
}

print "\n";
#until loop  = works like unless where false is being tested to loop
until ($num<1){
     print $num--. " ";
}
print "\n";

#for loop
for ($i = 1 ; $i <=10;$i++){
	print "$i ";
}
print "\n";

#foreach
@arr = split(" ", "Rui Rafael Rosillas Gwapo");
foreach $var (@arr){
	print "$var ";
}

#do while loop
print "\n";
do {
 print $num++." ";
}while($num<=20);


print "\n";

for($i = 0; $i<20;$i++){
   if ($i==19){
   	print "$i \n";
	last; # acts like a break statement
   }
	
   if($i%2!=0){
   next;  # acts like a continue statement 
   }
   if($i==18){
	   $i++;
	   redo; # loops ones disregarding condition acts like a normal loop
   }
   print "$i ";
}
