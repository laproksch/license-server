package at.fh.lp.licenseserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class License {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Date expireDate;
    @Column(unique = true)
    private String fingerprint;
    private Date lastCheck;
    private boolean active;
    private boolean inUse;

    public License(String name, String fingerprint, Date expireDate, Date lastCheck, boolean active,
                   boolean inUse) {
        this.name = name;
        this.fingerprint = fingerprint;
        this.expireDate = expireDate;
        this.lastCheck = lastCheck;
        this.active = active;
        this.inUse = inUse;
    }
}
