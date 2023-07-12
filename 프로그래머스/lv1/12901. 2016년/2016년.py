import calendar
def solution(a, b):
    week_list = ["MON","TUE","WED","THU","FRI","SAT","SUN",]
    return week_list[calendar.weekday(2016,a,b)]