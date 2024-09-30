package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.FavoriteJourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.StationDTO;
import cat.tecnocampus.fgcstations.application.DTOs.StationTopFavoriteJourney;
import cat.tecnocampus.fgcstations.domain.Station;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, String> {

    //Do a findAllDTO
    /*@Query("""
            Select new cat.tecnocampus.fgcstations.application.DTOs.StationDTO(s.name, s.longitud, s.latitud)
            from Station s
            """)

     */
    @Query("Select s from Station s")
    List<StationDTO> findAllDTO();

    //Do a findByName
    Optional<Station> findByName(String name);

    //do a findByNameDTO (use the previous method)
    @Query("""
            Select s
            from Station s
            where s.name = :name
            """)
    Optional<StationDTO> findByNameDTO(String name);

    //Do a findStationsOrderedByFavoriteJourneysAsEitherOriginOrDestination
    @Query("""
            select s
            from Station s
            join Journey j on s = j.origin or s = j.destination
            group by s
            order by count(j) desc
            """)
    List<StationTopFavoriteJourney> findStationsOrderedByFavoriteJourneysAsEitherOriginOrDestination();
}
