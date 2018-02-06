#!/usr/bin/env ruby
#Directories
puts Dir.pwd
puts Dir.entries(Dir.pwd)

#csv
require 'csv'
filename = "data.csv"
if File.file? filename 
	CSV.foreach(filename) do |data|
	 p data
	end
else
    CSV.open(filename, 'w') do |data|
	data << ["name","ext", "room"]
	data << ["test", "123", "ok"]
	data << ["OOK", "OOP", "Ruby"]
    end	
end

#Math
