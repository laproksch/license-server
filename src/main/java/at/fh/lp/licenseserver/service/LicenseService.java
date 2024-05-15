package at.fh.lp.licenseserver.service;

import at.fh.lp.licenseserver.entity.License;
import at.fh.lp.licenseserver.repository.LicenseRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LicenseService {

    private static final int INACTIVITY_TIME_IN_MIN = 10;

    public LicenseRepository licenseRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void checkLicensesForInactivity() {
        log.info("Checking licenses for inactivity (lastCheck older than {} minutes)",
            INACTIVITY_TIME_IN_MIN);
        List<License> allLicenses = getLicenses();
        List<License> licensesToUpdate = new ArrayList<>();
        Date inactivityDate = new Date();
        inactivityDate.setTime(inactivityDate.getTime() - INACTIVITY_TIME_IN_MIN * 60000);
        for (License license : allLicenses) {
            if (license.getLastCheck().before(inactivityDate)) {
                license.setInUse(false);
                licensesToUpdate.add(license);
            }
        }
        log.info("Found {} licenses which aren't inUse anymore", licensesToUpdate.size());
        for (License license : licensesToUpdate) {
            updateLicense(license);
        }
        log.info("Finished license check for inactivity");
    }

    public List<License> getLicenses() {
        return licenseRepository.findAll();
    }

    public License getLicense(Long licenseId) {
        return licenseRepository.findById(licenseId).orElseThrow();
    }

    public License getLicenseByFingerprint(String fingerprint) {
        return licenseRepository.findFirstByFingerprint(fingerprint);
    }

    public License createLicense(License license) {
        return licenseRepository.save(license);
    }

    public License updateLicense(License license) {
        if (licenseRepository.existsById(license.getId())) {
            return licenseRepository.save(license);
        }
        return null;
    }

    public void deleteLicense(Long licenseId) {
        licenseRepository.deleteById(licenseId);
    }
}
