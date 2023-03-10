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

        // allOf() ???????????????????????? CompletableFuture ??????????????????????????????
        CompletableFuture<Void> future = CompletableFuture.allOf(task1, task2, task3);

        // ?????? join() ??????????????? task1???task2???task3 ?????????????????????????????????
        future.join();
        System.out.println("all done!");

        // anyOf() ??????????????????????????? CompletableFuture ?????????????????????????????????????????????????????????????????????
        CompletableFuture<Object> future1 = CompletableFuture.anyOf(task1, task2, task3);
        System.out.println(future1.get());
    }

}
