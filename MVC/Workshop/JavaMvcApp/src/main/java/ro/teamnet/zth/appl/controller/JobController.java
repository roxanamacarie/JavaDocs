package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestObject;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;
import ro.teamnet.zth.appl.service.JobService;
import ro.teamnet.zth.appl.service.JobServiceImpl;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {
    private final JobService jobService = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs(){

        return jobService.findAllJobs();
    }
    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name="id")String id){


        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/delete",methodType = "DELETE")
    public void deleteOneEmployee(@MyRequestParam(name="id") String id){
        jobService.deleteOneJob(id);
    }


    @MyRequestMethod(urlPath = "/create",methodType = "POST")
    public Job saveJob(@MyRequestObject Job job){
        return jobService.save(job);
    }

}
