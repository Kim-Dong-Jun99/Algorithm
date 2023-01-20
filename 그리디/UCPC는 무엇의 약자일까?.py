words = input()

ucpc = "UCPC"
index = 0
done = False
for i in words:
	if i == ucpc[index]:
		index += 1
		if index == 4:
			done = True
			break

if done:
	print("I love UCPC")
else:
	print("I hate UCPC")