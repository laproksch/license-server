package at.fh.lp.licenseserver.rest.mapper;

import at.fh.lp.licenseserver.entity.License;
import at.fh.lp.licenseserver.rest.entity.LicenseInfoTransferObject;
import java.util.Date;

public class LicenseMapper {
    public LicenseInfoTransferObject map(License license) {
        LicenseInfoTransferObject licenseInfo = new LicenseInfoTransferObject();
        if (license != null) {
            licenseInfo.setName(license.getName());
            licenseInfo.setValid(license.isActive() && license.getExpireDate().after(new Date()));
        }
        return licenseInfo;
    }
}
