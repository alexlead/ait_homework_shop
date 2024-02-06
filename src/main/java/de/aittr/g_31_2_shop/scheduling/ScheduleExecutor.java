package de.aittr.g_31_2_shop.scheduling;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.services.jpa.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleExecutor {

    private TaskService taskService;
    private static Logger logger = LoggerFactory.getLogger(ScheduleExecutor.class);

    public ScheduleExecutor(TaskService taskService) {
        this.taskService = taskService;
    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask () {
//        System.out.println("Fixed delay task");
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedDelayTask () {
//        taskService.createTask("Fixed rate task 3000 ms");
//        try{
//            Thread.sleep(3000);
//        } catch ( InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

//    @Scheduled(fixedRate = 5000)
//    @Async
//    public void fixedRateAsyncTask () {
//        taskService.createTask("Fixed rate async task 7000 ms");
//        try{
//            Thread.sleep(7000);
//        } catch ( InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    @Scheduled(fixedRate = 5000, initialDelay = 10000)
//    public void fixedDelayTask () {
//        taskService.createTask("Initial delay task 7000 ms");
//
//    }
//    @Scheduled(fixedDelayString = "PT3S")
//    public void fixedDelayTask () {
//        taskService.createTask("Delay String format task 7000 ms");
//
//    }
//    @Scheduled(cron = "10,30 * * * * *")
//    public void cronExpressionTask () {
//        taskService.createTask("Cron format task");
//
//    }

//    public static void scheduleAndExecuteTask (Task task) {
//        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//        scheduler.schedule(
//                ()-> logger.info(task.getDescriiption()),
//                new CronTrigger("0,10,20,30,40,50 * * * * *")
//        );
//
//    }

    public static void scheduleAndExecuteTask (Task task) {
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Instant instant = Instant.now().plusSeconds(30);
        scheduler.schedule(
                ()-> logger.info(task.getDescriiption()),
                instant
        );

    }

}
