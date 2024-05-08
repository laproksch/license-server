package at.fh.lp.licenseserver.service;

import at.fh.lp.licenseserver.entity.License;
import at.fh.lp.licenseserver.repository.LicenseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    public LicenseRepository licenseRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public List<License> getLicenses() {
        return licenseRepository.findAll();
    }

    public License getLicense(Long licenseId) {
        return licenseRepository.findById(licenseId).orElseThrow();
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
