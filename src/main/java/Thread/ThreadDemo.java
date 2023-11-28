package Thread;

public class ThreadDemo {
    public ThreadDemo() {thread1();thread2();  }
    public void thread1(){
        new Thread(){
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println("听音乐"+i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("听完音乐");
            }
        }.start();
    }
    public void thread2(){
        new Thread(){
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println("写论文"+i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("写完论文");
            }
        }.start();
    }

    public static void main(String[] args) {
        new ThreadDemo();
    }
}
