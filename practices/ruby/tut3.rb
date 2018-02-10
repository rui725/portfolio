#!/usr/bin/env ruby

my_bool = !true;
# statments conditions
if my_bool
   puts "im true"
else
   puts "im false"
end

#0 means true

if 0
   puts "im true"
elsif !my_bool
   puts "I'm false"
else
   puts "I'm neither"
end

# input from user
puts "Enter sum number: "
my_input = gets.to_i

# unless statements can't have elsif 
unless my_input > 0
    puts "unless input less than 0"
else
    puts "unless input greater than or equal to 0"
end

# case switches no need for break continues
case my_input
	when -1000..-1
		puts "case less than 0"
	when 0 
	        puts "case is zero" 
	when 1..10000
        	puts "case greater than 0"        
end