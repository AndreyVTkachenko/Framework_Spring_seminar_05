package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.model.Project;
import ru.gb.model.Timesheet;
import ru.gb.repository.ProjectRepository;
import ru.gb.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application {

    /**
     * spring-data - ...
     * spring-data-jdbc - зависимость, которая предоставляет удобные преднастроенные инструменты
     * для работы c реляционными БД
     * spring-data-jpa - зависимость, которая предоставляет удобные преднастроенные инструменты
     * для работы с JPA
     *      spring-data-jpa
     *       /
     *     /
     *   jpa   <------------- hibernate (ecliselink ...)
     *
     * spring-data-mongo - зависимость, которая предоставляет инструменты для работы с mongo
     * spring-data-aws
     *
     *
     */

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

//        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
//        jdbcTemplate.execute("delete from project");

        ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);
        for (int i = 1; i <= 5; i++) {
            Project project = new Project();
            project.setName("Project #" + i);
            projectRepo.save(project);
        }

        TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

        LocalDate createdAt = LocalDate.now();
        for (int i = 1; i <= 10; i++) {
            createdAt = createdAt.plusDays(1);

            Timesheet timesheet = new Timesheet();
            timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
            timesheet.setCreatedAt(createdAt);
            timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

            timesheetRepo.save(timesheet);
        }
    }
}
