#Change the choices to whatever you can't make your mind up about.

import random
a = 0
b = 0
c = 0
print '**First to 3 Wins **'
while a != 3 and b != 3 and c != 3 :
	foo = ['Choice 1', 'Choice 2', 'Choice 3']
	#print(random.choice(foo))  # If you want to see the choice uncomment this 
	if (random.choice(foo)) == 'Choice 1':
		a = a +1
	elif (random.choice(foo)) == 'Choice 2':
		b = b + 1
	elif (random.choice(foo)) == 'Choice 3':
		c = c + 1
else:
	print '**Finished**'
	print a, " = Choice 1"
	print b, " = Choice 2"
	print c, " = Choice 3"
input()

