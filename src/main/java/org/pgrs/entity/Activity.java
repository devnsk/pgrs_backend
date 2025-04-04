package org.pgrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slNo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "logintime")
    private LocalDateTime loginTime; // Session Timeline

    @Column(name = "logouttime")
    private LocalDateTime logoutTime; // SessionLogged out Timeline

    @Column(name = "browser_name")
    private String browserName; // Stores Browser Name (Chrome/FireFox)

    @Column(name = "ip_address")
    private String ipAddress; // Stores user IP

    @Column(name = "device_type")
    private String deviceType; // Stores device info (Mobile/Desktop)

    @Column(name = "location")
    private String location;

}
