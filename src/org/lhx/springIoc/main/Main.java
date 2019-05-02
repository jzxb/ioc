package org.lhx.springIoc.main;

import org.lhx.springIoc.ClassPathXmlApplicationContext;
import org.lhx.springIoc.service.IStudentService;
import org.lhx.springIoc.vo.Student;

/**
 * @author lhx
 * @date 2019/5/2 - 15:25
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myIoc.xml");
        IStudentService studentService = (IStudentService) context.getBean("studentService");
        studentService.add(new Student());
    }
}
