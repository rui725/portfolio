import pdb
import re
#------------------------------------------------
def starts():
	print '\n\n\n1 - Login'
	print '2 - Change password'
	print '3 - Sign up'
	print '4 - Display DB users'
	print '5 - Quit' 
	print 'Choice: '
	return raw_input()
#------------------------------------------------
def login():
	global db
	global username
	global password
	print 'Login:\nusername:'
	username = raw_input()
	print'\npassword:'
	password = raw_input()
	if username in db.keys():
		temp=decrypt(db[username])
		if password == temp:
			print 'successfully login'
			return True
	print 'failed login'
	return False
#-----------------------------------------------
def load():
	global db
	try:
		fo = open('db.txt', 'r')
	except Exception:
		fo = open('db.txt','w')
		fo.write('username,password')
		fo.close()
	words = [n.split(',') for n in fo.xreadlines()]
	for n,y in words:
		db[n] =y.replace('\n','')
	fo.flush()
	fo.close()
#-----------------------------------------------
def signup():
	global db
	pass1= '0'
	pass2= '1'
	print 'enter username: '
	user = raw_input()
	if user in db.keys():
		print 'username already exist'
		return signup()
	elif ' ' in user:
		print 'can\'t have space in username'
		return signup()
	while(True):
		print 'enter password: '
		pass1 = raw_input()
		if validpass(pass1):
			print 'enter password again: '
			pass2 = raw_input()
			if pass1==pass2:
				break
			print 'password mismatch'
	if pass1==pass2:
		print 'successfully registered user '+ user
		try:
			fo = open('db.txt','a')
			fo.write('{0},{1}'.format(user,encryp(pass1)))
			fo.close()
		except Exception as e:
			print e
#----------------------------------------------
def changepass():
	global username
	global db
	pass1='0'
	pass2='1'
	if login():
		while(pass1!=pass2):
			print 'User: ' + username
			print 'enter new password'
			pass1 = raw_input()
			if validpass(pass1):
				print 'enter password again for verification'
				pass2 = raw_input()

		if pass1==pass2:
			print 'enter previous password'
			passprev = raw_input()
			temp = decrypt(db[username])
			if temp==passprev:
				db[username] = encryp(pass1)
				fo = open('db.txt','w')
				for n in db.keys():
					fo.write('{0},{1}\n'.format(n,db[n]))
				print'successfully changed password'
			else:
				print 'previous password wrong'
#------------------------------------------------
def showdb():
	load()
	global db
	print '-----------------------------------------------------'
	print 'DB users\n'
	print '-----------------------------------------------------'
	for n in db.keys():
		val = decrypt(db[n])	
		print '{0}\t\t|{1}\t|{2}'.format(n,db[n],val)
#-----------------------------------------------
def encryp(password):
	global db
	patt=''
	for k in range(0,password.__len__()):
		if k%2!=0:
			patt+=chr(ord(password[k])+k)
		else:
			patt+=chr(ord(password[k])-k)
	return patt
#----------------------------------------------
def decrypt(password):
	patt=''
	for n in range(0,password.__len__()):
		if n%2!=0:
			patt+=chr(ord(password[n])-n)
		else:
			patt+=chr(ord(password[n])+n)
	return patt
#-----------------------------------------------
def validpass(password):
	if password.__len__()>=8:
		try:
			if re.search('[a-z]+',password).group(0):
				if re.search('[A-Z]+',password).group(0):
					return True
		except Exception as e:
			pass	
	
	print '\n+ Has at least one capital letter.'
	print '+ Has at least one lower-case letter.'
	print '+ Is at least 8 characters long.\n'
	return False
#----------------------------------------------
db = {}
username =''
password=''
load()
switcher = {
	'1':login,
	'2':changepass,
	'3':signup,
	'4':showdb,
	'5':quit,
}
while(True):
	runswitch = switcher[starts()]
	runswitch()
	if runswitch.__code__.co_name=='quit':
		break
