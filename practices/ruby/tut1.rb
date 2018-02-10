#!/usr/bin/env ruby

# line comment 
MY_CONSTANT = 123 # constant variable
my_var = "test" # variable
# class MyClass in naming classes

#assignment & data types
my_string = "I'm a string"
my_int = 123
my_float = 12.4
my_bool = true

# output strings
puts my_string + " "+ String(my_int + my_float) + String(my_bool)
puts "#{my_string} #{my_int} #{my_float}"
puts "%s %i %f" %[my_string, my_int, my_float]

# another type is using symbol
# immutable value
my_symb = :imok
my_symb = "ok"
puts "#{my_symb} my symbol"

# Arrays
my_arr = [2,3,[23,1],"me", :ok ]
puts my_arr[2] 
puts my_arr[4]
puts "#{my_arr} my array" 
# hashes 
my_hash = {"key" => "value", :myval => [2,3]}
puts "#{my_hash[:myval][0]} hashes"

# alternative way for hashes symbol
my_hash = {"next"=>"val" , myval: [4,5]}
puts  "#{my_hash[:myval][0]} Hash Alternative"
