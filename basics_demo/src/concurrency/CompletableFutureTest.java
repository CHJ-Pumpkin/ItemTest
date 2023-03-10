package concurrency;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Pumpkin
 * @createTime 2023/3/10 23:44
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        try {
            CompletableFutureOfSupplyAsyncAndThenCompose();
            CompletableFutureOfSupplyAsyncAndAllOf();
        } catch (ExecutionException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private static void CompletableFutureOfSupplyAsyncAndThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1 + 2)
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s * 3));

        System.out.println(future.get());
        System.out.println(1 + 2 * 3);
    }

    private static void CompletableFutureOfSupplyAsyncAndAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future1 done...");
            }
            return "task1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future2 done...");
            }
            return "task2";
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future3 done...");
            }
            return "task3";
        });

        // allOf() 方法会等到所有的 CompletableFuture 都运行完成之后再返回
        CompletableFuture<Void> future = CompletableFuture.allOf(task1, task2, task3);

        // 调用 join() 方法可以让 task1、task2、task3 都执行完之后再继续执行
        future.join();
        System.out.println("all done!");

        // anyOf() 方法不会等待所有的 CompletableFuture 都运行完成之后再返回，只要有一个执行完成即可！
        CompletableFuture<Object> future1 = CompletableFuture.anyOf(task1, task2, task3);
        System.out.println(future1.get());
    }

}
