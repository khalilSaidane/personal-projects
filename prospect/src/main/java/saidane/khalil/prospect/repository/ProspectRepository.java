package saidane.khalil.prospect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saidane.khalil.prospect.model.Prospect;
import saidane.khalil.prospect.model.enumeration.EProspectStatus;

import java.util.Optional;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect, Long> {
    Optional<Prospect> findByIdAndStatus(Long id, EProspectStatus status);
}
