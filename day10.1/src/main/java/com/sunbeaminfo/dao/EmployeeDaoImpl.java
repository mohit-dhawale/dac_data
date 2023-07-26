package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Department;
import com.sunbeaminfo.pojos.Employee;
import org.hibernate.*;
import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String addEmpToDept(Employee newEmp, Long deptId) {
		String mesg = "adding emp to dept failed !!!!!!!!!";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// 1. get dept ref
			Department dept = session.get(Department.class, deptId);
			if (dept != null) {
				// => valid dept
				// establish bi dir link between Dept <----> Emp
				dept.addEmployee(newEmp);
				// since no cascading yet , you will have to explicitly save emp details
				// session.persist(newEmp);
				mesg = "Added new emp to existing dept ....";
			}
			tx.commit(); /*
							 * session.flush --> dept : persistent --- existing --state 
							 * is changed --new emp
							 * entity --auto saved with FK
							 */
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
