#!/usr/bin/env ruby

#sample fibanocci

def fib_func(x,y, un)
	yield y
    while x < un
	    yield x
		x,y = x+y,x
	end
end

puts fib_func(1,1,50) {|x| puts x}


def exceptionExample(x,y)
    begin
		x=y/x
		x
		#exception handling part 
		rescue ZeroDivisionError
		  puts "you have used exception handling zero division error"
	end
end

puts exceptionExample(0,2)
puts exceptionExample(4,2.0)
