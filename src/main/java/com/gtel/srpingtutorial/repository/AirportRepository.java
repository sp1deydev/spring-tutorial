package com.gtel.srpingtutorial.repository;

import com.gtel.srpingtutorial.model.Airport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM airport WHERE iata = ?1)", nativeQuery = true)
    boolean isExisted(String iata);

    @Query(value = "SELECT * FROM airport OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<Airport> getAirports(int page, int size);

    @Query(value = "SELECT COUNT(*) FROM airport", nativeQuery = true)
    int countAirports();

    @Query(value = "SELECT * FROM airport WHERE iata = ?1", nativeQuery = true)
    Airport getAirport(String iata);

    @Query(value = "INSERT INTO airport (iata, name, airport_group_code, language, priority) " +
            "VALUES (:#{#airport.iata}, :#{#airport.name}, :#{#airport.airportGroupCode}, " +
            ":#{#airport.language}, :#{#airport.priority}) RETURNING *", nativeQuery = true)
    Airport createAirport(@Param("airport") Airport airport);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM airport WHERE iata= ?1", nativeQuery = true)
    void deleteAirport(String iata);

    @Query(value = "UPDATE airport " +
            "SET name = :#{#airport.name}, " +
            "airport_group_code = :#{#airport.airportGroupCode}, " +
            "language = :#{#airport.language}, " +
            "priority = :#{#airport.priority} " +
            "WHERE iata = :#{#airport.iata} RETURNING *",
            nativeQuery = true)
    Airport updateAirport(@Param("airport") Airport airport);

    @Query(value = "UPDATE airport " +
            "SET " +
            "name = CASE WHEN :#{#airport.name} IS NOT NULL THEN :#{#airport.name} ELSE name END, " +
            "airport_group_code = CASE WHEN :#{#airport.airportGroupCode} IS NOT NULL THEN :#{#airport.airportGroupCode} ELSE airport_group_code END, " +
            "language = CASE WHEN :#{#airport.language} IS NOT NULL THEN :#{#airport.language} ELSE language END, " +
            "priority = CASE WHEN :#{#airport.priority} IS NOT NULL THEN :#{#airport.priority} ELSE priority END " +
            "WHERE iata = :#{#airport.iata} RETURNING *",
            nativeQuery = true)
    Airport updatePatchAirport(@Param("airport") Airport airport);
}
