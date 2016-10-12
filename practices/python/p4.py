import re

email = raw_input()
if(re.search('^[a-zA-Z][a-zA-Z0-9\_]*\@[a-zA-Z]+[a-zA-Z0-9]*\.com',email)):
	print 'Valid Email Address'
else:
	print 'Invalid Email Address'
	if not email[0].isalpha():
		print 'email address always starts with a letter'
		print 'sugguest using a'+email
	elif '@' not in email:
		print 'missing @ sign'
		print 'sugguest email@email.com'
	elif '.' not in email and email[email.__len__()-4:]=='com':
		print 'missing . sign'
		print 'sugguest '+email[email.__len__()-4:]+'.com'
	elif email.rindex('.')-email.index('@')==0:
		print 'domain needed'
		print email[:email.index('@')]+'example.com'

