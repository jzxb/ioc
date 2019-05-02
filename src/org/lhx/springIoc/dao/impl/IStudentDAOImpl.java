package org.lhx.springIoc.dao.impl;

import org.lhx.springIoc.dao.IStudentDAO;
import org.lhx.springIoc.vo.Student;

/**
 * @author lhx
 * @date 2019/3/30 - 18:13
 */
public class IStudentDAOImpl implements IStudentDAO {
    @Override
    public void add(Student student) {
        System.out.println("IStudentDAOImpl.add");
    }
}
