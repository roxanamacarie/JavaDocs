'use strict';

hrApp.factory('CommonResourcesFactory', function() {
      //  var baseUrl = "http://hrapp-zth.rhcloud.com/hrapp/";
        var baseUrl = "http://192.168.99.100:49100/app/mvc/";
        return {
            findAllDepartmentsUrl: baseUrl + "departments/all",
            findAllEmployeesUrl: baseUrl + "employees/all",
            findAllJobsUrl: baseUrl + "jobs/all",
            findAllLocationsUrl: baseUrl + "locations/all",
            findOneDepartmentUrl: baseUrl + "departments/one",
            findOneEmployeeUrl: baseUrl + "employees/one",
            findOneJobUrl: baseUrl + "jobs/one",
            findOneLocationUrl: baseUrl + "locations/one",
            deleteDepartmentUrl: baseUrl + "departments/delete",
            deleteEmployeeUrl: baseUrl + "employees/delete",
            deleteJobUrl: baseUrl + "jobs/delete",
            deleteLocationUrl: baseUrl + "locations/delete",
            addDepartmentUrl: baseUrl + "departments",
            addEmployeeUrl: baseUrl + "employees",
            addJobUrl: baseUrl + "jobs",
            addLocationUrl: baseUrl + "locations",
            editDepartmentUrl: baseUrl + "departments",
            editEmployeeUrl: baseUrl + "employees",
            editJobUrl: baseUrl + "jobs",
            editLocationUrl: baseUrl + "locations"
        };
    }
);