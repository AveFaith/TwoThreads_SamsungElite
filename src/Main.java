public class Main {
    public static void main(String[] args) {

        MyThread thread1 = new MyThread("Первый поток: ", 2, 20);
        thread1.start();
        MyThread thread2 = new MyThread("Второй поток: ", 1, 19);
        thread2.start();


    }
    public static final Object KEY = new Object();
    public static void printMessage(String name, int message) {
        synchronized (KEY) {
            try {
                System.out.println(name + message);
                Thread.sleep(300);
                KEY.notify();
                KEY.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } // synchronized
    }
}
class MyThread extends Thread {

    private String name;
    private int start;
    private int stop;
    MyThread(String name, int start, int stop) {
        this.start = start;
        this.name = name;
        this.stop = stop;
    }

    @Override
    public void run() {
        while (true) {
            Main.printMessage(this.name, this.start);
            if(start == stop){
                if(start % 2 == 0){
                    this.start = 2;
                }
                else {
                    this.start = 1;
                }
            }
            else {
                this.start += 2;
            }
        }
    }
}