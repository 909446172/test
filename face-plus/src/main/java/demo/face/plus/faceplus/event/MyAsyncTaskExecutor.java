package demo.face.plus.faceplus.event;

import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @Author zyy
 * @Date 2019/9/23 17:28
 * @Version 1.0
 */
public class MyAsyncTaskExecutor implements AsyncTaskExecutor {
    @Override
    public void execute(Runnable task, long startTimeout) {

    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public void execute(Runnable task) {

    }
}
