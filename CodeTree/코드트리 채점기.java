
import java.util.*;
import java.io.*;

/*
N개의 채점기가 준비되어있음,
초기 문제 url에 해당하는 u0이 주어짐, 문제에서 url은 도메인 / 문제ID 형태로 주어짐
    도메인 - 알파벳 소문자와 '.' 로만 이루어짐
    문제 ID - 1이상 10억 이하의 정수
N개의 채점기에는 1번부터 N번까지 번호가 붙어있고, 0초에 채점 우선순위가 1이면서 url이 uo인 초기 문제에 대한 채점 요청이 들어옴

t초에 채점 우선 순위가 p이면서 url이 u인 문제에 대한 채점 요청이 들어온다. 채점 task는 대기 큐에 들어감
단 큐에 있는 task 중 정확히 u와 일치하는 url이 존재하면 추가하지 않음

t초에 채점 대기 큐에서 즉시 채점이 가능한 경우 중 우선 순위가 가장 높은 task를 골라 채점한다.

task가 채점 될 수 없는 조건은 다음과 같다
    task의 도메인이 현재 채점을 진행중인 도메인 중 하나라면 불가
    task의 도메인과 정확히 일치하는 도메인에 대해 가장 최근에 진행된 채점 시작 시간이 start, 종료 시간이 start + gap이고, 현재 시간 t가 start + 3 * gap보다 작다면 부적절한 채점이라 의심하고 채점 x

즉시 채점이 가능한 경우 우선 순위가 가장 높은 task는 아래 조건에 따라 골라짐
    p가 작을 수록 우선순위 높음
    우선 순위가 동일하다면, 큐에 들어온 시간이 더 빠를 수록 우선순위 높음
t초에 채점이 가능한 task가 하나라도 있다면 쉬고 있는 채점기 중 가장 번호가 작은 채점기가 우선순위가 가장 높은 task에 대한 채점 시작, 쉬고 있는게 없다면 넘어감


 t초에 Jid번 채점기가 진행하던 채점이 종료, 채점기는 다시 쉬는 상태
 만약 진행하던 채점이 없으면 스킵

채점대기큐에있는 채점 task의 수를 출력

*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int PREPARE = 100;
    static final int NEW_TASK = 200;
    static final int RUN_TASK = 300;
    static final int FINISH_TASK = 400;
    int Q, N;
    PriorityQueue<Integer> scoringMachines;
    HashMap<String,Work> domainWorkMap;
    HashMap<Integer, String> scoringTaskMap;
    WaitingQueue waitingQueue;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    String[] getInputArray() throws IOException {
        return BR.readLine().split(" ");
    }

    void init() throws IOException {
        Q = Integer.parseInt(BR.readLine());
        scoringMachines = new PriorityQueue<>();
        waitingQueue = new WaitingQueue();
        domainWorkMap = new HashMap<>();
        scoringTaskMap = new HashMap<>();
    }


    void solution() throws IOException {
        while (Q-- > 0) {
            String[] order = getInputArray();
            int orderType = Integer.parseInt(order[0]);

            if (orderType == PREPARE) {
                N = Integer.parseInt(order[1]);
                String url = order[2];
                initScoringMachine();
                addToQueue(0, 1, url);
            } else if (orderType == NEW_TASK) {
                int t = Integer.parseInt(order[1]);
                int p = Integer.parseInt(order[2]);
                String url = order[3];
                addToQueue(t, p, url);
            } else if (orderType == RUN_TASK) {
                int t = Integer.parseInt(order[1]);
                runTask(t);
            } else if (orderType == FINISH_TASK) {
                int t = Integer.parseInt(order[1]);
                int id = Integer.parseInt(order[2]);
                finishTask(t, id);
            } else {
                BW.write(Integer.toString(waitingQueue.getTaskSize()));
                BW.newLine();
            }
        }
        BW.flush();
        BW.close();
    }

    void runTask(int time) {
        if (scoringMachines.isEmpty()) {
            return;
        }
        PriorityQueue<Task> taskHeap = new PriorityQueue<>(Task::getPriorTask);
        for (String domain : waitingQueue.domainTaskMap.keySet()) {
            if (!waitingQueue.domainTaskMap.get(domain).isEmpty() && canWork(domain, time)) {
                taskHeap.add(waitingQueue.domainTaskMap.get(domain).peek());
            }
        }
        if (!taskHeap.isEmpty()) {
            Task toWork = taskHeap.peek();

            int machine = scoringMachines.remove();

            scoringTaskMap.put(machine, toWork.domain);
            domainWorkMap.put(toWork.domain, new Work(time, Integer.MAX_VALUE));

            waitingQueue.domainTaskMap.get(toWork.domain).remove();
            waitingQueue.urls.remove(toWork.url);

        }
    }

    void finishTask(int time, int id) {
        if (!scoringTaskMap.containsKey(id)) {
            return;
        }
        String domain = scoringTaskMap.get(id);
        scoringTaskMap.remove(id);
        Work work = domainWorkMap.get(domain);
        int gap = time - work.startTime;
        work.endTime = work.startTime + gap*3;
        scoringMachines.add(id);
    }

    boolean canWork(String domain, int time) {
        Work work = domainWorkMap.get(domain);
        return !domainWorkMap.containsKey(domain) || work.endTime <= time;
    }

    void initScoringMachine() {
        for (int i = 1; i <= N; i++) {
            scoringMachines.add(i);
        }
    }

    void addToQueue(int t, int p, String u) {
        String[] splitString = u.split("/");
        String domain = splitString[0];
        String id = splitString[1];

        waitingQueue.addNewTask(new Task(t, p, domain, id, u));
        
    }

    static class WaitingQueue {
        HashMap<String, PriorityQueue<Task>> domainTaskMap;
        HashSet<String> urls;

        public WaitingQueue() {
            this.domainTaskMap = new HashMap<>();
            this.urls = new HashSet<>();
        }

        void addNewTask(Task task) {
            if (urls.contains(task.url)) {
                return;
            }
            PriorityQueue<Task> taskHeap = domainTaskMap.getOrDefault(task.domain, new PriorityQueue<>(Task::getPriorTask));
            taskHeap.add(task);
            domainTaskMap.put(task.domain, taskHeap);
            urls.add(task.url);
        }

        int getTaskSize() {
            int size = 0;
            for (PriorityQueue<Task> queue : domainTaskMap.values()) {
                size += queue.size();
            }
            return size;
        }
    }

    static class Work {
        int startTime;
        int endTime;

        public Work(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    static class Task {
        int time;
        int priority;
        String domain;
        String id;
        String url;

        public Task(int time, int priority, String domain, String id, String url) {
            this.time = time;
            this.priority = priority;
            this.domain = domain;
            this.id = id;
            this.url = url;
        }

        int getPriorTask(Task compare) {
            if (this.priority != compare.priority) {
                return Integer.compare(this.priority, compare.priority);
            }
            return Integer.compare(this.time, compare.time);
        }

    }



}
