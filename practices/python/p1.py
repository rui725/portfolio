
__author__ = 'Rui Rafael Rosillas'
def inst(name):
	user = '*.:*|'+name+'|*:.*'
	pat =''
	for n in range(0,user.__len__()):
		pat +='*'
	print '\n\n\n\n\nBanner By '+__author__+'\n'+pat+'\n'+user+'\n'+pat
print 'Write your name:'
inst(raw_input())
