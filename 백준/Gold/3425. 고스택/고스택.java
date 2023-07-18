import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final double MAX_NUM = Math.pow(10, 9);
    Deque<String> commands;
    Deque<Long> stack;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.solution();
    }

    void init() {
        commands = new ArrayDeque<>();
    }

    void solution() throws Exception {
        while (true) {
            String firstInput = BR.readLine();
            if (firstInput.equals("QUIT")) {
                break;
            } else {
                init();

                if (!firstInput.equals("END")) {
                    commands.add(firstInput);
                    while (true) {
                        String command = BR.readLine();
                        if (command.equals("END")) {
                            break;
                        }
                        commands.add(command);
                    }
                }

                int N = Integer.parseInt(BR.readLine());
                for (int i = 0; i < N; i++) {
                    long given = Long.parseLong(BR.readLine());
                    stack = new ArrayDeque<>();
                    stack.add(given);
                    boolean done = true;
                    for (String command : commands) {
                        boolean worked = work(command);
                        if (!worked) {
                            done = false;
                            break;
                        }
                        if (stack.size() > 0 && Math.abs(stack.getLast()) > MAX_NUM) {
                            done = false;
                            break;
                        }
                    }
                    if (done && stack.size() == 1) {
                        BW.write(Long.toString(stack.removeLast()));
                        BW.newLine();
                    } else {
                        BW.write("ERROR\n");
                    }
                }

                BR.readLine();
                BW.newLine();

            }
        }
        BW.flush();
        BW.close();
    }

    boolean work(String command) {
        switch (command) {
            case "POP" -> {
                if (stack.size() > 0) {
                    stack.removeLast();
                    return true;
                } else {
                    return false;
                }
            }
            case "INV" -> {
                if (stack.size() > 0) {
                    stack.add(-stack.removeLast());
                    return true;
                } else {
                    return false;
                }
            }
            case "DUP" -> {
                if (stack.size() > 0) {
                    stack.add(stack.getLast());
                    return true;
                } else {
                    return false;
                }
            }
            case "SWP" -> {
                if (stack.size() > 1) {
                    Long firstNum = stack.removeLast();
                    Long secondNum = stack.removeLast();
                    stack.add(firstNum);
                    stack.add(secondNum);
                    return true;
                } else {
                    return false;
                }
            }
            case "ADD" -> {
                if (stack.size() > 1) {
                    Long firstNum = stack.removeLast();
                    Long secondNum = stack.removeLast();
                    stack.add(firstNum + secondNum);
                    return true;
                } else {
                    return false;
                }
            }
            case "SUB" -> {
                if (stack.size() > 1) {
                    Long firstNum = stack.removeLast();
                    Long secondNum = stack.removeLast();
                    stack.add(secondNum - firstNum);
                    return true;
                } else {
                    return false;
                }
            }
            case "MUL" -> {
                if (stack.size() > 1) {
                    Long firstNum = stack.removeLast();
                    Long secondNum = stack.removeLast();
                    stack.add(firstNum * secondNum);
                    return true;
                } else {
                    return false;
                }
            }
            case "DIV" -> {
                if (stack.size() > 1) {
                    Long firstNum = stack.removeLast();
                    Long secondNum = stack.removeLast();
                    if (firstNum == 0) {
                        return false;
                    }
                    long i = Math.abs(secondNum) / Math.abs(firstNum);
                    if (secondNum * firstNum > 0) {
                        stack.add(i);
                    } else {
                        stack.add(-i);
                    }
                    return true;
                } else {
                    return false;
                }
            } case "MOD" -> {
                if (stack.size() > 1) {
                    Long firstNum = stack.removeLast();
                    Long secondNum = stack.removeLast();
                    if (firstNum == 0) {
                        return false;
                    }
                    long i = Math.abs(secondNum) % Math.abs(firstNum);
                    if (secondNum > 0 ) {
                        stack.add(i);
                    } else {
                        stack.add(-i);
                    }
                    return true;
                } else {
                    return false;
                }
            }
            default -> {
                String[] split = command.split(" ");
                stack.add(Long.parseLong(split[1]));
                return true;
            }
        }
    }
}
