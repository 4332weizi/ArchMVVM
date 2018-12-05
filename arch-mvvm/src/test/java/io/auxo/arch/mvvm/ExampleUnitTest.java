package io.auxo.arch.mvvm;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

import io.auxo.arch.mvvm.viewmodel.command.Command;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    private int value = 0;

    @Test
    public void testCommand() throws Exception {
        ((Command) () -> value = 1).execute();
        assertEquals(value, 1);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> assertEquals(value, 3));
        // Command command = new StatefulAsyncCommandWrapper(() -> {
        //     value++;
        //     try {
        //         barrier.await();
        //     } catch (BrokenBarrierException e) {
        //         e.printStackTrace();
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // });

        // command.execute();
        // new Thread(() -> {
        //     long start = System.currentTimeMillis();
        //     for (; ; ) {
        //         command.execute();
        //         long now = System.currentTimeMillis();
        //         if (now - start >= 3000) {
        //             break;
        //         }
        //     }
        //     try {
        //         barrier.await();
        //     } catch (BrokenBarrierException e) {
        //         e.printStackTrace();
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // });
    }
}