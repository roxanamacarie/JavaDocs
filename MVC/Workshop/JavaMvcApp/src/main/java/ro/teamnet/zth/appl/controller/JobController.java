package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs(){
        JobDao jobDao = new JobDao();
        return jobDao.getAllJobs();
    }
    @MyRequestMethod(urlPath = "/one")
    public String getOneJob(){
        return "oneRandomJob";
    }
}
