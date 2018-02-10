#!/usr/bin/env ruby

#input/output

#automtic new line
puts "hello"
#prints a character no new line
putc 'e'
#no newline
print "hello"


puts ""
#simple input
i = gets
puts "\ninputted #{i}"

#highline
require 'rubygems'
require 'highline/import'

loop do
  cmd = ask("Enter command: ", %w{save load reset quit}) do |q|
  q.readline = true
  end
  say("Executing\" #{cmd}\"...")
  break if cmd == "quit"
end

#file open/close *notes can also open pictures but in binary form
filename = "testfile.txt"
#checks if file exist or not   *NOTES Files.writable .executable .readable
if File.file? filename
    # opens the file
	File.open(filename) do |file|
	   #readsline per line 
		while line = file.gets
		   #outputs the line
		   puts line
		end
	end
else
    puts "FILE NOT FOUND"
end

puts "\nReading File"
#reading file
if File.file? filename
    File.open(filename) do |file|
		while line = file.gets
			puts line
		end
	end
end

puts "\nreading file using foreach"
#alternative reading file
IO.foreach(filename) {|line| puts line}
#another alternative reading file
puts "\nreading file using read"
str = IO.read(filename)

puts puts " #{str} length: #{str.length}"
#another alternative way of reading files
puts "reading using readlines"
str = IO.readlines(filename)

puts " #{str} length: #{str.length}"

#write
str = "ME WRITING A FILE"

File.open('writetestfile.txt', 'w') do |file|
   File.open("testfile.txt", 'r') do |filer|
   while line = filer.gets
      file.puts line.length
   end
   end
end



