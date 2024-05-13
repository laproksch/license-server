package at.fh.lp.licenseserver.rest.entity;

import lombok.Data;

@Data
public class LicenseInfoTransferObject {
    private String name;
    private boolean valid = false;
}
