package at.fh.lp.licenseserver.rest;

import at.fh.lp.licenseserver.entity.License;
import at.fh.lp.licenseserver.rest.entity.LicenseFingerprint;
import at.fh.lp.licenseserver.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/license")
public class LicenseAdminEndpoint {

    private final LicenseService licenseService;

    @Autowired
    public LicenseAdminEndpoint(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public Iterable<License> getLicenses() {
        return licenseService.getLicenses();
    }

    @GetMapping("/{id}")
    public License getLicenseById(@PathVariable("id") Long id) {
        return licenseService.getLicense(id);
    }

    @PostMapping
    public License createLicense(@RequestBody License license) {
        return licenseService.createLicense(license);
    }

    @PutMapping("/{id}")
    public License updateLicense(@PathVariable("id") Long id, @RequestBody License license) {
        return licenseService.updateLicense(license);
    }

    @DeleteMapping("/{id}")
    public void deleteLicense(@PathVariable("id") Long id) {
        licenseService.deleteLicense(id);
    }
}
