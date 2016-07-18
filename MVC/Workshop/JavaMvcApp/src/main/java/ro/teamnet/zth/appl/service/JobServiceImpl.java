package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.controller.JobController;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */

public class JobServiceImpl implements JobService {
    @Override
    public List<Job> findAllJobs() {

        JobDao jobDao = new JobDao();

        return jobDao.getAllJobs();
    }

    @Override
    public Job findOneJob(String id) {


        JobDao jobDao = new JobDao();

        return jobDao.getJobById(id);
    }

    @Override
    public void deleteOneJob(String id) {
        JobDao jobDao = new JobDao();
        // Employee employee=employeeDao.getEmployeeById(id);
        //  employeeDao.deleteEmployee(employee);
        jobDao.deleteJob(jobDao.getJobById(id));

    }

    @Override
    public void save(Job job) {
        JobDao jobDao = new JobDao();
        jobDao.insertJob(job);
    }


}
