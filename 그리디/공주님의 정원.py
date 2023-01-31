import sys

month_date = {1:31, 2:28, 3:31, 4:30, 5:31, 6:30, 7:31, 8:31, 9:30, 10:31, 11:30, 12:31}

# 날짜를 숫자로 변환하는 함수
def convert(month, date):
	date_num = 0
	for i in range(1,month):
		date_num += month_date[i]
	date_num += date
	return date_num


N = int(sys.stdin.readline())
flowers = []
# 시작날 & 끝날 날짜 변환
start_date = convert(3, 1)
end_date = convert(11, 31)
for _ in range(N):
	b_month, b_date, d_month, d_date = map(int, sys.stdin.readline().split())
	flowers.append([convert(b_month, b_date), convert(d_month, d_date)])
# 시작일이 빠른 순으로 정렬
flowers.sort(key = lambda x : x[0])
result = 0
cur_flower = [0, start_date]
next_flower = [0, 0]
done = False
for start, end in flowers:
	if cur_flower[1] < start:
		if next_flower[1] < start:
			break
		result += 1
		cur_flower = next_flower
		next_flower = [start, end]
	else:
		if end > next_flower[1]:
			next_flower[0] = start
			next_flower[1] = end
		if next_flower[1] >= end_date:
			done = True
			result += 1
			break
	if cur_flower[1] >= end_date:
		done = True
		break
if done:
	print(result)
else:
	print(0)