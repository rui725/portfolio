#INPUTS/OUTPUTS
#Reading from a file
open(MYFILE, 'example.txt'); #open(VarName, filelocation) -- opens file
while(<MYFILE>){    #iterates the text file
	print "$_"; # since the value is text we use $ 
}
close(MYFILE); # close the file

#Writing to a file
open (WFILE, '>cexample.txt'); # > means we are writing to the file doesnt matter if the file doesnt exist it will create it
$text = "I CREATED THIS FILE";
print WFILE $text;
close(WFILE);

#INPUT
print "Enter a number: ";
$num = <STDIN>;
$square = $num**2;
print "the square of $num is $square\n";

#COPYiNG FILES 
open(SOURCE,"<cexample.txt");
open(DESTINATION,">copied.txt");
while(<SOURCE>){
	print DESTINATION $_;
}
close(SOURCE);
close(DESTINATION);

# RENAME FUNCTION
# Renames a txt file
if(rename("copied.txt","rui.txt")){
	print "Successfully renamed to rui.txt\n";	
}else{
	print "unsucessful renamed\n";
}

#DISPLAY DIRECTORY
$source = ('*.*');
@list = glob($source);
$size = @list;
print "TOTAL number of files: $size\n";
foreach $l (@list){
	print "$l \n";
}

#CREATING DIRECTORIES
$folder ="/samplefolder";
if(mkdir($folder)){
  print "successfuly created directory\n";
}else{
  print "failed to create directory\n";
}

