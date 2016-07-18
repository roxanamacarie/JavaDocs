/**
 * HelloWorldServlet.java
 *
 * <p>
 * Copyright (c) 2014 Teamnet. All Rights Reserved.
 * <p>
 * This source file may not be copied, modified or redistributed,
 * in whole or in part, in any form or for any reason, without the express
 * written consent of Teamnet.
 **/
package ro.teamnet.zth;

import oracle.jdbc.proxy.annotation.Methods;
import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestObject;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Hello World Servlet expose get method to say hello to input user
 */
public class MyDispatcherServlet extends HttpServlet {
    //rol de registru
    //key: urlPath
    //value: info despre metoda ce va procesa acest url
    HashMap<String,MethodAttributes> allowedMethods = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("GET",req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("PUT",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("POST",req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("DELETE",req,resp);
    }

    @Override
    public void init() throws ServletException {
        try {
            //cautare clase din pachet
            Iterable<Class> controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for(Class  controller: controllers){
               if( controller.isAnnotationPresent(MyController.class)){
                   //iau annotation
                   MyController myControllerAnnotation= (MyController) controller.getAnnotation(MyController.class);
                   String controllerUrlPath = myControllerAnnotation.urlPath();
                   Method[] controllerMethods = controller.getMethods();
                   for(Method controllerMethod : controllerMethods){
                       if(controllerMethod.isAnnotationPresent(MyRequestMethod.class)){
                           MyRequestMethod myRequestMethodAnnotation = controllerMethod.getAnnotation(MyRequestMethod.class);
                           String methodUrlPath= myRequestMethodAnnotation.urlPath();

                           //Construiesc cheia pt hashMap
                           String urlPath = controllerUrlPath+methodUrlPath;



                           //Construiesc valoarea
                           MethodAttributes methodAttributes = new MethodAttributes();
                           methodAttributes.setControllerClass(controller.getName());
                           methodAttributes.setMethodType(myRequestMethodAnnotation.methodType());
                           methodAttributes.setMethodName(controllerMethod.getName());
                           methodAttributes.setParameterTypes(controllerMethod.getParameterTypes());

                           //adaug in HashMap
                           allowedMethods.put(urlPath,methodAttributes);
                       }

                   }

               }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dispatchReply(String type, HttpServletRequest req, HttpServletResponse resp){

        //Metode
        //dispatch
        //reply
        //error
        Object r=null;
       try{
           r= dispatch(req,resp);
       }
       catch (Exception e){
           sendExceptionError(e,req,resp);
       }
        try {
            reply(r,req,resp);
        } catch (IOException e) {

            e.printStackTrace();
            sendExceptionError(e,req,resp);
        }

    }

    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String path = req.getPathInfo();
     /*   if(req.getPathInfo().startsWith("/employees")){
            EmployeeController employeeController = new EmployeeController();
            String result = employeeController.getAllEmployees();
            return result;
        }
        else
            if(req.getPathInfo().startsWith("/departments")){
                DepartmentController departmentController = new DepartmentController();
            String result = departmentController.getAllDepartments();
            return result;
        }

    */
        String id = req.getParameter("id");

        //acum trebuie sa caut in registru

        MethodAttributes methodAttributes=allowedMethods.get(path);

        if(methodAttributes==null){
            //nu putem procesa url-ul
            return "Hello";
        }
        else {

            String methodName =methodAttributes.getMethodName();

            String controllerName = methodAttributes.getControllerClass();

            try {
                Class<?> controllerClass = Class.forName(controllerName);
                Object controllerInstance = controllerClass.newInstance();
                Method method = controllerClass.getMethod(methodAttributes.getMethodName(),methodAttributes.getParameterTypes());
                Parameter[] parameters =method.getParameters();
                List<Object> parameterValues = new ArrayList<>();
                for (Parameter parameter : parameters) {
                    Class<?> type = parameter.getType();
                    if(parameter.isAnnotationPresent(MyRequestParam.class)){
                        MyRequestParam annotation = parameter.getAnnotation(MyRequestParam.class);
                        String name= annotation.name();
                        String requestParamValue = req.getParameter(name);

                        Object requestParamObject;
                        if(type.equals(String.class)){
                            requestParamObject=requestParamValue;
                        }
                        else{
                            requestParamObject =  new ObjectMapper().readValue(requestParamValue,type);
                        }

                        parameterValues.add(requestParamObject);
                    }
                    else
                    if(parameter.isAnnotationPresent(MyRequestObject.class)){
                        BufferedReader requestBodyReader = req.getReader();
                        Object requestObject= new ObjectMapper().readValue(requestBodyReader,type);
                        parameterValues.add(requestObject);
                    }
                }

                Object result = method.invoke(controllerInstance,parameterValues.toArray() );

                return result;

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return "hello";
    }

    private void reply(Object r,HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(r);
            resp.getWriter().write(valueAsString);


    }

    private void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp) {
    }

}
