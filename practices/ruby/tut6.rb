#!/usr/bin/env ruby
#load or require a file
load 'tut5.rb'

# does not load again because it has already been loaded
b = Book.new

#displays all methods in a better way
puts b.methods.sort

#Duck Typing appends element in to an array or String
CONSTANT_NUM = 0

class DTTest
	CONSTANT_ = 23
	iVar = 100
	def addOne(s, one)
	   # duck typing
	    s << one
	end
	# constructor method
	def initialize
	   puts "I'm a constructor"
	   # :: access global variable
	   puts "#{::CONSTANT_NUM} constant"
	end
end

d = DTTest.new
s = []
for i in (1..10)
    d.addOne(s,i)
end
puts s

d.addOne(s, "ADD ONE LAST ONE")
puts s

