import sys

month_date = {1:31, 2:28, 3:31, 4:30, 5:31, 6:30, 7:31, 8:31, 9:30, 10:31, 11:30, 12:31}

# 날짜를 숫자로 변환하는 함수
def convert(month, date):
	result = 0
	for i in range(1,month):
		result += month_date[i]
	result += date
	return result


N = int(sys.stdin.readline())
flowers = []
start_date = convert(3, 1)
end_date = convert(11, 30)
for _ in range(N):
	b_month, b_date, d_month, d_date = map(int, sys.stdin.readline().split())
	flowers.append([convert(b_month, b_date), convert(d_month, d_date)])
	
flowers.sort(key = lambda x : x[0])
result = 0
cur_max = start_date
next_max = 0
link = False
for start, end in flowers:
	if start >  cur_max:
		if link == False:
			break
		result += 1
		cur_max = next_max
		next_max = end
	else:
		link = True
		if end > next_max:
			next_max = end
			if next_max >= end_date:
				result += 1
				break
		

if link == False:
	print(0)
else:
	print(result)
