package io.zipcoder;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;

public class MonkeyTypewriter {

    static String introduction = "It was the best of times,\n" +
            "it was the worst of times,\n" +
            "it was the age of wisdom,\n" +
            "it was the age of foolishness,\n" +
            "it was the epoch of belief,\n" +
            "it was the epoch of incredulity,\n" +
            "it was the season of Light,\n" +
            "it was the season of Darkness,\n" +
            "it was the spring of hope,\n" +
            "it was the winter of despair,\n" +
            "we had everything before us,\n" +
            "we had nothing before us,\n" +
            "we were all going direct to Heaven,\n" +
            "we were all going direct the other way--\n" +
            "in short, the period was so far like the present period, that some of\n" +
            "its noisiest authorities insisted on its being received, for good or for\n" +
            "evil, in the superlative degree of comparison only.";

    public static void main(String[] args) {
        unsafeCopierMonkeys();
        safeCopierMonkeys();

        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }

    }

// Do all of the Monkey / Thread building here
    // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
    // A Tale Of Two Cities.

    public static void unsafeCopierMonkeys(){
        UnsafeCopier unsafeCopier = new UnsafeCopier(introduction);
        startUnsafeMonkeys(unsafeCopier);
        System.out.println("Unsafe Copy:");
        System.out.println(unsafeCopier.copied);
    }

    public static void startUnsafeMonkeys(UnsafeCopier unsafeCopier) {
        for (int i = 0; i < 5; i++) {
            Thread unsafeMonkey = new Thread(unsafeCopier);
            unsafeMonkey.start();
        }
    }

    public static void safeCopierMonkeys(){
        SafeCopier safeCopier = new SafeCopier(introduction);
        startSafeMonkeys(safeCopier);
        System.out.println("Safe Copy:");
        System.out.println(safeCopier.copied);
    }

    public static void startSafeMonkeys(SafeCopier safeCopier) {
        for (int i = 0; i < 5; i++) {
            Thread safeMonkey = new Thread(safeCopier);
            safeMonkey.start();
        }
    }

}