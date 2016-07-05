/**
 * Created by Dragos on 29.06.2016.
 */


class MyRunnable {
    private final long countUntil;
    Dog dog = new Dog(0);

    MyRunnable(long countUntil) {
        this.countUntil = countUntil;
    }

    public void run(int val) {
        Dog dog = new Dog(val);

        dog.age = val;
        System.out.println("" + Thread.currentThread() + dog + " " + dog.hashCode());
        for (long i = 1; i < countUntil; i++) {

            dog.age += i;
        }
        System.out.println("" + Thread.currentThread() + dog);
    }
}

public class Test3 {

    public static void main(String ...args){
        MyRunnable myRunnable = new MyRunnable(1000);

        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myRunnable.run(0);
                }
            }).start();
        }

    }


}

class Dog{
    long age = 0;

    public Dog(long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}
