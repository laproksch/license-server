package at.fh.lp.licenseserver.repository;

import at.fh.lp.licenseserver.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
    public License findFirstByFingerprint(String fingerprint);
}
