package pl.mandat.mandat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mandat.mandat.Model.UserModel;

import java.util.Optional;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel,Long> {
    java.util.Optional<UserModel> findByUsername(String name);
}
