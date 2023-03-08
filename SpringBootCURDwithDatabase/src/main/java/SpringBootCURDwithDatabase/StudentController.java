package SpringBootCURDwithDatabase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	SessionFactory sf;
	Student tr;
	
		@GetMapping("SingleRecord")
		Student SingleRecord() {
			Session ss= sf.openSession();
			Student tr= ss.load(Student.class, 2);
			System.out.println("single record from database");
			return tr;
		}
	
	@GetMapping("SingleRecord2/{id}")
	Student SingleRecord2(@PathVariable int id) {
		Session ss= sf.openSession();
		Student tr= ss.load(Student.class, id);
		System.out.println("single record from database");
		return tr;
	}
	
	@GetMapping("multipleRecord")
	List multipleRecord() {
		
		Session ss= sf.openSession();
		Query query=ss.createQuery("from Student");
		List<Student>list= query.list();
		System.out.println("Multiple record of Student");
		return list;
		
	}
	
	
	@PostMapping("insertRecord")
	Student insertRecord(@RequestBody Student student) {
		Session ss=sf.openSession();
		
		Transaction tx= ss.beginTransaction();
		ss.save(student);
		System.out.println("insert a record of student");
		tx.commit();
		return student;
		}
	
	@PutMapping("updateRecord")
	Student updateRecord(@RequestBody Student student) {
		Session ss=sf.openSession();
		
		Transaction tx= ss.beginTransaction();
		ss.update(student);
		System.out.println("update the record");
		tx.commit();
		return student;
	}	
	@DeleteMapping("deleteRecord/{id}")
	Student deleteSingleRecord(@PathVariable int id) {
		Session ss=sf.openSession();
		
		Transaction tx= ss.beginTransaction();
		Student tr= ss.load(Student.class, id);
		ss.delete(tr);
		System.out.println("delete the single record from the student");
		tx.commit();
		return tr;
	}	
}
