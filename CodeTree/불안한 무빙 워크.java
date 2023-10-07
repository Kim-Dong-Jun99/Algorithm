
import java.util.*;
import java.io.*;

/*
무빙워크 길이가 n, 2n 개의 판

무빙워크는 시계 방향으로 회전, 1번칸에서 타서 n번칸에서 내림
사람이 칸에 올라가면 칸의 안정성은 즉시 1 감소, 안정성 0인 칸 탑승 불가

1. 무빙워크 회전
2. 가장 먼저 무빙워크에 올라간 사람부터 무빙워크가 회전하는 방향으로 이동할 수 있음 이동
    - 앞선 칸에 사람이 있거나 안정성 0이면 이동 불가
3. 1번 칸에 사람이 없고 안정성이 0이 아니면 사람을 한명 더 올림
4. 안정성이 0인 칸이 k개 이상이면 과정을 종료

1~3번중 n번칸 위치에 사람이 위치하면 그 즉시 내리게된다.



*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int[] inputArray;
    int n, k, safetyLength;
    int[] safety;
    boolean[] occupied;
    int experiment;
    PriorityQueue<Person> people;
    int unsafe;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
            main.printResult();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        n = inputArray[0];
        k = inputArray[1];

        safety = getInputArray();
        occupied = new boolean[n];
        people = new PriorityQueue<>(Person::compareWithEntered);
        safetyLength = n * 2;

        experiment = 0;
        unsafe = 0;
    }


    void solution() {
        do {
            experiment += 1;
            spinMovingWalk();
            movePeople();
            addPeople();
        } while (!isEnd());
    }

    boolean isEnd() {
        int unsafe = 0;
        for (int safe : safety) {
            if (safe == 0) {
                unsafe += 1;
            }
        }
        return unsafe >= k;
    }

    int getLeftIndex(int index) {
        return (index - 1 + safetyLength) % safetyLength;
    }

    void spinMovingWalk() {
        int[] newSafety = new int[safetyLength];
        for (int index = 0; index < safetyLength; index++) {
            newSafety[index] = safety[getLeftIndex(index)];
        }
        PriorityQueue<Person> newPeople = new PriorityQueue<>(Person::compareWithEntered);
        for (Person person : people) {
            occupied[person.loc] = false;
            person.loc += 1;
            if (person.loc != n -1) {
                occupied[person.loc] = true;
                newPeople.add(person);
            }
        }
        people = newPeople;
        safety = newSafety;
    }

    void movePeople() {
        PriorityQueue<Person> newPeople = new PriorityQueue<>(Person::compareWithEntered);
        while (!people.isEmpty()) {
            Person person = people.remove();
            occupied[person.loc] = false;
            int next = person.loc + 1;
            if (safety[next] > 0 && !occupied[next]) {
                safety[next] -= 1;
                if (next == n - 1) {
                    continue;
                }
                person.loc = next;

            }
            occupied[person.loc] = true;


            newPeople.add(person);
        }
        people = newPeople;
    }

    void addPeople() {
        if (safety[0] > 0 && !occupied[0]) {
            occupied[0] = true;
            safety[0] -= 1;
            people.add(new Person(experiment, 0));
        }
    }

    void printResult() throws IOException {
        System.out.println(experiment);
    }

    static class Person{
        int entered;
        int loc;

        public Person(int entered, int loc) {
            this.entered = entered;
            this.loc = loc;
        }

        int compareWithEntered(Person compare) {
            return Integer.compare(this.entered, compare.entered);
        }
    }



}
