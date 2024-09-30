package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, JourneyId> {

    @Query("""
            select new cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO(j.origin.name, j.id.destinationName)
            from Journey j
            """)
    List<JourneyDTO> findAllRawDTO();

    @Query("""
            select j
            from Journey j
            """)
    Optional<JourneyId> findByIdJourneyId(JourneyId journeyId);
}
