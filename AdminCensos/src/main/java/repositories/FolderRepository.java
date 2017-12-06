package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer>{
	/*Name of folders by actorId*/
	@Query("select f.name from User u join u.folders f where u.id=?1")
	public Collection<String> foldersNameByUser(int userId);
	
	/*Id of folders by actorId*/
	@Query("select f.id from User u join u.folders f where u.id=?1")
	public Collection<Integer> foldersIdByUser(int userId);

}
