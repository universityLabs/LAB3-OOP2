package com.tech.zootech.security.DTO;

import com.tech.zootech.security.domain.interfaces.IReport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ReportDto extends AbstractDto implements IReport {
    private String name;

    private String type;

    public ReportDto(IReport iReport) {
        this.id = iReport.getId();
        this.created = iReport.getCreated();
        this.updated = iReport.getUpdated();
        this.name = iReport.getName();
        this.type = iReport.getType();
    }
}
