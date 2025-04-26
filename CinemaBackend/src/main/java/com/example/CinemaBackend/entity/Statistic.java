package com.example.CinemaBackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_users", nullable = false)
    private Integer totalUsers;

    @Column(name = "total_movies", nullable = false)
    private Integer totalMovies;

    @Column(name = "total_views", nullable = false)
    private Integer totalViews;

    @Column(name = "total_revenue", nullable = false, precision = 10, scale = 2)
    private Double totalRevenue;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
    

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Integer return the totalUsers
     */
    public Integer getTotalUsers() {
        return totalUsers;
    }

    /**
     * @param totalUsers the totalUsers to set
     */
    public void setTotalUsers(Integer totalUsers) {
        this.totalUsers = totalUsers;
    }

    /**
     * @return Integer return the totalMovies
     */
    public Integer getTotalMovies() {
        return totalMovies;
    }

    /**
     * @param totalMovies the totalMovies to set
     */
    public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

    /**
     * @return Integer return the totalViews
     */
    public Integer getTotalViews() {
        return totalViews;
    }

    /**
     * @param totalViews the totalViews to set
     */
    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    /**
     * @return Double return the totalRevenue
     */
    public Double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * @param totalRevenue the totalRevenue to set
     */
    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    /**
     * @return LocalDateTime return the updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return User return the admin
     */
    public User getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(User admin) {
        this.admin = admin;
    }

}
