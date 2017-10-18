#Import IDOL and logging packages
from iodpython.iodindex import IODClient
import logging

#Declare global variables
global count2 
global bad
global good
global answer 
global answers1
count2 = 0
answers1 = ""
bad = "#################################################"+ '\n' + 	 "#						#"+ '\n' + 	 "#	Coupon					#" + '\n' + 	 "#	Sorry for the inconvenience		#"+ '\n' + 	 "#	One free meal + Drink			#"+ '\n' + 	 "#	Valid till 31/12/17			#"+ '\n' + 	 "#						#"+ '\n' +"#################################################"
good = "#################################################"+ '\n' + 	 "#						#"+ '\n' + 	 "#	Coupon					#" + '\n' + 	 "#	Thanks for completing the survey	#"+ '\n' + 	 "#	Free Drink on next visit		#"+ '\n' + 	 "#	Valid till 31/12/17			#"+ '\n' + 	 "#						#"+ '\n' +"#################################################"

def neg (topic):
	answers2 = ""
	ans1 = ""
	topic = n_topics
	#print the negative topic 
	print n_topics
	count = 0
	#for loop to loop throught the topics
	for i in (n_topics):
		if  'food' in n_topics[count]:
			print '\n' + "**new questions loop food**", '\n'
			questions3 = ["What was wrong with the food?  ","How long did the food take to arrive? ", "Was the food nicely presented ?"]
			#for loop to loop throught the set of questions
			for i in questions3:
				print i
				#take users input
				ans1 = raw_input("Type your answer: ")
				print '\n'
				#concatenate answer to string
				answers2 = answers2 +" "+ ans1+"."
			print answers2,'\n'
			count += 1
		elif "waiter" in n_topics[count]:
			print '\n' + "**new questions loop waiter**", '\n'
			questions4 = ["What was wrong with the waiter?  ","Was the waiter nice and helpful? ", "Did the waiter offer you more drinks ?"]
			#for loop to loop throught the set of questions
			for i in questions4:
				print i
				#take users input
				ans1 = raw_input("Type your answer: ")
				#concatenate answer to string
				answers2 = answers2 +" "+ ans1+"."
			print answers2,'\n'
			count += 1
		elif "restaurant" in n_topics[count]:
			print '\n' + "**new questions loop restaurant**", '\n'
			questions5 = ["What was wrong with the restaurant?  ","Was the restaurant cold/warm? ", "Was the restaurant unclean ?"]
			#for loop to loop throught the set of questions
			for i in questions5:
				print i
				#take users input
				ans1 = raw_input("Type your answer: ")
				#concatenate answer to string
				answers2 = answers2 +" "+ ans1+"."
			print answers2,'\n'
			count += 1
		else:
			#else statment
			print '\n' + "**new negative topic**"

def call(answers,count2):
	#Call client from IOD and pass in API key
	client = IODClient('http://api.idolondemand.com', '#*APIKEY*#')
	text = answers
	#set data to text dictionary
	data = {'text':text}
	#tell the client what API to call
	r = client.post('analyzesentiment', data)
	#Return information from IOD
	sentiment = r.json()['aggregate']['sentiment']
	score = r.json()['aggregate']['score']
	print "********************************",'\n'
	data = r.json()
	count1 = 0
	
	#Set lists to hold topics
	global n_topics
	global p_topics
	n_topics = []
	p_topics = []
	
	for i in (data['negative']):
		#If statment to check is there is a negative topic
		if 'negative' not in data:
			print('negative key is missing')
		elif len(data['negative']) == 0:
			print('no items in the negative list')
		elif 'topic' not in data['negative'][count2]:
			print('topic is missing')
		elif data['negative'][count2]['topic'] == '':
			print('topic is empty')
		else:
			#what to return from the negative topic
			print "Text:", "'"+text+"'"
			print "Sentiment: negative "
			print "Topic:" , (data['negative'][count2]['topic'])
			print "Score:", (data['negative'][count2]['score'])
			n_topics.append(str(data['negative'][count2]['topic']))
			if count2 == 1:
				print "negative topic"
			break
		logging.warning('Watch out!')
		
	print '\n',"********************************"
	print '\n',"Negative topics:",n_topics
	print '\n',"********************************", '\n'
	#If statment to check is there is a positive topic
	for i in (data['positive']):
		if 'positive' not in data:
			print('positive key is missing')
		elif len(data['positive']) == 0:
			print('no items in the positive list')
		elif 'topic' not in data['positive'][count1]:
			print('topic is missing')
		elif data['positive'][count1]['topic'] == '':
			print('topic is empty')
		else:
			#what to return from the positive topic
			print "Text:", "'"+text+"'"
			print "Sentiment: positive "
			print "Topic:" , (data['positive'][count1]['topic'])
			print "Score:", (data['positive'][count1]['score'])
			p_topics.append(str(data['positive'][count1]['topic']))
			count1 += 1
		print '\n',"********************************",'\n'
	print "Positive topics:",p_topics,'\n'
	
	# print statments to print the aggregate for the data_set
	#print "Aggregate Result"
	#print "sentiment: ", sentiment + '\n',"score: ", score , '\n'
	print "********************************"
	

#Allow users input
answer = raw_input("Did you eat in or take out: ")
print '\n'

#Set variables for counting purposes 
count = 0
x = 0
#If statment to check which branch of questions to take
if 'in' in answer:
	questions1 = ["What was the restaurant like ?  ","What was the food like ? ", "Was the waiter attentive ?"]
	#for loop to loop throught the questions
	for i in questions1:
		print i
		ans = raw_input("Type your answer: ")
		answers1 = ans+"."
		call(answers1,count2)
		# if to check for negative topic
		if  not n_topics:
			print "No negative topic",'\n'
		else:
			print "negative topic" , n_topics[count]
			neg(n_topics[count2])
			x += 1
elif 'out' in answer:
	questions2 = ["Was the receptionist welcoming ?  ","Did it take long to collect your order ? ", "Was the receptionist polite ?"]
	#for loop to loop throught the questions
	for i in questions2:
		print i
		ans = raw_input("Type your answer: ")
		answers1 = ans+"."
		call(answers1,count2)
		# if to check for negative topic
		if  not n_topics:
			print("No negative topic")
		else:
			print "negative topic" , n_topics[count]
			neg(n_topics[count2])
			x += 1

#If statment to print the correct coupon 
if x >=1:
	print ('\n' + "***Thanks for your feedback***"+'\n' )
	print bad
else:
	print ('\n' + "***Thanks for your feedback***"+'\n' )
	print good
	
#To prevent code from closing
input ()