package at.fh.lp.licenseserver.rest;

import at.fh.lp.licenseserver.entity.License;
import at.fh.lp.licenseserver.rest.entity.LicenseFingerprint;
import at.fh.lp.licenseserver.rest.entity.LicenseInfoTransferObject;
import at.fh.lp.licenseserver.rest.mapper.LicenseMapper;
import at.fh.lp.licenseserver.service.LicenseService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/license")
public class LicenseEndpoint {
    private static final Logger log = LoggerFactory.getLogger(LicenseEndpoint.class);
    private final LicenseService licenseService;
    private final LicenseMapper licenseMapper = new LicenseMapper();

    @Autowired
    public LicenseEndpoint(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public LicenseInfoTransferObject getLicenseByFingerprint(@RequestBody LicenseFingerprint licenseFingerprint) {
        log.info("GET /license for {}", licenseFingerprint);
        License license = licenseService.getLicenseByFingerprint(licenseFingerprint.getFingerprint());
        if (license == null) {
            license = new License();
            license.setFingerprint(licenseFingerprint.getFingerprint());
            license.setActive(false);
            license = licenseService.createLicense(license);
        }
        license.setLastCheck(new Date());
        license.setInUse(true);
        license = licenseService.updateLicense(license);
        return licenseMapper.map(license);
    }
}
