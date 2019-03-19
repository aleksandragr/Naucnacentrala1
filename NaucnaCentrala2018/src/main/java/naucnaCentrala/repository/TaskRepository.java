package naucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import naucnaCentrala.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
