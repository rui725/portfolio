#!/usr/bin/env ruby

# class OOP template for the object 
# class name must be Capital Letter
class Book
	def myMethod
	    puts "I'm a method"
	end
end

#require 'file.rb' to import library

# create instance of class object
c = Book.new
c.myMethod

class Example
    # this variables are protected can't access
	
	# this is a class variable
	# can be used by all instanced objects and 
	# changes are applied across all the instanciated objects
    @@aClassVar = "hello from aClassVar"
	# this is a an instance variable
	# this only exists on the instance object and different from others
	# therefore if we change the value it only affects the specific instance of an object
	# needs to be set can't be initialized
	@aInstanceVar = "Hello from aInstanceVar"
	def getClassVariable
	    puts @@aClassVar
	end
	
	def setClassVariable(s)
	    @@aClassVar = s
	end
	
	def getIVar
	    puts @aInstanceVar
	end
	def setIVar(s)
	    @aInstanceVar = s
	end
end

e = Example.new
e2 = Example.new
e.setIVar("test")
e2.setIVar("test2")
# different from each other
e.getIVar
e2.getIVar

# class variables
e.getClassVariable
e.setClassVariable("Class changed")
# also changed
e2.getClassVariable

# Accessing protected variables
class ExAccess
	# this lets protected to be a just only getter
	attr_reader :aIVar
	# this lets protected to have get and setter
	attr_accessor :exIVar
	# this lets protected to be just only setter
	attr_writer :wrIVar
	def setIVar(s)
	    @aIVar = s
	end
	def getWVar
	    puts @wrIVar
	end
end
#get setter 
ex = ExAccess.new
# set
ex.exIVar = "get setter"
# get 
ex.exIVar
#reader or getter only no setter so you have to create a setter method
#set
ex.setIVar("reader")
#get it is accessible by the attr_reader
ex.aIVar

#writer or setter only no getter so you have to create a getter method
#set is accessible by the attr_writer
ex.wrIVar = "Writer"
#get
ex.getWVar


#declaring global variable
$var = "ExMethod"
#methods without parameters name should be small letter
def exMethod
    # explicitly the last line will be the return
	# return var is also allowed but this also works
    $var
end
#methods with parameters
def exMethod2(s)
    $var = s
end
puts exMethod
exMethod2("ye")
puts $var