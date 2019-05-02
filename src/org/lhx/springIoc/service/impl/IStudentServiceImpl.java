package org.lhx.springIoc.service.impl;


import org.lhx.springIoc.dao.IStudentDAO;
import org.lhx.springIoc.service.IStudentService;
import org.lhx.springIoc.vo.Student;

/**
 * @author lhx
 * @date 2019/3/30 - 18:19
 */
public class IStudentServiceImpl implements IStudentService {

    public IStudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public void add(Student student) {
        System.out.println("IStudentServiceImpl.add()");
        studentDAO.add(student);
    }

    public void destroy(){
        System.out.println("destroy()");
    }

    public void init(){
        System.out.println("init()");
    }

    private IStudentDAO studentDAO;
}
