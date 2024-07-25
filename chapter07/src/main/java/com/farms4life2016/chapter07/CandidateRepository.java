package com.farms4life2016.chapter07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class CandidateRepository {
    
    private DataSource dataSource;

    public CandidateRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // most of the methods below are structured like this:
    public Candidate findCandidateById(int id) throws SQLException {
        // we have public function for what we want to do.
        // it gets a connection to the db and then passes it
        // to a private function
        try (Connection conn = dataSource.getConnection()) {
            return findCandidateById(conn, id);
        }
    }

    // the private function will actually access or mutate the db
    private Candidate findCandidateById(Connection conn, int id) {
        String sql = "SELECT * FROM candidates WHERE id=?"; // SQL statement

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); // replace the ? in the query with useful arguments
            try (ResultSet rs = ps.executeQuery()) { // execute the SQL statement
                if (rs.next()) { // checks if result set is non-empty before returning a valid Candidate
                    return new Candidate(id, rs.getString("c_name"));
                } else {
                    throw new CandidateNotFoundException("ID#" + id + " was not found in the Candidate database.");
                }
            }
        } catch (SQLException e) {
            throw new CandidateNotFoundException(e);
        }

    }

    public Candidate saveCandidate(String name) throws SQLException {
        // we have these public-private function compositions so that we only need
        // one connection per operation. case in point here lol
        try (Connection conn = dataSource.getConnection()) {
            try {
                // attempts to save the candidate into the database
                return saveCandidate(conn, name);
            } catch (SQLException e) {
                // if there is a database-related problem, assume that the
                // specified candidate is already in the db
                e.printStackTrace();
                // so just get the existing candidate
                return findCandidateByName(conn, name);
            }
        }
    }

    private Candidate saveCandidate(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO candidates (c_name) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                return new Candidate(id, name);
            }
        }
    }

    public Candidate findCandidateByName(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findCandidateByName(conn, name);
        }
    }

    private Candidate findCandidateByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM candidates WHERE LOWER(name) = LOWER(?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Candidate(rs.getInt("c_id"), rs.getString("c_name"));
                } else {
                    throw new CandidateNotFoundException(name + " was not found in the Candidates database.");
                }
            }
        }
    }

    public List<Candidate> findAllCandidatesByName(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findAllCandidatesByName(conn, name);
        }
    }

    private List<Candidate> findAllCandidatesByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM candidates WHERE LOWER(c_name) LIKE LOWER(?)  ORDER BY c_name";
        List<Candidate> candidates = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    candidates.add(new Candidate(rs.getInt("c_id"), rs.getString("c_name")));
                }
            }
        }
        return candidates;
    }

}
