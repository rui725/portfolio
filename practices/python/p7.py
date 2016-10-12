col = raw_input()
col = col.replace('\(\)','')
col = col[1:col.__len__()-1]
col= col.split(',')
hexav= '#'
for n in col:
	if hex(int(n)).__len__() ==4:
		hexav+=hex(int(n))[2:].upper()
	else:
		hexav+='0{0}'.format(hex(int(n))[2:].upper())
print hexav
